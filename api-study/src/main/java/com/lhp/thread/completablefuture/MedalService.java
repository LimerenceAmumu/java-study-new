package com.lhp.thread.completablefuture;

import lombok.SneakyThrows;

public class MedalService {
    @SneakyThrows
    public MedalInfo getMedalInfo(long userId) {
        Thread.sleep(500); //模拟调用耗时
        return new MedalInfo("666", "守护勋章");
    }
}