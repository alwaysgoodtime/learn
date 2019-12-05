class TextInstance{
	public static void main(String[] args) {

	Test2 a = new Test2();
	Test b = (Test)a;
	System.out.println( b instanceof Test );
		
	}

}

class Test{
	int i = 1;
}

class Test2 extends Test {
	int j = 1;

}