package main

import "fmt" //fmt 包实现了格式化 IO（输入/输出）的函数

// 声明了两个常量
const LENGTH int = 10
const WIDTH int = 5

// 枚举
const (
	Unknown = 0
	Female  = 1
	Male    = 2
)

func init() {
	fmt.Println("init----")

}

//入口函数  有init方法 会先执行init
func main() {
	fmt.Println("Hello, World!") //可以没有 分号;
	format()
	dataType()
	defaultValue()
}

//格式化
func format() {
	// %d 表示整型数字，%s 表示字符串
	var stockcode = 123
	var enddate = "2020-12-31"
	var url = "Code=%d &endDate= %s"
	var target_url = fmt.Sprintf(url, stockcode, enddate) //Code=123 &endDate= 2020-12-31
	fmt.Println(target_url)
}

//
func dataType() {
	var flag bool = true

	var intd int = 120
	var intd8 int8 = 120
	fmt.Println(flag, intd, intd8)

	//可以一次声明多个变量：
	var b, c int = 1, 2
	fmt.Println(b, c)

	intVal3 := 1
	//等价于
	var intVal4 int
	intVal4 = 1

	fmt.Println(intVal3, intVal4)

	//--------

	f := "Runoob" // var f string = "Runoob"
	fmt.Println(f)

}

//默认值
func defaultValue() {
	var i int
	var f float64
	var b bool
	var s string
	fmt.Printf("%v %v %v %q\n", i, f, b, s)
	//0 0 false ""
}

//枚举
func enumTest() {

}
