package com.pandits.opensource.concurrent.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.pandits.opensource.concurrent.model.SimpleRequest;
import com.pandits.opensource.concurrent.model.SimpleResponse;
import com.pandits.opensource.concurrent.model.executor.LatchExecutor;

import static org.junit.Assert.assertEquals;

public class LatchServiceImplTest {

    private LatchServiceImpl serviceImpl;
    private LatchExecutor latchExecutor;

    @Before
    public void before() {
        latchExecutor = new SimpleLatchExecutor();
        serviceImpl = new LatchServiceImpl(latchExecutor);
    }

    @Test
    public void testNonDependent() {
        List<SimpleRequest> simpleRequests = new ArrayList<SimpleRequest>();
        simpleRequests.add(createSimpleRequest(1L));
        simpleRequests.add(createSimpleRequest(2L));
        simpleRequests.add(createSimpleRequest(3L));
        List<SimpleResponse> responses = serviceImpl.execute(simpleRequests);
        assertEquals(3, responses.size());
    }

    @Test
    public void testDependent() {
        List<SimpleRequest> simpleRequests = new ArrayList<SimpleRequest>();
        simpleRequests.add(createSimpleRequest(1L));
        simpleRequests.add(createSimpleRequestDependet(2L, new long[] { 1 }));
        simpleRequests.add(createSimpleRequestDependet(3L, new long[] { 2 }));
        List<SimpleResponse> responses = serviceImpl.execute(simpleRequests);
        assertEquals(3, responses.size());
    }

    @Test
    public void testDependentComplex() {
        List<SimpleRequest> simpleRequests = new ArrayList<SimpleRequest>();
        simpleRequests.add(createSimpleRequest(1L));
        simpleRequests.add(createSimpleRequest(4L));
        simpleRequests.add(createSimpleRequest(3L));
        simpleRequests.add(createSimpleRequestDependet(2L, new long[] { 4, 3, 5 }));
        simpleRequests.add(createSimpleRequestDependet(5L, new long[] { 1, 4, 3 }));
        List<SimpleResponse> responses = serviceImpl.execute(simpleRequests);
        assertEquals(5, responses.size());
    }

    private SimpleRequest createSimpleRequest(long l) {
        SimpleRequest request = new SimpleRequest(l, null);
        return request;
    }

    private SimpleRequest createSimpleRequestDependet(long l, long[] d) {
        SimpleRequest request = createSimpleRequest(l);
        for (long entry : d) {
            request.getDependentParentRequests().add(entry);
        }
        return request;
    }
}
