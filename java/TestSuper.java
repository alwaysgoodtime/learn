class TestSuper{
	public static void main(String[] args){
		int i = 1;

	TestSon s = new TestSon();

	System.out.println(s.getI());
	System.out.println(s.j);

	}
}

class TestSup{
	int i = 1;
	int j = 1;
}

class TestSon extends TestSup{
	int i = 2;
	int j = 3;
	public int getI(){
	  return super.i;
	}
	public int getJ(){
	  return j;
	}
}