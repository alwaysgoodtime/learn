import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.*;

/**
 * 深拷贝第二种方法：序列反序列化
 * @author goodtime
 * @create 2020-03-07 7:23 下午
 */
public class Clone2 {
        public static void main(String[] args) throws CloneNotSupportedException {
            S a = new S();
            ByteArrayInputStream bai = null;
            ObjectInputStream oi = null;
            ByteArrayOutputStream bs = null;
            ObjectOutputStream oo = null;


            try{
                bs = new ByteArrayOutputStream();
                oo = new ObjectOutputStream(bs);//包装流

                oo.writeObject(a);//先把对象输出，再输入后按对象得到，这样就完成了深拷贝，而且不用重写clone方法

                bai = new ByteArrayInputStream(bs.toByteArray());//输入
                oi = new ObjectInputStream(bai);
                S o = (S)oi.readObject();

                System.out.println(a == o);
                System.out.println(a.toString());
                System.out.println(o.toString());


            }catch (Exception e){
            }finally {
                try {
                    oi.close();
                    bai.close();
                    bs.close();
                    oo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


    }

    class S implements Serializable {
        int a = 1;
        String b = "ss";
        String c = new String("ss");
        //    A d = new A();//克隆方法不要克隆自己，否则会出现StackOverflow
        Integer e = new Integer(1222);
        BS f = new BS();

        @Override
        protected S clone() throws CloneNotSupportedException {
            Object clone = super.clone();
            return (S)clone;
        }


        @Override
        public String toString() {
            return "S{" +
                    "a=" + a +
                    ", b='" + b + '\'' +
                    ", c='" + c + '\'' +
                    ", e=" + e +
                    ", f=" + f +
                    '}';
        }
    }

    class BS implements Serializable{
        int a = 1;
    }
