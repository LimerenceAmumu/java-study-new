package simplefactory.v3;

import simplefactory.catfoodimpl.CatFood;

/**
 * 麻辣猫粮
 */
public class MaLaCatFood extends CatFood {
    public MaLaCatFood() {
        this.flavor = "mala";
    }

    @Override
    public void make() {
        System.out.println("正在制作【麻辣】口味猫粮");
    }
}