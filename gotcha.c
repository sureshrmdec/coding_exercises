// https://hackernoon.com/so-you-think-you-know-c-8d4e2cd6f6a6#.d043xueld
struct R{
    int i;
} r;

struct S{
    int i;
      char c;
} s; 

struct T{
      char c;
} t;

main(){
    printf("%ld\n", sizeof(*(&r)));
    printf("%ld\n", sizeof(*(&s)));
    printf("%ld\n", sizeof(*(&t))); 
    printf("\n");

    char a = 0;
    short int b = 0;
    printf("%ld\n", sizeof(a+b));
    printf("%d\n", sizeof(b));
    printf("%d\n", sizeof(b) == sizeof(a+b)); 

    printf("\n");
    for (int i=0; i<=13; i++) {
      char d = ' ' * i;
      printf("%d * %d = %d\n", i, ' ', d);
    }
    printf("\n");
    int i = 16;
    printf("%ld\n", i >= i);
    printf("%ld\n", 1 << i);
    printf("%ld\n", 65536 >> i);
    printf("%ld\n", (((((i >= i) << i) >> i) <= i))); 

    printf("\n");
    i = 0;
    printf("%ld\n", i++ + i);

    int j = 0;
    printf("%ld\n", j++ + ++j);
}
