package leetcode.src.main.java.array;

/**
 * https://leetcode.cn/problems/integer-to-roman/description/
 * @author goodtime
 * @create 2023-12-04 02:49
 */
public class Number12 {
    public static void main(String[] args) {
        System.out.println(new Solution12().intToRoman(1994));
    }
}

/**
 * @see Number13
 */
class Solution12 {
    public String intToRoman(int num) {

        StringBuilder romanNumber = new StringBuilder();

        while (num != 0) {

            if (num >= 1000) {
                romanNumber.append("M");
                num -= 1000;
            } else if (num >= 900){
                romanNumber.append("CM");
                num -= 900;
            }else if(num >= 500){
                romanNumber.append("D");
                num -= 500;
            }else if(num >= 400){
                romanNumber.append("CD");
                num -= 400;
            }else if(num >= 100) {
                romanNumber.append("C");
                num -= 100;
            }else if(num >= 90){
                romanNumber.append("XC");
                num -= 90;
            }else if(num >= 50){
                romanNumber.append("L");
                num -= 50;
            }else if(num >= 40){
                romanNumber.append("XL");
                num -= 40;
            }else if(num >= 10){
                romanNumber.append("X");
                num -= 10;
            }else if(num >= 9){
                romanNumber.append("IX");
                num -= 9;
            }else if(num >= 5){
                romanNumber.append("V");
                num -= 5;
            }else if(num >= 4){
                romanNumber.append("IV");
                num -= 4;
            }else {
                romanNumber.append("I");
                num -= 1;
            }
        }

        return romanNumber.toString();
    }

}
