package leetcode.src.main.java.doublePointer;

/**
 * https://leetcode.cn/problems/container-with-most-water/
 * @author goodtime
 * @create 2023-12-01 02:50
 */
public class Number11 {
    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(new Solution11().maxArea(height));
    }

}

/**
 * 类似接雨水，不过本题是用双指针，也是贪心算法的一种
 *
 * 理论：
 * 在每个状态下，无论长板或短板向中间收窄一格，都会导致水槽底边宽度变短：
 *
 * 若向内移动短板 ，水槽的短板 min(h[i],h[j])min(h[i], h[j])min(h[i],h[j]) 可能变大，因此下个水槽的面积可能增大 。
 * 若向内移动长板 ，水槽的短板 min(h[i],h[j])min(h[i], h[j])min(h[i],h[j])不变或变小，因此下个水槽的面积一定变小 。
 * 因此，初始化双指针分列水槽左右两端，循环每轮将短板向内移动一格，并更新面积最大值，直到两指针相遇时跳出；即可获得最大面积。
 *
 * 作者：Krahets
 * 链接：https://leetcode.cn/problems/container-with-most-water/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 */
class Solution11 {
    public int maxArea(int[] height) {

        int prePointer = 0;
        int postPointer = height.length - 1;
        int maxArea = 0;

        while (prePointer < postPointer) {

            int a1 = height[prePointer];
            int a2 = height[postPointer];

            int area = getArea(a1, a2, prePointer, postPointer);

            if (maxArea < area) {
                maxArea = area;
            }

            if (a1 <= a2) {
                prePointer++;
            } else {
                postPointer--;
            }

        }

        return maxArea;
    }

    int getArea(int a1, int a2, int i1, int i2) {
        int height = Math.min(a1, a2);
        return height * (i2 - i1);
    }
}
