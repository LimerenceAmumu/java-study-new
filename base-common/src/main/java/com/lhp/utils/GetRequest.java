package com.lhp.akka;

import java.io.Serializable;

public class GetRequest implements Serializable {
    public final String key;
    public GetRequest(String key) {
        this.key = key;
    }
}