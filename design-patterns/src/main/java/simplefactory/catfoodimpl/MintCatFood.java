package simplefactory.catfoodimpl;

/**
 * 薄荷猫粮
 */
public class MintCatFood extends CatFood {
    public MintCatFood() {
        this.flavor = "mint";
    }

    @Override
    public void make() {
        System.out.println("正在制作【mint】口味猫粮");
    }
}