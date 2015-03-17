package com.pandits.opensource.concurrent.model;

import java.util.HashSet;
import java.util.Set;

public class SimpleRequest {

    private Long id;
    private Set<Long> dependentParentRequests;

    public SimpleRequest(Long id, Set<Long> dependentParentRequests) {
        super();
        this.id = id;
        this.dependentParentRequests = dependentParentRequests;
    }

    public Long getId() {
        return id;
    }

    public Set<Long> getDependentParentRequests() {
        if (dependentParentRequests == null) {
            dependentParentRequests = new HashSet<Long>();
        }
        return dependentParentRequests;
    }

}
