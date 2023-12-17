package leetcode.src.main.java.heap;

import java.util.PriorityQueue;

/**
 * https://leetcode.cn/problems/find-median-from-data-stream/
 *
 * @author goodtime
 * @create 2023-12-16 19:38
 */
public class Number295 {
    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(40);
        medianFinder.addNum(12);
        medianFinder.addNum(16);
        System.out.println(medianFinder.findMedian());
    }
}

/**
 * 尝试用优先队列实现，也即分别用大小堆来记录离中位数最近的两个数
 */
class MedianFinder {

    //大顶堆，小于等于中位数的数
    PriorityQueue<Integer> bigQueue;
    //小顶堆,大于中位数的数
    PriorityQueue<Integer> smallQueue;
    int size = 0;


    public MedianFinder() {
        bigQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        smallQueue = new PriorityQueue<>();
    }

    //最好举例与画图来确定其中的逻辑
    public void addNum(int num) {
        if (bigQueue.size() == 0) {
            bigQueue.offer(num);
        } else {
            if (num > bigQueue.peek()) {
                if (smallQueue.size() < bigQueue.size()) {
                    smallQueue.offer(num);
                } else if (smallQueue.peek() < num) {
                    bigQueue.offer(smallQueue.poll());
                    smallQueue.offer(num);
                } else {
                    bigQueue.offer(num);
                }

            } else {
                if (bigQueue.size() > smallQueue.size()) {
                    smallQueue.offer(bigQueue.poll());
                }
                bigQueue.offer(num);

            }
        }

        size++;
    }

    public double findMedian() {
        if (size % 2 == 0) {
            return (bigQueue.peek() + smallQueue.peek()) / 2.0;
        } else {
            return bigQueue.peek();
        }
    }
}
