package com.lhp.thread.completablefuture;


import lombok.SneakyThrows;

public class UserInfoService {

    @SneakyThrows
    public UserInfo getUserInfo(Long userId) {
        Thread.sleep(300);//模拟调用耗时
        return new UserInfo("666", "捡田螺的小男孩", 27); //一般是查数据库，或者远程调用返回的
    }
}


