package single;

/**
 * 静态内部类
 *
 * @author lihp
 * @date 2021年12月11日 9:41
 */
public class InnerHolder {
//    static {
//        //仅用来测试加载顺序，与单例模式无关
//        System.out.println(" HolderSingleton的静态代码块---");
//    }
//私有的构造方法
private InnerHolder() {
    System.out.println("HolderSingleton  构造函数");
}
    //私有静态内部类
    private static class HolerInstence{
        static {
            //仅用来测试加载顺序，与单例模式无关
            System.out.println("私有静态内部类的静态代码块---");
        }
        private static InnerHolder holderSingleton=new InnerHolder();
    }
    public static InnerHolder getHolderInstence(){
        System.out.println("获取HolderSingleton 实例 ");
        return HolerInstence.holderSingleton;
    }

}
