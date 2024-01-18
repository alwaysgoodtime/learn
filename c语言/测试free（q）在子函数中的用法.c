/*
2019年06月27日10:48:18
目的：测试free（q）在子函数中的用法
*/
#include <stdio.h>
#include <stdlib.h>

void g(int *p) {
    *p = 20;
    free(p);
}

int main(void) {
    int *q = (int *) malloc(sizeof(int));

    *q = 10;
    printf("%d\n", *q);
    g(q);
    printf("%d\n", *q);
    free(q);
    printf("%d\n", *q);
    return 0;
}

/*
输出结果是
------------------------------------------
10
20
求1到100奇数偶数的问题 2(11665,0x10a1025c0) malloc: *** error for object 0x7fd7e1500aa0: pointer being freed was not allocated
求1到100奇数偶数的问题 2(11665,0x10a1025c0) malloc: *** set a breakpoint in malloc_error_break to debug
Abort trap: 6
------------------------------------------

总结：在这个版本中，free(p)在子函数中生效，不会影响其实际参数q所指向的内存。

*/