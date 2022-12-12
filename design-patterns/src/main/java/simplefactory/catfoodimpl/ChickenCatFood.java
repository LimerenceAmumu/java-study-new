package simplefactory.catfoodimpl;

/**
 * 鸡肉猫粮
 */
public class ChickenCatFood extends CatFood {
    public ChickenCatFood() {
        this.flavor = "chicken";
    }

    @Override
    public void make() {
        System.out.println("正在制作【chicken】口味猫粮");
    }
}
