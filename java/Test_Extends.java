/*
2019年07月09日15:35:05
功能：测试继承和重写
目的：掌握extends、super
*/


class Human//大驼峰命名法，类名中的每个英文单词首字母大写
{
    private int age;
    private String name;

    public Human()//一定别忘记写，无参的一定要有，方便后续赋值，也方便继承的时候写构造函数不发生错误
    {
    }

    public Human(int age, String name)//构造函数不能写成public void Human（），一定不能写返回类型为空。
	/*
	原因：    “构造函数是一种很特殊的函数，因为他没有返回值。这和‘返回值为void’有极大的差别。返回void时，一般函数并不返回任何东西，但是一般的
	函数能够选择是否要返回些什么东西。构造函数则绝对不返回任何东西，
	而且你也没有任何选择。如果它有一个返回值，而且你有权利选择你自己的返回型别（return type），
	编译器势必得通过某种方式来知道如何处理那个返回值。” —— thinking in Java
	*/ {
        this.age = age;
        this.name = name;
    }

    public String GetInfo() {
        String Info = age + ":" + name;//name是string类型，age也自动变成了字符串和name相加。
        return Info;
    }
}

class Student extends Human {
    public String school;

    public Student(int age, String name, String school) {
        // this.name = name;//当age和name在Human中是private时，不能直接访问，或者说,
        // this.age = age;//物理继承了，但是逻辑没有继承。
        super(age, name);
        this.school = school;

        //如果想照下面这样写，name和age首先不能是private，第二，要有public Human（）这个构造函数，
        //没有这个构造函数，Student这个构造函数会自动默认第一行为super（），从而报错。
        // this.name = name;
        // this.age = age;
        // this.school = school;
    }

    @Override
	public String GetInfo() {
        String Info = super.GetInfo() + ":" + this.school;
        return Info;
    }
    //这里重写了父类Human GetInfo（）方法，
    //从此例也可看出，在子类中有办法可以隐形地访问到父类中私有的数据成员。

}

class Test_Extends {
    public static void main(String[] args) {
        Student a = new Student(22, "goodtime", "哈哈大学");
        System.out.printf("%s\n", a.GetInfo());
    }
}
/*
------------------------------------------------------------
输出结果为：22:goodtime:哈哈大学
------------------------------------------------------------
*/