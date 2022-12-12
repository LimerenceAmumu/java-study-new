package simplefactory.v3;

import simplefactory.catfoodimpl.CatFood;

/**
 * 葱香猫粮
 */
public class CongXiangCatFood extends CatFood {
    public CongXiangCatFood() {
        this.flavor = "congxiang";
    }

    @Override
    public void make() {
        System.out.println("正在制作【葱香】口味猫粮");
    }
}