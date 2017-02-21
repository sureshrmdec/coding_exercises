package main

import (
    "fmt"
    "os"
    "strings"
)

func main() {
    for _, s := range os.Args[1:] {
        fmt.Printf("%s\n", basename(s))
    }
}

// convert a/b/c.go to c, c.d.go to c.d
func basename(s string) string {
    slash := strings.LastIndex(s, "/") // -1 if not found
    s = s[slash+1:]
    if dot := strings.LastIndex(s, "."); dot >= 0 {
        s = s[:dot]
    }
    return s
}
