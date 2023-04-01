package 剑指0ffer.stackAndQueue;

import java.util.Stack;

/**
 * JZ9 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 * 要求：存储n个元素的空间复杂度为O(n) ，插入与删除的时间复杂度都是O(1)
 * @author goodtime
 * @create 2020-01-18 12:16 上午
 */
public class Number5 {
    public static void main(String[] args) {
        Solution5s solution5 = new Solution5s();
        solution5.push(1);
        solution5.push(2);
        solution5.push(3);
        solution5.push(4);
        System.out.println(solution5.pop());
        System.out.println(solution5.pop());
        solution5.push(5);
        solution5.push(6);
        solution5.push(7);

        int pop = solution5.pop();
        System.out.println(pop);
        System.out.println(solution5.pop());
        System.out.println(solution5.pop());
        System.out.println(solution5.pop());
        System.out.println(solution5.pop());
    }
}

class Solution5 {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    private int count = 0;

    //要求：存储n个元素的空间复杂度为O(n) ，插入与删除的时间复杂度都是O(1),在这种条件下，只有这种方法满足
    public void push(int node) {
        if (stack1.empty()) {
            stack1.push(node);
        } else {
            count++;
            for (int i = 0; i < count; i++) {
                stack2.push(stack1.pop());
            }
            stack1.push(node);
            for (int i = 0; i < count; i++) {
                stack1.push(stack2.pop());
            }
        }

    }

    public int pop() {
        count--;//如果不写，count会取值太多，造成空栈异常。
        return stack1.pop();
    }
}

class Solution5s {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    int count;

    public void push(int node) {
        stack1.push(node);
        count++;
    }

    /**
     * 注意count--和--count的区别
     */
    public int pop() {
        int i = count;
        while ((i--) != 0) {
            stack2.push(stack1.pop());
        }

        int val = stack2.pop();

        i = --count;

        while ((i--) != 0) {
            stack1.push(stack2.pop());
        }

        return val;

    }
}

/**
 * 栈是后入先出，队列是先入先出，用栈模拟队列，就是让另一个栈把先入的值伪装成后入的值
 * 下面这个方法是取巧，对栈进行了遍历
 */
class Solution5ss {

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();


    public void push(int val) {
        stack1.push(val);
        stack2.removeAllElements();
        for (int i = stack1.size() - 1; i >= 0; i--) {
            stack2.push(stack1.get(i));
        }
    }

    public int pop() {
        int result = stack2.pop();
        stack1.removeAllElements();
        for (int i = stack2.size() - 1; i >= 0; i--) {
            stack1.push(stack2.get(i));
        }
        return result;
    }


}