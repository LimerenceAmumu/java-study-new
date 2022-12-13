package simplefactory.v3;

/**
 * @author : lihp
 * @Description :
 * @date : 2022/9/28 14:52
 */
public class Client {
    public static void main(String[] args) {
        HuNanSimpleCatFoodFactory huNanSimpleCatFoodFactory = new HuNanSimpleCatFoodFactory();
        PaoMaChangV3 paoMaChang = new PaoMaChangV3(huNanSimpleCatFoodFactory);
        //下单剁椒猫粮
        paoMaChang.order("duojiao");
    }


}
