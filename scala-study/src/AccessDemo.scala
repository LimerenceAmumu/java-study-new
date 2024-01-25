package com.lhp


// 访问修饰
Object AccessDemod {

  def main(args: Array[String]): Unit = {

  }


  private var a = 10;

  private def funDemo(): Unit = {
    println("AccessDemo")
  }

  class InnerClass {
    private var b = 10;
    funDemo(); // 内部可以访问外部私有

    private val clazz = new InnInnerClass()
    //    clazz.funDemo2(); //无法从此处访问符号 funDemo2
    clazz.funDemoPub();

    class InnInnerClass {
      private def funDemo2(): Unit = {
        println("InnInnerClass")
      }

      def funDemoPub(): Unit = {
        println("InnInnerClass")
      }

      private var c = 10;

    }
  }
}


