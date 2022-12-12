package simplefactory.v3;

import simplefactory.catfoodimpl.CatFood;

/**
 * 跑码场对象-版本3
 *
 * @author 蝉沐风
 */
public class PaoMaChangV3 {

    private ICatFoodFactory factory;

    public PaoMaChangV3(ICatFoodFactory factory) {
        this.factory = factory;
    }

    public CatFood order(String flavor) {

        CatFood catFood = factory.create(flavor);

        catFood.make();

        return catFood;
    }
}