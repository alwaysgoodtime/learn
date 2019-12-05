/*
  三种注释方式：单行注释 多行注释 文本注释（java特有）
  
*/
  // java编译后出来的是类的名字，而非java文件本身的名字
  // 注释语言不参与编译，换句话说，编译后生成的.class字节码文件不包含注释掉的信息
  // 但注意:前端最后要删去注释，会影响网页加载速度

/*
1.在一个java源文件名可以声明多个class，但是，只能最多一个类声明为public的。
而且要求声明为public的类的类名必须与源文件名相同。

2.程序的入口是main（）方法，格式是固定的。

3.输出语句

System.out.println(); 先输出数据，然后换行
system.out.print(); 只输出数据

4.每一句执行语句都以;结尾 多个语句可以写一行，但不便于阅读

5.编译后，有几个class，就生成几个字节码文件，不过只有写main方法的能运行
*/

class First{
	// java类名也有命名规则，不合法的类名会报错缺少标识符
	// 单行注释：如下的main方法是程序的入口！
	// args 是单词arguments（参数）的缩写
	public static void main(String[] args)
		{
		float text = 12.3f;
		float myNumber = 134423420f;
		int i = 0123;
		int g = 10;
		g =  g * ++g; 
		System.out.println(i);
		System.out.println(g);
		System.out.println(myNumber);// 只用于换行
	}
	// public static void main(String a[])
	// public static void main(String [] a)
	// 这是声明唯一能变的两个地方

}

class A23{

}

