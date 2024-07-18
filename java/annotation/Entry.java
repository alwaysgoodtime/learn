package annotation;

@DocumentAnno
@NoDocumented
public class Entry {
    /**
     * 测试@DocumentAnno注解,保留此信息
     */
    @DocumentAnno
    public static String name = "Entry";

    @DocumentAnno
    public static void main(String[] args) {
        System.out.println("test");
    }
}
