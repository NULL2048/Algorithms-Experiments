import java.util.Scanner;

public class DeleteNumber {
    /**
     * 从高位到低位看，如果高位大于低位，那么删掉高位的数：175438,1<7，不管；7>5，删掉7；15438，5>4,删5；
     * 高位数越大，这个数就越大，所以我们要从高位来看，高位和下一位比较；
     * 如果是递增的数，那么就删最后一位；位数变少，数字也会比删其他的要少；例如：123456；
     * @param args
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String n = scan.next();
        // 将要操作的数转化为数组，方便删数
        char[] num = n.toCharArray();
        // 删除k个数
        int k = scan.nextInt();

        int len = n.length();

        // 删除k个数
        for (int i = 0; i < k; i++) {
            // 遍历剩余的数字，查找当前位和下一位哪个数字大，当前数字j是高位，如果高位数字比低位数字大，就将当前高位数字删掉。因为如果当前高位数字大，最后生成的数就大，应该让低位更小的数字取代他的位置，如果高位数字小的话，就不用管了，因为低位比高位数大，让低位数跑到高位的话反而会使数变得更大
            for (int j = 0; j < len - 1; j++) {
                // 删除当前高位数字
                if (num[j] > num[j + 1]) {
                    for (int l = j; l < len - 1; l++) {
                        num[l] = num[l + 1];
                    }
                    // 删完数直接跳出找数删数的循环
                    break;
                }
            }
            // 如果都是递增的数，直接将最小位删除就行了，因为高位都是小数，也就是将长度减一
            len--;
        }
        int i = 0;
        // 找到剩余序列中第一个不为0的数，从这个数开始输出
        while (i <= len - 1 && num[i] == '0') {
            i++;
        }

        // 如果都是0，说明删数最后结果就是0
        if (i == len) {
            System.out.println(0);
        // 否则直接输出
        } else {
            for (int j = i; j < len; j++) {
                System.out.print(num[j]);
            }
        }
    }
}
/*
178543
4
 */