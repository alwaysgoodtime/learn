import java.util.Stack;

/**
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
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
    public void push(int node) {
        if(stack1.empty()){
        stack1.push(node);
        }else{
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
    Stack<Integer> stack1 = new Stack<Integer>();//真栈
    Stack<Integer> stack2 = new Stack<Integer>();//模拟队列
    int count;

    public void push(int node) {
        count++;
        stack1.push(node);
    }

    public int pop() {
        int i = count;
        while((i--)!= 0){
            stack2.push(stack1.pop());
        }
        i = count-1;
        Integer pop = stack2.pop();
        while((i--) != 0) {
            stack1.push(stack2.pop());
        }
        count--;
        return pop;
    }
}