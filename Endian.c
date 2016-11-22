#include <stdio.h>

typedef enum tagEndian {
  BigEndian, LittleEndian
} Endian;

Endian byteOrder() {
  short int word = 0x0001;
  char *byte = (char *) &word;
  return (byte[0] ? LittleEndian : BigEndian);
}

int main() {
  printf("%d\n\n", byteOrder());
}

