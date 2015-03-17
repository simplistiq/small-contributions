package com.pandits.opensource.concurrent.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pandits.opensource.concurrent.model.SimpleRequest;
import com.pandits.opensource.concurrent.model.SimpleResponse;
import com.pandits.opensource.concurrent.model.executor.LatchExecutor;
import com.pandits.opensource.concurrent.model.internal.LatchRequest;

public class LatchServiceImpl implements LatchService {

    private static final Logger LOG = LoggerFactory.getLogger(LatchServiceImpl.class);

    private LatchExecutor executor;
    private final ExecutorService pool = Executors.newCachedThreadPool();

    public LatchServiceImpl(LatchExecutor executor) {
        super();
        this.executor = executor;
    }

    @Override
    public List<SimpleResponse> execute(List<SimpleRequest> simpleRequests) {
        Map<Long, LatchRequest> latchMap = new HashMap<Long, LatchRequest>();

        constructToWaitLatches(simpleRequests, latchMap);
        constructToReleaseLatches(simpleRequests, latchMap);

        List<Future<SimpleResponse>> futures = constructFutureRequests(latchMap);

        return blockAndAccumulateResponses(futures);
    }

    private List<SimpleResponse> blockAndAccumulateResponses(List<Future<SimpleResponse>> futures) {
        List<SimpleResponse> responses = new ArrayList<SimpleResponse>();

        boolean isCompleted = false;
        while (isCompleted == false) {
            Iterator<Future<SimpleResponse>> iterator = futures.iterator();
            while (iterator.hasNext()) {
                try {
                    Future<SimpleResponse> response = iterator.next();
                    if (response.isDone()) {
                        responses.add(response.get());
                        iterator.remove();
                    }
                }
                catch (InterruptedException | ExecutionException e) {
                    LOG.error("Error fetching responses {}");
                    break;
                }
            }
            if (futures.isEmpty()) {
                isCompleted = true;
            }
        }

        return responses;
    }

    private List<Future<SimpleResponse>> constructFutureRequests(
            Map<Long, LatchRequest> latchDependencyMap) {
        List<Future<SimpleResponse>> futures = new ArrayList<Future<SimpleResponse>>();
        for (Map.Entry<Long, LatchRequest> entry : latchDependencyMap.entrySet()) {
            final LatchRequest request = entry.getValue();
            Future<SimpleResponse> response = pool.submit(new Callable<SimpleResponse>() {

                @Override
                public SimpleResponse call() throws Exception {
                    return request.executeRequest();
                }

            });
            futures.add(response);
        }
        return futures;
    }

    private void constructToReleaseLatches(List<SimpleRequest> simpleRequests,
            Map<Long, LatchRequest> latchDependencyMap) {
        for (SimpleRequest request : simpleRequests) {
            for (SimpleRequest anotherRequest : simpleRequests) {
                if (anotherRequest.getId() == request.getId()) {
                    continue;
                }

                Set<Long> parentIds = anotherRequest.getDependentParentRequests();
                if (parentIds.contains(request.getId())) {
                    LatchRequest childRequest = latchDependencyMap.get(anotherRequest.getId());
                    LatchRequest parentRequest = latchDependencyMap.get(request.getId());
                    parentRequest.getToReleaseLatches().add(childRequest.getToWaitLatch());
                }
            }
        }
    }

    private void constructToWaitLatches(List<SimpleRequest> simpleRequests,
            Map<Long, LatchRequest> latchDependencyMap) {
        for (SimpleRequest request : simpleRequests) {
            LatchRequest latchRequest = null;
            CountDownLatch latch = null;

            Set<Long> parentRequests = request.getDependentParentRequests();
            if (parentRequests.size() > 0) {
                latch = new CountDownLatch(parentRequests.size());
                latchRequest = new LatchRequest(request.getId(), executor, latch);
            }
            else {
                latchRequest = new LatchRequest(request.getId(), executor);
            }

            latchDependencyMap.put(request.getId(), latchRequest);
        }
    }
}
