package main

import (
    "fmt"
)

func main() {
    fmt.Printf("%d\n", gcd(5, 15))
}

func gcd(x, y int) int {
    for y != 0 {
        x, y = y, x%y
    }
    return x
}
