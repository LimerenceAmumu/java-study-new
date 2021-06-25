package com.lhp.test;

import com.lhp.bean.Trader;
import com.lhp.bean.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author Amumu
 * @create 2020/9/17 13:48
 */
public class SortTest {
    List<Transaction> transactions = null;

    @Before
    @Test
    public void testInitData() {
        //交易员
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        //订单
        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950));

    }


    @Test
    public void testsa(){

        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(12);
        integers.add(13);

/*        integers.stream().filter(i->{
           if(i.equals(1)){
               return true;
           }else {return false;}

        }).collect(Collectors.toList());*/
        ArrayList<Integer> integers1 = new ArrayList<>(Arrays.asList(12));
        integers.removeAll(integers1);

        System.out.println("integers = " + integers);
    }
    @Test
    public void testSort1() {
        Comparator<Transaction> byValue = (t1, t2) -> {
            if (t1.getValue() > (t2.getValue())) {
                return 0;
            }
            return 0;
        };
      //  transactions.sort((t1, t2) -> t1.getValue().com);
    }


}
