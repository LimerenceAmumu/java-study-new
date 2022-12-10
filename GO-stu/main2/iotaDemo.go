package main

import "fmt"

//第一个 iota 等于 0，每当 iota 在新的一行被使用时，它的值都会自动加 1
const (
	a = iota
	b = iota
	c = iota
)

func main() {
	fmt.Println(a, b, c)
}
