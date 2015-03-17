package com.pandits.opensource.concurrent.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pandits.opensource.concurrent.model.SimpleResponse;
import com.pandits.opensource.concurrent.model.executor.LatchExecutor;
import com.pandits.opensource.concurrent.model.internal.LatchRequest;

public class SimpleLatchExecutor implements LatchExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleLatchExecutor.class);

    @Override
    public SimpleResponse execute(LatchRequest request) {

        LOG.debug("Executing request with request id {}", request.getId());

        return new SimpleResponse(UUID.randomUUID(), request.getId());
    }

}
