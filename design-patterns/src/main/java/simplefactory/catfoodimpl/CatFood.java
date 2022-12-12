package simplefactory.catfoodimpl;

/**
 * 猫粮的抽象类，所有具体口味的猫粮必须继承自该接口
 *
 * @author 蝉沐风
 */
public abstract class CatFood {
    //产品风味
    public String flavor;

    public abstract void make();
}