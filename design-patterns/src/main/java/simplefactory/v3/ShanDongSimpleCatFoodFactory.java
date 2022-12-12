package simplefactory.v3;

import simplefactory.catfoodimpl.CatFood;

/**
 * 山东分公司简单工厂
 *
 * @author 蝉沐风
 */
public class ShanDongSimpleCatFoodFactory implements ICatFoodFactory {
    CatFood catFood;

    @Override
    public CatFood create(String flavor) {
        if ("congxiang".equals(flavor)) {
            catFood = new CongXiangCatFood();
        } else if ("dajiang".equals(flavor)) {
            catFood = new DaJiangCatFood();
        } else {
            throw new RuntimeException("找不到该口味的猫粮");
        }

        return catFood;
    }
}