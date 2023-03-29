import java.util.Stack;

/**
 * @author goodtime
 * @create 2020-01-20 10:21 上午
 */
public class Number20 {
    public static void main(String[] args) {
        Solution20s solution20 = new Solution20s();
        solution20.push(3);
        solution20.push(4);
        solution20.push(2);
        solution20.push(3);
        solution20.pop();
        solution20.pop();
        System.out.println(solution20.min());
        solution20.pop();
        System.out.println(solution20.min());

    }
}

//造两个栈，一个栈有序，最上面是最小的。
class Solution20 {

    private Stack<Integer> stack = new Stack<Integer>();
    private Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack.push(node);
        if (stack2.isEmpty()) {
            stack2.push(node);
            return;
        }
        copyAndSort(stack, stack2);
    }

    public void pop() {
        stack.pop();
        copyAndSort(stack, stack2);
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return stack2.peek();
    }

    //  封装了一下算法,不过封装后比封装前多耗了10ms，内存也多了几k
    private void copyAndSort(Stack<Integer> stack, Stack<Integer> stack2) {
        int count = stack.size();
        int[] a = new int[count];
        stack2.removeAllElements();
        //      冒泡排序，序号越小值越大
        for (int i = 0; i < count; i++) {
            a[i] = stack.pop();
        }
        for (int k = count - 1; k >= 0; k--) {
            stack.push(a[k]);
        }
        for (int i = 0; i < count - 1; i++) {
            for (int j = i + 1; j < count; j++) {
                if (a[i] < a[j]) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
        for (int l = 0; l < count; l++) {
            stack2.push(a[l]);
        }
    }
}

//造两个栈，一个栈有序，最上面是最小的。
class Solution20s {

    private Stack<Integer> stack = new Stack<Integer>();
    private Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack.push(node);
        sortPush(stack2, node);
    }

    public void pop() {
        Integer node = stack.pop();
        sortPop(stack2, node);
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return stack2.peek();
    }

    void sortPush(Stack<Integer> stack, int node) {

        int size = stack.size();

        if (size == 0) {
            stack.push(node);
            return;
        }

        int[] tmp = new int[size];

        for (int i = stack.size() - 1; i >= 0; i--) {
            tmp[i] = stack.pop();
        }

        int flag = 0;

        for (int i = 0; i <= size - 1; i++) {
            if (tmp[i] <= node && flag == 0) {
                stack.push(node);
                flag = 1;
            }
            stack.push(tmp[i]);
        }

        if (flag == 0) {
            stack.push(node);
        }
    }

    private void sortPop(Stack<Integer> stack, Integer node) {

        int size = stack.size();

        int[] tmp = new int[size];

        for (int i = stack.size() - 1; i >= 0; i--) {
            tmp[i] = stack.pop();
        }

        int flag = 0;

        for (int i = 0; i <= size - 1; i++) {

            if (tmp[i] == node && flag == 0) {
                flag = 1;
            } else {
                stack.push(tmp[i]);
            }
        }
    }

}


