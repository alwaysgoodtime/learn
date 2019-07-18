/*
2019年07月03日09:49:16
功能：用循环和递归求n！
目的：练习递归
*/
#include "stdio.h"

// int f(int j)
// {
// 	int k=1;
// 	for (int i = 1; i <= j; i++)
// 		k=k*i;

// 	return k;
// };  //f函数的循环实现

int f(int j)
{
	return j==1?1:f(j-1)*j; //三目运算符
	// if (j == 1)
	// 	return 1；
	// else 
	// 	return f(j-1)*j;
}

int main (void)
{
	int i;

	printf("请输入要求的阶乘数");
	scanf("%d",&i);

	printf("i的阶乘 = %d\n",f(i));


	return 0;
}

/*
输入 
--------------
5
--------------
输出
----------------------
i的阶乘 = 120
----------------------

总结：递归就是函数直接或间接地调用自己。
*/