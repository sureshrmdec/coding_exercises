#include <stdio.h>
 
int atoi(char *str) {
    if (*str == NULL) return 0;
 
    int res = 0; 
    int sign = 1;
    int i = 0; 
 
    if (str[0] == '-') {
        sign = -1;
        i++; 
    }
 
    for (; str[i] != '\0'; ++i) {
        if (str[i] >= '0' && str[i] <= '9') {
          res = res*10 + str[i] - '0';
        }
    }
    return sign*res;
}
 
int main() {
    char str[] = "-134";
    int val = atoi(str);
    printf("%d\n", val);
    return 0;
}
