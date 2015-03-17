package com.pandits.opensource.concurrent.model.executor;

import com.pandits.opensource.concurrent.model.SimpleResponse;
import com.pandits.opensource.concurrent.model.internal.LatchRequest;

public interface LatchExecutor {
    public SimpleResponse execute(LatchRequest request);
}
