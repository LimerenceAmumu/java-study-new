package simplefactory.v3;

import simplefactory.catfoodimpl.CatFood;

/**
 * 剁椒猫粮
 */
public class DuoJiaoCatFood extends CatFood {
    public DuoJiaoCatFood() {
        this.flavor = "duojiao";
    }

    @Override
    public void make() {
        System.out.println("正在制作【剁椒】口味猫粮");
    }
}