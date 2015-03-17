package com.pandits.opensource.concurrent.service;

import java.util.List;

import com.pandits.opensource.concurrent.model.SimpleResponse;
import com.pandits.opensource.concurrent.model.SimpleRequest;

public interface LatchService {
    public List<SimpleResponse> execute(List<SimpleRequest> simpleRequests);
}
