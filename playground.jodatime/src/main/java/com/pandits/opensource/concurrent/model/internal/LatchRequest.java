package com.pandits.opensource.concurrent.model.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.pandits.opensource.concurrent.model.SimpleResponse;
import com.pandits.opensource.concurrent.model.executor.LatchExecutor;

public class LatchRequest {

    private Long id;
    private LatchExecutor executor;
    private CountDownLatch toWaitLatch;
    private List<CountDownLatch> toReleaseLatches;

    public LatchRequest(Long id, LatchExecutor executor) {
        this(id, executor, null);
    }

    public LatchRequest(Long id, LatchExecutor executor, CountDownLatch toWaitLatch) {
        super();
        this.id = id;
        this.toWaitLatch = toWaitLatch;
        this.executor = executor;
    }

    public Long getId() {
        return id;
    }

    public LatchExecutor getExecutor() {
        return executor;
    }

    public CountDownLatch getToWaitLatch() {
        return toWaitLatch;
    }

    public List<CountDownLatch> getToReleaseLatches() {
        if (toReleaseLatches == null) {
            toReleaseLatches = new ArrayList<CountDownLatch>();
        }
        return toReleaseLatches;
    }

    public SimpleResponse executeRequest() throws InterruptedException {
        if (toWaitLatch != null) {
            toWaitLatch.await();
        }

        SimpleResponse execute = executor.execute(this);

        for (CountDownLatch releaseLatch : getToReleaseLatches()) {
            releaseLatch.countDown();
        }

        return execute;
    }

}
