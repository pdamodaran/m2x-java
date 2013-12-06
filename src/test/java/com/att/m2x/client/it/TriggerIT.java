package com.att.m2x.client.it;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import com.att.m2x.client.api.trigger.Condition;
import com.att.m2x.client.api.trigger.Trigger;
import com.att.m2x.client.builder.ModelBuilders;
import com.att.m2x.client.util.BaseResourceIT;


public class TriggerIT extends BaseResourceIT {

    @Test
    public void loadTrigger() {
        Trigger trigger = client.feeds().feed(prop("feed.id")).triggers().get(prop("trigger.id"));

        assertThat(trigger, is(notNullValue()));
        assertThat(trigger.getId(), is(prop("trigger.id")));
    }

    @Test
    public void loadAllTriggers() {
        List<Trigger> triggers = client.feeds().feed(prop("feed.id")).triggers().list();

        assertThat(triggers, is(not(empty())));
    }

    @Test
    public void createTrigger() {
        Trigger trigger = client.feeds().feed(prop("feed.id")).triggers().create(
                ModelBuilders.trigger().stream(prop("stream.id")).name(name)
                        .condition(Condition.EQUAL).value(String.valueOf(10))
                        .callback("http://example.com")

        );

        assertThat(trigger, is(notNullValue()));
        assertThat(trigger.getStream(), is(prop("stream.id")));
        assertThat(trigger.getCondition(), is(Condition.EQUAL));
        assertThat(trigger.getValue(), is(String.valueOf(10)));
    }

}

