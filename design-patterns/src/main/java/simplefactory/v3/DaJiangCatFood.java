package simplefactory.v3;

import simplefactory.catfoodimpl.CatFood;

/**
 * 大酱猫粮
 */
public class DaJiangCatFood extends CatFood {
    public DaJiangCatFood() {
        this.flavor = "dajiang";
    }

    @Override
    public void make() {
        System.out.println("正在制作【大酱】口味猫粮");
    }
}