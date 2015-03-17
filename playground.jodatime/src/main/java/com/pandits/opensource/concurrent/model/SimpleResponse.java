package com.pandits.opensource.concurrent.model;

import java.util.UUID;

public class SimpleResponse {

    private UUID id;
    private Long requestId;

    public SimpleResponse(UUID id, Long requestId) {
        super();
        this.id = id;
        this.requestId = requestId;
    }

    public UUID getId() {
        return id;
    }

    public Long getRequestId() {
        return requestId;
    }

}
