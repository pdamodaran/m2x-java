package com.att.m2x.client.util;

import java.util.Properties;

import org.junit.Assert;


public abstract class TestWithSamples {

    private Properties samples = null;

    protected abstract String getPathToProperties();

    protected String prop(String key) {
        if (samples == null) {
            try {
                samples = new Properties();
                samples.loadFromXML(ClassLoader.getSystemResourceAsStream(getPathToProperties()));
            } catch (Exception ex) {
                Assert.fail("Samples file not found");
            }
        }

        if (!samples.containsKey(key)) {
            Assert.fail("Sample key:" + key + " not found");
        }

        return samples.getProperty(key);
    }

}

