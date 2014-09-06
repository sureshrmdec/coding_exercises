#include <stdio.h>
 
int string_length(char *pointer) {
   int c = 0;
 
   while( *(pointer+c) != '\0' ) c++;
   return c;
} 

void reverse(char *string) {
   int length, c;
   char *begin, *end, temp;
 
   length = string_length(string);
 
   begin = string;
   end = string;
 
   for ( c = 0 ; c < ( length - 1 ) ; c++ ) end++;
 
   for ( c = 0 ; c < length/2 ; c++ ) {        
      temp = *end;
      *end = *begin;
      *begin = temp;
 
      begin++;
      end--;
   }
}
 
char* itoa(int num, char* str) {
    int i = 0;
    int isNegative = 0;
 
    if (num == 0) {
        str[i++] = '0';
        str[i] = '\0';
        return str;
    }
 
    if (num < 0) {
        isNegative = 1;
        num = -num;
    }
 
    while (num != 0) {
        int rem = num % 10;
        str[i++] = rem + '0';
        num = num/10;
    }
 
    if (isNegative) str[i++] = '-';
    str[i] = '\0';
    reverse(str);
    return str;
}


int main() {
    char str[256];
    char *val = itoa(1234, str);
    printf("%s\n", val);
    return 0;
}
