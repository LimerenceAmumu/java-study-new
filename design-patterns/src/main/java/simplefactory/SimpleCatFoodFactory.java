package simplefactory;

import simplefactory.catfoodimpl.*;

/**
 * SimpleCatFoodFactory不仅仅可以被order()方法使用了，之后的任何相关逻辑都可以调用我们写的这个类，而且如果后续需要改变，我们也仅仅需要改变这个单独的类就可以了”。
 * <p>
 * 招财无奈地回应说，“好吧，你的话确实很有道理，把经常变动的部分提取出来是个不错的代码优化习惯。对了，刚才这种优化技巧有名字吗？”
 * <p>
 * “这种叫简单工厂，很多开发人员都误以为它是一种设计模式了，但是它其实并不属于GoF23种设计模式，但是由于用的人太多，经常把它和工厂模式一起介绍。至于是不是设计模式，对我们而言并不重要。”
 */
public class SimpleCatFoodFactory {
    public static CatFood createCatFood(String flavor) {
        CatFood catFood;

        if ("fish".equals(flavor)) {
            catFood = new FishCatFood();
        } else if ("beef".equals(flavor)) {
            catFood = new BeefCatFood();
        } else if ("mint".equals(flavor)) {
            catFood = new MintCatFood();
        } else if ("chicken".equals(flavor)) {
            catFood = new ChickenCatFood();
        } else {
            throw new RuntimeException("找不到该口味的猫粮");
        }

        return catFood;

    }
}