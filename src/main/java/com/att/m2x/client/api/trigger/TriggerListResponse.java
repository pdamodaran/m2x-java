package com.att.m2x.client.api.trigger;

import java.util.List;


public class TriggerListResponse {

    private List<Trigger> triggers;

    public List<Trigger> getTriggers() {
        return triggers;
    }

    public void setTriggers(List<Trigger> triggers) {
        this.triggers = triggers;
    }

}

