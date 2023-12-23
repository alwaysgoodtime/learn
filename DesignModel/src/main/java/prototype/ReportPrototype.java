package prototype;

/**
 * @author goodtime
 * @create 2023-12-23 03:46
 */
public class ReportPrototype implements Prototype {

    String content;
    String auther;

    public ReportPrototype() {
    }

    public ReportPrototype(String content, String auther) {
        this.content = content;
        this.auther = auther;
    }

    public void write() {
        content = "这周我拯救了世界";
        auther = "goodtime";
    }


    @Override
    public Prototype copy() {
        return new ReportPrototype(content,auther);
    }

    @Override
    public String toString() {
        return "ReportPrototype{" +
                "content='" + content + '\'' +
                ", auther='" + auther + '\'' +
                '}';
    }
}
