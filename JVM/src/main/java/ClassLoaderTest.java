import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author goodtime
 * @create 2024-01-22 21:12
 */
public class ClassLoaderTest extends URLClassLoader {


    public ClassLoaderTest(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }


}
