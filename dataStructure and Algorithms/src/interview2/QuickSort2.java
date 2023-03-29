package interview2;

import java.util.Arrays;

/**
 * @author goodtime
 * @create 2020-02-21 8:33 下午
 */
public class QuickSort2 {
    public static void main(String[] args) {
        int[] a = {8,6,3,6,6,2,7,9};
        quicksort(a,0,a.length-1);
        System.out.println(Arrays.toString(a));
    }

    private static void quicksort(int[] a,int start,int end){
        if(start >= end){
            return;
        }
        int middle = a[start];
        int head = start;
        int tail = end;
        while (true){
            while( tail > head && a[tail] >= middle){
                tail--;
            }
            if(tail == head){
                a[tail] = middle;
                quicksort(a,start,tail-1);
                quicksort(a,tail+1,end);
                return;
            }
            a[head] = a[tail];
            head++;
            while(head < tail && a[head] < middle){
                head++;
            }
            if(head == tail){
                a[tail] = middle;
                quicksort(a,start,head-1);
                quicksort(a,head+1,end);
                return;
            }
            a[tail] = a[head];
            tail--;
        }
    }
}
