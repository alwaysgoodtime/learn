class TestWhile{
	public static void main(String[] args) {
		// int i = 1;
		// while(i < 0){
		// 	i--;

		// }
		// System.out.println(i);
		// 上面这样写ok，输出1
		/*
		int i = 1;
		
		while(i < 0){
			int k = 1;
			i--;

		}
		System.out.println(k);
		*/
		// 这样写不行，只要是while循环里才定义变量，在while循环
		// 外就失效了


		
		// if(boolean i = false){
		// 	int k = 1;
		// 	i = false;

		// }
		// System.out.println(i);
		// 这样写也不行，因为i这个变量在if中才使用，出了条件判断，i这个变量就不起作用了。

		

		// int i = 1;
		// 	{
		// 		int i = 2;
		// 	}
		// 这种代码块中变量名的重新使用，在c和c++中可以，但在java中不行（作用域冲突时没有优先级判定）

		// int i = 1;
		// for (int i = 2; i < 3; i++){

		// }
		// 这样也是不行的


		
		for (int i = 2; i < 3; i++){

		}

		int i = 1;
		

		{
			int j = 1;
		}

		int j = 1;
		// 这样是可以的，{}里的i和j是个代码块级变量，它和另一个局部变量的作用域不产生冲突就可以。
		// 一个类的成员变量也可以和方法变量同名，不过方法中默认都是方法变量。


	}
}