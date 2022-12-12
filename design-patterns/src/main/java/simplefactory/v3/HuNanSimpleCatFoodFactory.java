package simplefactory.v3;

import simplefactory.catfoodimpl.CatFood;

/**
 * 湖南分公司简单工厂
 *
 * @author 蝉沐风
 */
public class HuNanSimpleCatFoodFactory implements ICatFoodFactory {
    CatFood catFood;

    @Override
    public CatFood create(String flavor) {
        if ("duojiao".equals(flavor)) {
            catFood = new DuoJiaoCatFood();
        } else if ("mala".equals(flavor)) {
            catFood = new MaLaCatFood();
        } else {
            throw new RuntimeException("找不到该口味的猫粮");
        }

        return catFood;
    }
}