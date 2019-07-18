/*
2019年07月14日18:07:24
目的：练习awt中布局管理器的嵌套
功能：输出郝斌老师的作业
*/

import java.awt.*;

public class Test_awt
{
	public static void main(String[] args)
	{
		Frame f = new Frame("郝斌设计的十次按钮");
		Panel p1 = new Panel(); 
		Panel p2 = new Panel();
		Panel p3 = new Panel();
		Panel p4 = new Panel();//创建所需的容器

		Button bn1 = new Button("Button1");
		Button bn2 = new Button("Button2");
		Button bn3 = new Button("Button3");
		Button bn4 = new Button("Button4");
		Button bn5 = new Button("Button5");
		Button bn6 = new Button("Button6");
		Button bn7 = new Button("Button7");
		Button bn8 = new Button("Button8");
		Button bn9 = new Button("Button9");
		Button bn10 = new Button("Button10");//创建所需的组件


		f.setLayout(new GridLayout(2,1));
		f.add(p1);
		f.add(p2);
		p3.setLayout(new GridLayout(2,1));
		p4.setLayout(new GridLayout(2,2));
		p1.setLayout(new BorderLayout());
		p2.setLayout(new BorderLayout());//设定每个容器的布局，初步确定嵌套关系
										 //Frame默认为BordereLayout布局管理器，
										 //Panel默认为FlowLayout布局管理器。
		p1.add(p3,BorderLayout.CENTER);
		p2.add(p4,BorderLayout.CENTER);
		p1.add(bn1,BorderLayout.WEST);
		p1.add(bn2, BorderLayout.EAST);
		p2.add(bn5,BorderLayout.WEST);
		p2.add(bn6,BorderLayout.EAST);
		p3.add(bn3);
		p3.add(bn4);
		p4.add(bn7);
		p4.add(bn8);
		p4.add(bn9);
		p4.add(bn10);
		f.pack();
		// f.setSize(200, 200);
		f.setVisible(true);//将按钮放到组件中，注意：这一步不能和前一步调换顺序，
							//因为容器有默认的布局管理器，如果调换顺序，就用默认的输出了

	}
}