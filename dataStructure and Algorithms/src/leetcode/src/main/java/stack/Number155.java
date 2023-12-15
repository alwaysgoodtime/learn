package leetcode.src.main.java.stack;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/min-stack/
 *
 * @author goodtime
 * @create 2023-12-02 14:45
 */
public class Number155 {

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(3);
        minStack.push(2);
        minStack.push(10);
        minStack.push(12);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
    }
}

/**
 * 设置辅助栈
 * 辅助栈不能是按照大小顺序排列的栈，对于push和pop函数耗时太多
 * 辅助栈放的是每个元素入栈后，当前栈的最小元素
 */
class MinStack {

    private Stack<Integer> stack = new Stack();
    private Stack<Integer> minStack = new Stack();

    public MinStack() {
    }

    public void push(int val) {

        if (stack.size() == 0) {
            minStack.push(val);
        } else {
            minStack.push(Math.min(minStack.peek(), val));
        }

        stack.push(val);

    }

    public void pop() {

        stack.pop();
        minStack.pop();

    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}

