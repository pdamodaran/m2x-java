package com.att.m2x.client.api.feed;

import java.util.List;


public class LogListResponse {

    private List<LogEntry> requests;

    public List<LogEntry> getRequests() {
        return requests;
    }

    public void setRequests(List<LogEntry> requests) {
        this.requests = requests;
    }

}

