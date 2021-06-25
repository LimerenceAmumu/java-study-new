package com.lhp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Amumu
 * @create 2020/6/21 18:06
 * 菜
 */
@Data
@AllArgsConstructor
public class Dish {
    private String name;
    private Integer calories;
    private Double price;
}
