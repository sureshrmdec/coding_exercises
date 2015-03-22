/* 
 * http://cs-fundamentals.com/tech-interview/c/c-program-to-count-number-of-ones-in-unsigned-integer.php
 * Hamming weight of a string is the number of symbols that are different from 
 * the zero-symbol of the * alphabet used. For a typical case consider a string of 
 * bits and count the * number of 1's in the string. In this binary case, 
 * it is also called the * population count. It is the digit sum of the 
 * binary representation of * a given number. Here, we will look into the 
 * available solutions and will * compare there running time to pick the fastest one.
 *
 */

#include <stdio.h>
#include <stdlib.h>

int naiveCountSetBits(unsigned int n) {
  unsigned int count = 0;
  while(n) {
    count += n & 1;
    n >>= 1;
  }
  return count;
}

/* Brian Kernighan’s Algorithm */
int bkCountSetBits(int n) {
    unsigned int count = 0;
    while (n) {
      n &= (n-1) ;
      count++;
    }
    return count;
}

/*
 * Count Set Bits by Divide and Conquer Strategy
 * By divide and conquer technique of counting set bits we have to first set
 * each 2-bit field equal to the sum of the two single bits that were
 * originally in the field, and then sum adjacent 2-bit fields, putting the
 * results in each 4-bit field, and so on. 
 */ 

int dqCountSetBits(unsigned int n)
{
  n = n - ((n >> 1) & 0x55555555);
  n = (n & 0x33333333) + ((n >> 2) & 0x33333333);
  n = (n + (n >> 4)) & 0x0F0F0F0F;
  n = n + (n >> 8);
  n = n + (n >> 16);
  return n & 0x0000003F;
}


int main(int argc,char *argv[]) {
  int i = 10;
  if (argc > 1) {
    i = atoi(argv[1]);
  }
  printf("%d\n", naiveCountSetBits(i));
  printf("%d\n", bkCountSetBits(i));
  printf("%d\n", dqCountSetBits(i));
  return 0;
}

