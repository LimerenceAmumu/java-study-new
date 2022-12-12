package simplefactory;

import simplefactory.catfoodimpl.CatFood;

/**
 * 重构之后的order代码
 */
public class PaoMaChangV2 {

    public CatFood order(String flavor) {
//我们完成了封装的操作，把生成对象的操作集中在了SimpleCatFoodFactory中。
        CatFood catFood = SimpleCatFoodFactory.createCatFood(flavor);

        catFood.make();

        return catFood;
    }

    // 反射
    public CatFood order(Class<? extends CatFood> clazz) {

        CatFood catFood = SimpleCatFoodFactoryV2.createCatFood(clazz);

        catFood.make();

        return catFood;
    }
}