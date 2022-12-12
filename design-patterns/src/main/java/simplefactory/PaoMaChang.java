package simplefactory;

import simplefactory.catfoodimpl.*;

public class PaoMaChang {

//    public FishCatFood order1() {
//      //  FishCatFood fishCatFood = new FishCatFood("fish");
//        fishCatFood.make();
//        return fishCatFood;
//    }

    public CatFood order2(String flavor) {

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

        catFood.make();

        return catFood;
    }
}