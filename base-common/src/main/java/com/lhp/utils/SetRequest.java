package com.lhp.akka;

import java.io.Serializable;

public class SetRequest implements Serializable {
    public final String key;
    public final Object value;
    public SetRequest(String key, Object value) {
        this.key = key;
        this.value = value;
    }

}