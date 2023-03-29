package sort;

/**
 * 归并排序
 * @author goodtime
 * @create 2020-02-05 8:21 下午
 */
public class MergeSort {
    public static int[] sort(int[] sort){
        int[] temp = new int[sort.length];
        return sort(sort,0,sort.length-1,temp);
    }


//分
    public static int[] sort(int[] origin,int head,int tail,int[] temp){
        if(tail-head == 0){
            return temp;
        }
        sort(origin,head,(head+tail)/2,temp);
        sort(origin,(head+tail)/2+1,tail,temp);
        merge(origin,head,tail,temp);
        return temp;
    }


//并
    public static void merge(int[] origin,int head,int tail,int[] temp){
        int middle = (head+tail)/2;
        int l = head;//temp的指针
        int i = head;//前一半的指针
        int j = middle+1;//后一半的指针

        //前半部分的值小，就前半部分赋值，后半部分等着，否则反过来

        while(i <= middle && j <= tail) {
            if(origin[i] <= origin[j]) {
                temp[l] = origin[i];
                i++;
                l++;
            } else {
                temp[l] = origin[j];
                j++;
                l++;
            }
        }
        //如果一半走到头，另一半还没有，说明剩下的都是另一半，直接往进赋值即可

        while(i <= middle && j > tail){
            temp[l++] = origin[i++];
        }

        while(j <= tail && i > middle){
            temp[l++] = origin[j++];
        }

        //把temp中排好序的部分传给origin数组中

        for (int m = head; i < l;i++) {
            origin[m] = temp[m];
        }
    }
}
