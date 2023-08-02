package decorator;

/**
 * 装饰者模式
 */
public class Client {
    public static void main(String[] args) {
        //基础
        Beverage beverage = new HouseBlend();
        //+1
        beverage = new Mocha(beverage);
        //+1
        beverage = new Milk(beverage);
        System.out.println(beverage.cost());
    }
}
