package Itertor;


import java.util.ArrayList;
import java.util.Iterator;


/**
 * @author goodtime
 * @create 2023-12-24 17:26
 */
public class NewContainer implements Iterable {

    private ArrayList<String> elements = new ArrayList<>();

    public NewContainer() {
    }

    public void add(String name) {
        elements.add(name);
    }

    public void remove(String name) {
        elements.remove(name);
    }


    @Override
    public Iterator iterator() {
        return new NewContainerIterator();
    }


    private class NewContainerIterator implements Iterator<String> {

        int count = 0;
        int size = elements.size();

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public String next() {
            return elements.get(count++);
        }
    }


}
