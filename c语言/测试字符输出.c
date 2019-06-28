/*
2019年06月28日12:18:14
目的：测试字符输出
*/
#include <stdio.h>
#include <stdlib.h>

struct c
{
    char name[100];
};

int main(void)
{

	//c代表数组中第一个元素的地址。

	int c[10] = {1,3,4};

	printf("%d\n",*c);

	//字符数组输出直接用数组名

    struct c d;

    scanf("%s",d.name);

    printf("%s\n",d.name);


    //字符串、字符数组test

	char greeting[] = {'H', 'e', 'l', 'l', 'o','\0'};//字符串，也是字符数组
 	char hello_1[] = {'H', 'e', 'l', 'l', 'o','d'};//会多执行一次，字符数组，不是字符串
	char hello_2[] = {"Hello"};
	char hello_3[] = {"hello\0"};
	char hello_4[] = {'H', 'e', 'l', 'l', 'o','0'};//还是字符数组，不是字符串
 	char hello_5[] = {'H', 'e', 'l', 'l', 'o',0};//是字符串
	char *str[] = {"aaaaab","bdsafc","asdcd","ed","sdfasdf","sdfsdf"};//通过一维数组指针实现字符串数组的存储。
	//这是个指针组成的一维数组，每个元素存放的是字符串第一个字母的首地址。
	char **ss;
	int *p;
	char *s ={"abcde"};//s存放的是a的地址。

 	printf("Greeting message: %s\n", greeting);
 	printf("Greeting message: %s\n",hello_1);//输出整个字符数组
	printf("Greeting message: %c\n",hello_1[5]);//输出特定字符,注意，单个字符不能用%s输出，必须是%c
	//printf("Greeting message: %s\n",hello_1[5])//error
	printf("Greeting message: %s\n",hello_2);
	printf("Greeting message: %d\n",hello_2[5]);//'/o'在ascii中的编码是整数0；
	printf("Greeting message: %s\n",hello_3);
	printf("Greeting message: %c\n",hello_3[1]);
	printf("Greeting message: %s\n",hello_4);
	printf("Greeting message: %s\n",hello_5);
	printf("%d\n",s[5]);//s也是个字符数组
	printf("%d\n",s[0]);//a的ascii编码是97
	printf("%c\n",s[0]+1);//输出b
	printf("%ld %ld %ld %ld %ld %ld\n",sizeof(greeting),sizeof(hello_1),sizeof(hello_2),sizeof(hello_3),sizeof(hello_4),sizeof(hello_5));
	printf("%ld\n",sizeof(str));//str是一个指针数组，每个元素是一个指向字符串首字母地址的指针，共有6个指针，占48字节。
	printf("%ld\n",sizeof(ss));//不同数据类型的指针，所占字节数相同。
	printf("%ld\n",sizeof(p));//现在地址是64位了，指针占8个字节。
	printf("%s\n",*(str+2));
	printf("%s\n",*str);
	printf("%s\n",(*str)+2);//注意两者输出的区别
	return 0;
}

/*
输入是
------------------------------------------
sdf
------------------------------------------
输出结果是
------------------------------------------
1
sdf//此为输入，先输出1后输出。
sdf
Greeting message: Hello
Greeting message: HellodHello
Greeting message: d
Greeting message: Hello
Greeting message: 0
Greeting message: hello
Greeting message: e
Greeting message: Hello0hello
Greeting message: Hello
0
97
b
6 6 6 7 6 6
48
8
8
asdcd
aaaaab
aaab
------------------------------------------


知识点：1:
			1>
			char str[]="Hello World!";
			2>
			char *str="Hello World!";
			3>
			char str[100];
			strcpy(str, "Hello World!");
			如果要定义一个常量字符串树组，建议用1>, 2>
			如果要定义一个字符串树组，建议用3>

困惑：数组名就是数组第一个元素的首地址，所以对于a[10],输出第n个元素时要用*（a+n-1），或者a[n-1].
	 字符数组的数组名b[5]好像是整个字符数组第一个元素的首地址，但输出的时候，直接写b就能全部输出。
尝试解释：因为输出的时候用的是%s，这样直接通过b这个指针地址，就把数组中的元素一次性都取出来了。
在取数组中具体元素的时候，和一般数组的方式是一模一样的。
*/