package com.lhp.test;

import com.lhp.bean.Apple;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static java.util.Comparator.comparing;

/**
 * @author Amumu
 * @create 2020/6/18 22:10
 * 比较器复合
 */
public class Java8TestNew {
    ArrayList<Apple> apples = new ArrayList<>();

    @Before
    public void initList(){

        apples.add(new Apple("red",150,"hn")) ;
        apples.add(new Apple("red",150,"zz")) ;
        apples.add(new Apple("yellow",80,"sd")) ;
        apples.add(new Apple("black",150,"hh")) ;
        apples.add(new Apple("green",200,"hd")) ;
        apples.add(new Apple("orign",110,"tf")) ;
        apples.add(new Apple("red",150,"hn")) ;
        apples.add(new Apple("red",60,"hn")) ;
    }
    /**
     * 比较器复合
     */
    @Test
    public void testCompare(){

        //根据重量逆序   lambda
        apples.sort((a,b)->b.getWeight().compareTo(a.getWeight()));
        apples.sort((a,b)->b.getWeight().compareTo(a.getWeight()));
        //加入 方法引用  正序
        apples.sort(comparing(Apple::getWeight));
        //逆序
        apples.sort(comparing(Apple::getWeight).reversed());
        apples.sort(comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getColor)
        );
        apples.forEach(a-> System.out.println(a.toString()));


    }
    @Test
    public void test(){
        apples.stream().map(m->{
            m.setColor("aaa");
            return m;
        });
        apples.forEach(a-> System.out.println(a.toString()));

    }
}
