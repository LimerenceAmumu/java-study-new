package simplefactory.catfoodimpl;

/**
 * 牛肉猫粮
 */
public class BeefCatFood extends CatFood {
    public BeefCatFood() {
        this.flavor = "beef";
    }

    @Override
    public void make() {
        System.out.println("正在制作【beef】口味猫粮");
    }
}
