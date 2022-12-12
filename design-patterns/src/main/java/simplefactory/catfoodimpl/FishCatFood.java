package simplefactory.catfoodimpl;

/**
 * 鱼香猫粮
 */
public class FishCatFood extends CatFood {
    public FishCatFood() {
        this.flavor = "fish";
    }

    @Override
    public void make() {
        System.out.println("正在制作【fish】口味猫粮");
    }
}
