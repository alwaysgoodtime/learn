/**
 * @author goodtime
 * @create 2020-03-12 4:15 下午
 */
package interview2;

import com.sun.org.apache.xml.internal.res.XMLErrorResources_tr;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TestAddress {
    public static void main(String[] args) {
        Node[] a = new Node[10];
        Node first = new Node(1);
        Node second = new Node(2);
        first.setNext(second);

        Node next = first.next;

        first.next = a[0];
        a[0] = first;
        first = next;

        System.out.println(first);

        System.out.println(a[0].toString());

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();

    }


    static class Node {
        int val = 1;
        Node next;

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node(int val) {
            this.val = val;
        }


        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}
