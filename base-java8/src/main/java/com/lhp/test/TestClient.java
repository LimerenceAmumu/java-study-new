package com.lhp.test;


import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.lhp.bean.DemoExcelBean;
import com.lhp.bean.OrderBusiness;

import com.lhp.functionInterfaces.Converter;
import com.lhp.functionInterfaces.TestDefaultMethod;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Amumu
 * @create 2019/12/16 15:06
 * java8新特性
 * 接口默认方法
 * lambda表达式
 * 函数式接口
 * predicate
 * function
 * supplier
 */
public class TestClient {
    /**
     * 测试java8中的默认方法
     */
    @Test
    public void testDefaultMethords() {
        TestDefaultMethod testDefaultMethod;
        testDefaultMethod = new TestDefaultMethod() {
        };
        int factorial = testDefaultMethod.factorial(4, 1);
        System.out.println("factorial = " + factorial);
    }

    @Test
    public void testReplace(){
        String old="hell_o wor_d!!";
        String newStr = old.replace("_", "^");
        System.out.println("newStr = " + newStr);

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,1);
        System.out.println("instance.toString() = " + instance.toString());


    }

    /**
     * lambda表达式1
     */
    @Test
    public void testComapartor() {
        //tranditional
        String[] strs = {"amumu", "aero", "aixi"};
        List<String> strings = Arrays.asList(strs);
        Collections.sort(strings, (a, b) -> {
            return a.compareTo(b);
        });
        //strings.add("add");UnsupportedOperationException
        //strings.remove("aero");UnsupportedOperationException
        //strings.clear();UnsupportedOperationException
        System.out.println("strings = " + strings);
        System.out.println(strings.get(1));
        strings.set(0, "AA");
    }

    /**
     * lambda表达式2
     *
     * @FunctionalInterface public interface Converter<F, T> {
     * T convert(F from);
     * }
     */
    @Test
    public void testConvert() {
        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        System.out.println(converter.convert("123"));
    }

    /**
     * 方法引用
     * 把一个方法的具体实现赋值给函数式接口
     * 调用函数式接口的方法来调用实际函数
     */
    @Test
    public void testMethod() {
        //静态方法
        Converter<Integer, String> converter = String::valueOf;
        System.out.println(converter.convert(999));
        //非静态方法
        Something something = new Something();
        Converter<String, String> startWith = something::startsWith;
        System.out.println(startWith.convert("JAVA"));


    }

    /**
     * testPredicate
     * boolean test(T t);
     * and
     * or
     * nagate
     */
    @Test
    public void testPredicate() {
        Predicate<Integer> bt10 = (i) -> i > 10;//i大于10 返回真
        System.out.println(bt10.test(10));//false
        //bt10.test(20)
        bt10.negate().test(10);
        //System.out.println(bt10.and(bt10).test(60));

        Predicate<Integer> lt20 = (i) -> i < 20;//小于20
        System.out.println(bt10.and(lt20).test(15));//大于10小于20 true
        System.out.println(bt10.or(lt20).test(1));//

    }

    /**
     * Function<T,R></>
     * R apply(T t);
     */
    @Test
    public void testFunction() {
        Function<String, Integer> strToInteger = Integer::valueOf;
        System.out.println(strToInteger.apply("956"));
        //before  comose
        Function<Integer, Integer> doubleInput = (e) -> e * 2;

        System.out.println(doubleInput//2. 再以strToInteger.apply("5") rtn为参数 计算doubleInput.apply(rtn)
                .compose(strToInteger).apply("5"));// 1.先计算 strToInteger.apply("5")
        //after andThen
        Function<Integer, String> intToStr = String::valueOf;
        // 先执行 doubleInput.apply(333) ==rtn ,再执行intToStr.apply(rtn)
        String apply = doubleInput.andThen(intToStr).apply(new Integer(333));

        //<T> Function<T, T> identity()  返回一个总是返回输入参数的函数
        System.out.println(Function.identity().apply(65));
    }

    /**
     * Supplier
     * T get();
     */
    @Test
    public void testSupplier() {
        Supplier<String> str = () -> new String("asd");

        System.out.println("str = " + str.get());
        Supplier<Double> randInteger = () -> Math.random();
        System.out.println(randInteger.get());
    }

    /**
     * Consumer
     * void accept(T t);
     */
    @Test
    public void testConsumer() {
        Consumer<Double> doubleConsumer = (d) -> System.out.println(d * 2);
        doubleConsumer.accept(5.65);

    }

    /**
     * Optional
     * <p>
     * public static String getChampionName(Competition comp) throws IllegalArgumentException {
     * return Optional.ofNullable(comp)
     * .map(c->c.getResult())
     * .map(r->r.getChampion())
     * .map(u->u.getName())
     * .orElseThrow(()->new IllegalArgumentException("The value of param comp isn't available."));
     * }
     * 适用于层级处理（依赖上一步操作）的场合。
     * 产生对象的方法若可能返回null，可以用Optional包装。
     * 尽可能延后处理null的时机，在过程中使用Optional保留不确定性。
     * 尽量避免使用Optional作为字段类型。
     */

    @Test
    public void testOptional() {

        //Optional<String> optionalS=Optional.
        String n = null;
        Optional<String> n1 = Optional.ofNullable(n);
        if (n1.isPresent()) {
            String s = n1.get();
        }
        Optional<byte[]> bytes = n1.map(String::getBytes);

        //of（）：为非null的值创建一个Optional
        Optional<String> optional = Optional.of("bam");
        // isPresent（）： 如果值存在返回true，否则返回false
        optional.isPresent();           // true
        //get()：如果Optional有值则将其返回，否则抛出NoSuchElementException
        optional.get();                 // "bam"
        //orElse（）：如果有值则将其返回，否则返回指定的其它值
        optional.orElse("fallback");    // "bam"
        Consumer<String> charAt0 = s -> System.out.println(s.charAt(0));
        //ifPresent（）：如果Optional实例有值则为其调用consumer，否则不做处理
        optional.ifPresent(charAt0);
        System.out.println();
        Consumer<String> consumer = System.out::println;
        //optional 为空则不做处理，不为空则为其调用consumer.accept
        optional.ifPresent(consumer);

        //23点04分
        String lihepeng = "lihepeng";
        Optional<String> lihepengOptional = Optional.ofNullable(lihepeng);
        lihepengOptional.ifPresent(s -> System.out.println(s.charAt(0)));
        Optional<Boolean> l = lihepengOptional.map(s -> s.startsWith("l"));
        String nullStr = null;
        Optional<String> nullStr1 = Optional.ofNullable(nullStr);
        String rtn = nullStr1.orElse("为空");
        System.out.println(rtn);


    }

    /**
     * Stream
     */
    @Test
    public void testStream() {
        List<String> stringList = new ArrayList<>();
        stringList.add("ddd2");
        stringList.add("aaa2");
        stringList.add("aaa3");
        stringList.add("bbb1");
        stringList.add("aaa1");
        stringList.add("bbb3");
        stringList.add("ccc");
        stringList.add("bbb2");
        stringList.add("ddd1");
        stringList.stream()
                //Stream<T> filter(Predicate<? super T> predicate);  中间操作
                .filter(s -> s.startsWith("a"))//过滤不是以a开头的元素
                //Stream<T> sorted(Comparator<? super T> comparator); 中间操作
                .sorted(Comparator.reverseOrder())
                //void forEach(Consumer<? super T> action);           最终操作
                .forEach(System.out::println);
        //并没有改变原本的stringList
        /*
         * Map(映射)
         *    中间操作 map 会将元素根据指定的 Function 接口来依次将元素转成另外的对象。
         *
         *    下面的示例展示了将字符串转换为大写字符串。
         *       你也可以通过map来将对象转换成其他类型，
         *    map返回的Stream类型是根据你map传递进去的函数的返回值决定的。
         * */
        System.out.println("-------------map----------------");
        stringList.stream()
                //<R> Stream<R> map(Function<? super T, ? extends R> mapper); 中间操作
                .map(String::toUpperCase)
                .forEach(System.out::println);


        /**
         * 所有的匹配都是最终操作，作用于所有的stream内元素
         */
        System.out.println("-------------match----------------");

        boolean isAllStartWith = stringList.stream()
                .allMatch(s -> s.startsWith("a"));
        System.out.println("isSllStartWith = " + isAllStartWith);

        //anyMatch  任意匹配
        //noneMatch  没有这样的匹配


        System.out.println("-------------count----------------");
        /**
         * count 最终操作 返回值是long
         */

        System.out.println(
                stringList.stream()
                        .count()
        );
        System.out.println("-------------reduce----------------");
        //测试 Reduce (规约)操作

        stringList
                .stream()
                .filter(s -> s.startsWith("a"))//过滤不是以a开头的元素
                .sorted()
                //Optional<T> reduce(BinaryOperator<T> accumulator);  返回值类型Optional
                .reduce((s1, s2) -> s1 + "#" + s2)
                .ifPresent(System.out::println);

        // 字符串连接，concat = "ABCD"
        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        // 求最小值，minValue = -3.0

        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        // 求和，sumValue = 10, 有起始值
        int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        // 求和，sumValue = 10, 无起始值 由于可能没有足够的元素，返回的是 Optional
        sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        // 过滤，字符串连接，concat = "ace"
        concat = Stream.of("a", "B", "c", "D", "e", "F").
                filter(x -> x.compareTo("Z") > 0).
                reduce("", String::concat);


    }

    /**
     * 并行流
     */
    @Test
    public void testParallelStream() {
        long initStart = System.nanoTime();
        //初始化一个大的数组
        int max = 9000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }
        long initEnd = System.nanoTime();
        long millisinit = TimeUnit.NANOSECONDS.toMillis(initEnd - initStart);
        System.out.println(String.format("init took: %d ms", millisinit));
        //串行排序
        long t0 = System.nanoTime();
        values.stream().sorted();
        //System.out.println(count);
        long t1 = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", millis));//sequential sort took: 4 ms
       /* //并行排序
        long t00 = System.nanoTime();
        values.parallelStream().sorted();
        long t01 = System.nanoTime();
        long millisp = TimeUnit.NANOSECONDS.toMillis(t01 - t00);
        System.out.println(String.format("parallel sort took: %d ms", millisp));//parallel sort took: 2 ms
*/
    }

    /**
     * Map Stream
     */

    @Test
    public void testMap() {
        Map<Integer, String> map = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }

        //BiConsumer void accept(T t, U u);
        map.forEach((id, val) -> System.out.println(id + val));
        // BiFunction<? super K, ? super V, ? extends V> remappingFunction)
        /**
         * 先根据key获取V
         *  若不为空则计算新的v，并放入map，若新的v为空则移除此key并返回null
         *  若为空则返回null
         */

        map.computeIfPresent(3, (num, val) -> val + num);
        map.get(3);             // val33

        map.computeIfPresent(9, (num, val) -> null);
        map.containsKey(9);     // false

        System.out.println(map.get(23));
        map.computeIfAbsent(23, num -> "val" + num);
        map.containsKey(23);    // true

        map.computeIfAbsent(3, num -> "bam");
        map.get(3);             // val33

    }

    @Test
    public void testRandom() {
        Random random = new Random();

        List<String> strings = Arrays.asList("22515", "23", "584318", "5ww848", "58f48", "584fs8",
                "584778", "584h8", "58g48", "584aqsd8", "5848asd",
                "58l48", "5k848", "58k48");
        while (true){
            System.out.println(strings.get(random.nextInt(strings.size()))+"  ===");
        }
    }

    @Test
    public void testMaptoList() {
        List<String> strings = Arrays.asList("15", null, "55");

        String join = Joiner.on("->").useForNull("nullll").join(strings);
        System.out.println("join = " + join);
    }

    @Test
    public void testException() {
        try {
            int a = 1 / 0;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------------");
        }
        System.out.println("================");
    }

    @Test
    public void testSplit() {
        String testStr = "20";
        String[] split = testStr.split(",");//【20】
        System.out.println("split = " + split);

        String testStr2 = "20,";
        String[] split2 = testStr.split(",");
        Arrays.stream(split2).forEach(System.out::println);//20
    }



    @Test
    public void testMapNull() {

        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        Object asd = objectObjectHashMap.get("asd");
        if (asd == null) {
            System.out.println("asd = " + " asd");
        }
    }

    @Test
    public void testExcel() throws IOException {
        File file = new File("C:\\Users\\Limer\\Desktop\\数据运营服务平台\\标签管理功能设计_20200528\\手工标签绑定数据资源导入模板.xlsx");
        XSSFWorkbook sheets = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet sheetAt1 = sheets.getSheetAt(1);
        int rows = sheetAt1.getLastRowNum();
        for (int i = 0; i < rows; i++) {
            XSSFRow row = sheetAt1.getRow(i);

            short columns = row.getLastCellNum();
            for (int j = 0; j < columns; j++) {
                XSSFCell cell = row.getCell(j);
                System.out.println("cell = " + cell);

            }


        }

    }

    @Test
    public void testFilter() {
        DemoExcelBean demoExcelBean = new DemoExcelBean();
        demoExcelBean.setChName("李白");
        demoExcelBean.setDbUser("LLL");
        DemoExcelBean demoExcelBean2 = new DemoExcelBean();
        demoExcelBean2.setChName("赵云");
        demoExcelBean2.setDbUser("LLL");
        ArrayList<DemoExcelBean> demoExcelBeans = new ArrayList<>(2);
        demoExcelBeans.add(demoExcelBean);
        demoExcelBeans.add(demoExcelBean2);

        List<DemoExcelBean> aa = demoExcelBeans.stream().filter(d -> d.getChName().contains("李"))
                .collect(Collectors.toList());
        System.out.println("aa = " + aa);

    }

    @Test
    public void testnull() {
        ArrayList<Object> objects = new ArrayList<>();
        if (objects.size() > 0) {
            System.out.println("00000000000000");
        } else {
            System.out.println("55555");
        }
    }

    @Test
    public void testMapKey(){
        HashMap<Object, Object> map  = new HashMap<>();
        map.put("AA",12);
        map.put("BB",13);
        map.put("AA",5222);
        Set<Map.Entry<Object, Object>> entries = map.entrySet();

    }
    @Test
    public void testInsertList(){
        List<Integer> intList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);
        //List<Integer> integers = intList.subList(0, 3);
        List<List<Integer>> subs = ListUtils.partition(intList, 3);
        subs.size();
        List<List<Integer>> child = Lists.partition(intList, 50);

    }
    @Test
    public void testInsertListFilter(){
        List<Integer> intList = Lists.newArrayList(1,2,3,5,9,10);
        intList=intList.stream().filter(i -> i>=4).collect(Collectors.toList());
        System.out.println("intList = " + intList);

    }
    @Test
    public void testMapKeyValue(){
        HashMap<String, String> map = new HashMap<>();
        map.put(null,"null value");
        map.put("null",null);
        map.put("normal","normal");
        String aaaaaaaaa = map.get("aaaaaaaaa");
        Set<Map.Entry<String, String>> entries = map.entrySet();
        entries.stream().forEach(en -> System.out.println(en.getKey()+"==="+en.getValue()));
    }
    @Test
    public void testSub(){
        String time="20200215";
        String pre = time.substring(0, 4);
        String end = time.substring(5, 7);
        System.out.println();
    }

    @Test
    public void testString(){


        String ss="33405";
        ss=ss+"01";
        System.out.println(ss);
        double v = Double.parseDouble("16.595800");
        BigDecimal bigDecimal = new BigDecimal(v);
        System.out.println(bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        
    }
    @Test
    public void testMapPut(){
        HashMap<String, String> map = new HashMap<>();
        map.put("demo","first");
        map.put("demo2","double");


        Set<Map.Entry<String, String>> entries = map.entrySet();

        HashMap<String, Object> mapObj = new HashMap<>();


    }
    @Test
    public void testStringCotains(){

        String str1 = "nihao  阿斯顿 阿萨撒大大大";
        int result1 = str1.indexOf("阿斯顿");
        if(result1 != -1){
            System.out.println("字符串str中包含子串“ab”"+result1);
        }else{
            System.out.println("字符串str中不包含子串“ab”"+result1);
        }

    }

    @Test
    public void testListCotains(){

        ArrayList<String> strings = Lists.newArrayList("println", "array", "string");
        System.out.println(strings.contains("array"));
        System.out.println(strings.contains("aaaa"));

    }
    @Test
    public void testList(){
        //保存最终的结果
        ArrayList<OrderBusiness> finalList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            //每次循环要处理的元数据
            ArrayList<OrderBusiness> sorceData = new ArrayList<>();
            for (OrderBusiness idItem : sorceData) {

                String id = idItem.getId();
                String finalName=idItem.getName();
                Integer type=idItem.getType();

                boolean has=false;
                for (OrderBusiness typeItem : sorceData) {
                    if(idItem==typeItem){
                        //是同一个对象（内存地址相同）  tiao过这次循环
                        continue;
                    }
                    if(Objects.equals(typeItem.getId(),id) &&  Objects.equals(typeItem.getType(),type) ){
                        // id 和type 相同    获取当前的 name 拼接在后面
                        finalName = Joiner.on(",").join(finalName, typeItem.getName());
                        has=true;
                    }
                    //
                }

                if(!has){
                    //没有任何一个元素和 外层循环的当前元素匹配
                    finalList.add(idItem);
                }else {
                    //
                    OrderBusiness finalOrder = new OrderBusiness();
                    finalOrder.setName(finalName);
                    finalOrder.setId(id);
                    finalOrder.setType(type);
                    finalList.add(finalOrder);

                }
            }

        }
    }


    @Test
    public void test(){
        double d = 341_435_936.445_667;
        System.out.println(d);
        int bin = 0b0010_1111_1010_1111_1010_1111_1010_1111;
        System.out.println(Integer.toBinaryString(bin));
        System.out.printf("%x%n", bin); // [1]
        long hex = 0x7f_e9_b7_aa;
        System.out.printf("%x%n", hex);
    }



    @Test
    public void testRemove(){
        List<String> source = new ArrayList<>(Arrays.asList("33-1", "33-2", "33", "30-2"));
        Iterator<String> sourceIterator = source.iterator();

        while (sourceIterator.hasNext()){
            String outCurrent = sourceIterator.next();
            // 包含
            if(outCurrent.indexOf("-") != -1){
                //取出 前缀
                String subfix = outCurrent.split("-")[0];
                for (String innerCurrent : source) {
                    if(subfix.equals(innerCurrent)){
                        sourceIterator.remove();
                    }
                }

            }
        }
        System.out.println(source);

    }
    @Test
    public void testEndWith(){
        String aa="12036";
        System.out.println(aa.endsWith("36"));//true
        System.out.println(aa.endsWith("06"));//false
    }

    @Test
    public void testDate() throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date date = new Date();
        String dateStr2="2020-8-17";
        Date parse = format.parse(dateStr2);
        //dateStr2.substring()
        System.out.println(format.format(parse));

        boolean sameDay = DateUtils.isSameDay(date, parse);
        System.out.println(sameDay);
    }

    @Test
    public void testBigDemical(){
        BigDecimal bigDecimal = new BigDecimal("550000000069");
        System.out.println("bigDecimal.toString() = " + bigDecimal.toString());
        System.out.println("bigDecimal.doubleValue() = " + bigDecimal.doubleValue());


    }
    @Test
    public void testPowerOfTwo(){
        HashMap demo = new HashMap<>();
        String key = "1";
        String value = "2";

        demo.put(key,value);

        System.out.println(1<<30);
    }

    /**
     *
     * base64 和文件互转
     */
    @Test
    public void testBase64() throws IOException {
        File file = new File("D:\\Code\\Idea\\javaBaseStudy\\java8\\src\\main\\java\\com\\lhp\\bean\\OrderBusiness.java");
        Path path = Paths.get("D:\\Code\\Idea\\javaBaseStudy\\java8\\src\\main\\java\\com\\lhp\\bean\\OrderBusiness.java");

        byte[] bytes = Files.readAllBytes(path);
       String base64 = Base64.getEncoder().encodeToString(bytes);
        byte[] bytesaa = Base64.getDecoder().decode(base64);


        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file1 = new File("D:\\33.java");
        fos = new FileOutputStream(file1);
        bos = new BufferedOutputStream(fos);
        bos.write(bytes);
        bos.close();
        fos.close();

    }
    @Test
    public void testUnicode8(){
        System.out.println("\u6e56\u5317\u6b66\u6c49");

        System.out.println(Integer.MAX_VALUE);    }

        @Test
        public void testRegex(){
            //正则
            // 仅限数字和字母
            String codePattern="/^[A-Za-z0-9]+$/";
            Pattern compile = Pattern.compile(codePattern);
            Matcher matcher = compile.matcher("5");

            System.out.println(matcher.matches());


            String   re="^[a-zA-Z0-9]{1,20}$";
            System.out.println("1LL1kk1".matches(re));


        }

}
