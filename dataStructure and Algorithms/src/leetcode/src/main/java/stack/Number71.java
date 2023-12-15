package leetcode.src.main.java.stack;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/simplify-path/
 *
 * @author goodtime
 * @create 2023-12-02 15:17
 */
public class Number71 {
    public static void main(String[] args) {
        System.out.println(new Solution71().simplifyPath("/../..ga/b/.f..d/..../e.baaeeh./.a"));
    }
}

/**
 * 用栈对其进行简化，核心是如何用栈来模拟简化过程的逻辑
 *
 * 也可以用ArrayDeque这种双端队列处理，用"/"分割原字符串，看两个"/"之间的内容来决定如何从前到后简化它，其中每个元素存储的是一级别目录名，从前到后append"/"
 * 转字符串即可
 *
 */
class Solution71 {
    public String simplifyPath(String path) {

        if (path == null || path.length() == 0) {
            return path;
        }

        StringBuilder simplifyPath = new StringBuilder();

        Stack<Character> stack = new Stack();

        for (int i = 0; i < path.length(); i++) {

            char c = path.charAt(i);

            //1.如果什么都没有，就加入它
            if (stack.size() == 0) {
                stack.push(c);
            } else if (c == '/') {
                //2.如果字符是'/'且前面一个字符是'/'，省略它;
                if (stack.peek() != '/') {
                    stack.push(c);
                }
            } else if (c == '.') {
                //3.字符为'.'，
                if (stack.peek() != '/') {
                    //前面只要不是/，就是三个及以上的情况
                    stack.push(c);
                } else if (i + 1 == path.length() || path.charAt(i + 1) == '/') {
                    //前面没有.,那么看其后面有几个'.' 如果是1个.，那么直接省略；
                    continue;
                } else if (i + 2 == path.length() || path.charAt(i + 2) == '/') {

                    //类似'.x'的目录名
                    if (path.charAt(i + 1) != '.') {
                        stack.push(c);
                    } else if (stack.size() != 1){
                        // 如果是两个..，除了根目录的情况，其他情况都删除前一个/字符及上级目录名，两个点省略
                        stack.pop();
                        while (stack.peek() != '/') {
                            stack.pop();
                        }
                    } else {

                    }
                } else {
                    // 三个点的情况
                    stack.push(c);
                }

            } else {
                stack.push(c);
            }

        }

        //如果最后一个字符是'/'，且前面有字符，省略它
        if(stack.size() != 1 && stack.peek() == '/'){
            stack.pop();
        }

        int size = stack.size();

        for (int j = 0; j < size; j++) {
            simplifyPath.append(stack.pop());
        }

        return simplifyPath.reverse().toString();


    }
}
