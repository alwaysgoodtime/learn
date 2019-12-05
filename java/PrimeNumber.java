/*
时间：2019年11月30日12:14:15
功能：输出100以内的所有质数
目的：练习


*/

// class PrimeNumber{
// 	public static void main(String[] args){
// 			int count = 1;
// 			for(int i = 2; i <= 100; i++){
// 			// int count = 1;这是让每次循环都创造一个新变量，也可以，省去重置，但每次运行
// 			// 内存都要重新分配一个变量地址和空间，运行效率慢了
// 				for(int j = 2; j < i; j++){
// 						if (i % j == 0) {
// 							count++;
// 						}
// 						if (count == 2){
// 							break;
// 						}
// 				}
// 						if (count == 1){
// 							System.out.println(i);
// 						}
// 						count = 1;


// 			}
// 	}
// }

//优化版
class PrimeNumber{
	public static void main(String[] args){
			long start = System.currentTimeMillis();
			// 获取当前时间距离1970-1-1 0时的毫秒数
			int count = 1;
			label: for(int i = 2; i <= 100; i++){
			// int count = 1;这是让每次循环都创造一个新变量，也可以，省去重置，但每次运行
			// 内存都要重新分配一个变量地址和空间，运行效率慢了
				for(int j = 2; j < Math.sqrt(i); j++){
					//  Math.sqrt(i)是质数的算法优化
						if (i %  j == 0) {
							count++;
						}
						if (count == 2){
							break;
							// 这里可以写 count = 1 continue label; 直接就结束外层的此次循环，不过记得先重置count，而且不能
							//写在continue后面。
						}
				}
						if (count == 1){
							System.out.println(i);
						}
						count = 1;
			// 获取当前时间距离1970-1-1 0时的毫秒数
			long end = System.currentTimeMillis();					
			System.out.println(end - start);
			}
	}
}
