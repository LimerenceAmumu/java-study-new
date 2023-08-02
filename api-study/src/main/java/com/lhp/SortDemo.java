package com.lhp;

import org.junit.Test;

import java.util.*;

/**
 * @author : lihp
 * @Description :
 * @date : 2023/5/9 17:02
 */
public class SortDemo {


    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Tom,25,Beijing,1001");
        list.add("Jack,23,Shanghai,1002");
        list.add("Lucy,25,Guangzhou,1003");

        String field = "25"; // 指定排序字段
        Collections.sort(list, getComparator(field));
        System.out.println(list);

        field = "1001";
        Collections.sort(list, getComparator(field));
        System.out.println(list);
    }

    public static Comparator<String> getComparator(String field) {
        final int index = getFieldIndex(field);
        Comparator<String> cmp = new Comparator<String>() {
            public int compare(String o1, String o2) {
                String[] arr1 = o1.split(",");
                String[] arr2 = o2.split(",");
                return arr1[index].compareTo(arr2[index]);
            }
        };
        return cmp;
    }

    public static int getFieldIndex(String field) {
        List<String> fields = Arrays.asList("name", "age", "address", "id");
        return fields.indexOf(field);

    }

    @Test
    public void test() {
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        stringIntegerHashMap.put("a.b", 1);

        System.out.println(stringIntegerHashMap.get("a.b"));
    }

    /**
     * 比如  list  内容是   name  age  address  id；我指定一些参数 比如 传入 age，name，name最终 list 的排序为  age name  address id， 传入 id  最终 list 为id  name  age  address
     *
     * @param source 被排序的list
     * @param args   参数
     * @return 排序后的list
     */
    public static List<String> sort(List<String> source, List<String> args) {
        List<String> sortedList = new ArrayList<>(source);
        Collections.sort(sortedList, new Comparator<String>() {
            public int compare(String s1, String s2) {
                String[] s1Fields = s1.split(" ");
                String[] s2Fields = s2.split(" ");
                for (String arg : args) {
                    int index = source.indexOf(arg);
                    if (index == -1 || index >= s1Fields.length || index >= s2Fields.length) {
                        continue;
                    }
                    String sub1 = s1Fields[index];
                    String sub2 = s2Fields[index];
                    int cmp = sub1.compareTo(sub2);
                    if (cmp != 0) {
                        return cmp;
                    }
                }
                return 0;
            }
        });
        return sortedList;
    }

    @Test
    public void testdddd() {
        List<String> list = new ArrayList<>();
        list.add("id");
        list.add("name");
        list.add("age");
        list.add("address");


        List<String> sortFields = Arrays.asList("age", "name"); // 指定排序字段
        List<String> sortedList = sort(list, sortFields);
        System.out.println(sortedList);

        sortFields = Arrays.asList("id", "name");
        sortedList = sort(list, sortFields);
        System.out.println(sortedList);
    }
}


