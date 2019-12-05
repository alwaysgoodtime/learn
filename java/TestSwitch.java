class TestSwitch{
	public static void main(String[] args) {
		int i = 3;
		int k = 2;
		switch(i){
			case 3:
				i++;
				i++;
				i++;
				break;
			case 4:
				i--;
				i--;
			case 5:
				i--;
			// case 1+3: ; 
			// 这样写是错误的

		}
		// system.out.println(i);
		System.out.println(i = k);
		// 先赋值后输出i
		System.out.println(i == k);
		// 输出（i == k ）这个条件句的boolean值

	}
}