package simplefactory.v3;

import cn.hutool.core.util.StrUtil;
import org.junit.Test;

import java.math.BigDecimal;

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

    @Test
    public void test() {
        String s = "nihc,";
        String s1 = StrUtil.wrap(s, ",", ",");

    }


    @Test
    public void test2() {
        double a = 0.999999999999999999999999999998;
        double b = 0.99999999999999999999999999999;

        System.out.println(a - b);


        BigDecimal aa = new BigDecimal("0.999999999999999999999999999998");
        BigDecimal bb = new BigDecimal("0.99999999999999999999999999999");

        System.out.println(aa.compareTo(bb));


        BigDecimal aaa = new BigDecimal(a);
        BigDecimal bbb = new BigDecimal(b);
        System.out.println(aaa.compareTo(bbb));
    }

}
