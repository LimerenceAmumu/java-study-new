package com.lhp

object HelloWorld {

  val KEY_PREFIX: String = "data_"
  var name: String = "Amumu";

  def main(args: Array[String]): Unit = {
    //    println("Hello, world!")
    name = "asd"; //
    main02(args);
    var a = 30;
    val i = sum(a, 1);
    println(i)
    println("---------")
    delayed(time());
  }

  def main02(args: Array[String]) {
    println("Hello\tWorld\n\n");
  }


  def sum(a: Int, b: Int): Int = {
    //a=22;//TODO  为什么不能赋值
    return a + b;
  }


  def time(): Long = {
    println("获取时间，单位为纳秒")
    val time1 = System.nanoTime
    println(time1)
    return time1;
  }

  def delayed(t: => Long) = { //传名调用  每次使用参数t 的时候都会执行一次   time()
    println("在 delayed 方法内")
    println("参数： " + t)
    t
  }
}