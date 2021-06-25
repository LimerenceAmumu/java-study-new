package com.lhp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Amumu
 * @create 2020/6/20 10:59
 * 订单
 */
@Data
@AllArgsConstructor
public class Transaction {
    private  Trader trader;
    private  int year;
    private  int value;
}
