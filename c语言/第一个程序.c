/*
2019年06月24日13:25:25
功能：输入两个正整数m和n，求出1-m中可以被n整除的数。
目的：输入两个正整数m和n，求出1-m中可以被n整除的数。
*/

#include <stdio.h>
int main(void)
{
	int m ;
	int n ;
	int count = 0;

	printf("请输入两个正整数m和n，以空格分隔，程序求出1-m中可以被n整除的数");
	scanf("%d%d",&m,&n);
	
	for (int i = 1; i <= m; ++i)
	{	
		if (i%n == 0)					
		{
			printf("%5d",i);
			count++;
		}
		if (count%10 == 0)
		{
			printf("\n");
		}
	}

	printf("\n");

	return 0;
}

/*
在GCC中的输出结果是
-------------------------------------------
请输入两个正整数m和n，以空格分隔，程序求出1-m中可以被n整除的数100 3


    3    6    9   12   15   18   21   24   27   30


   33   36   39   42   45   48   51   54   57   60


   63   66   69   72   75   78   81   84   87   90


   93   96   99
-------------------------------------------

总结：

本程序在mac平台上，用sublimetext3编写，GCC编译，后面的程序如不注明，编写环境都是如此。

可以在sublimetext中build system，加入下面这段话。

{
"shell_cmd" : "gcc $file_name -o ${file_base_name}",
"working_dir" : "$file_path",
"variants":
  [
    {
      "name": "Run",
      "shell_cmd": "gcc $file_name -o ${file_base_name} && ${file_path}/${file_base_name}"
    }
  ]
}

scanf函数无论"%d%d"中有没有空格，都默认是用空格分开两个数，加逗号不行。

*/