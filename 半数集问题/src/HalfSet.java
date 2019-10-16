import java.util.Scanner;

public class HalfSet {
    // 半数集个数
    private static int n;
    // 存储要输出的半数集元素
    private static int[] num = null;
    // 标记num数组的下标
    private static int index = 0;
    // 记录半数集元素个数
    private static int cnt = 0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入半数集个数：");
        n = scan.nextInt();
        num = new int[n];

        halfSet(n);

        System.out.println("\n半数集个数为：" + cnt);
    }

    /**
     * 递归思想，效率比较慢，不如动规快
     * @param l
     */
    public static void halfSet(int l) {
        // 将当前可以在数的左边加的数字添加进num数组，以便以后输出
        num[index++] = l;
        // 要把每一次符合要求的元素输出
        output();
        // 找到所有不超过当前数字一半的数字进行添加
        for (int i = 1; i <= l / 2; i++) {
            halfSet(i);
        }
        // 返回上一层之前先把添加进去的数拿出来
        index--;
    }

    public static void output() {
        for (int i = index - 1; i >= 0; i--) {
            System.out.print(num[i]);
        }
        System.out.print(" ");
        cnt++;
    }
}
