package simplefactory;

import simplefactory.catfoodimpl.CatFood;

/**
 * 反射优化后的猫粮类的简单工厂
 *
 * @author 蝉沐风
 */
public class SimpleCatFoodFactoryV2 {
    public static CatFood createCatFood(Class<? extends CatFood> clazz) {
        if (clazz != null) {
            try {
                return clazz.newInstance();
            } catch (Exception e) {
                throw new RuntimeException("对象不存在");
            }
        }
        return null;

    }
}