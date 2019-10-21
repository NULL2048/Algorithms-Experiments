import java.util.Scanner;

public class DictionarySort {
    // b这个数组就是用来把按照字典序排列好的数字临时放到这个b数组里面，然后将其输出出来
    private static int[] b = null;
    // 标记b数组的下标位置
    private static int index = 0;
    // 记录哪些数字已经被使用了，0表示没有使用过
    private static int[] flag = null;
    // 记录输入的当前字典序数
    private static int[] c = null;
    // 标记是否可以输出
    private static boolean flag1 = false;
    // 记录字典序编号
    private static int num = 0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入要排列的数字位数：");
        int n = scan.nextInt();
        // 存储要进行字典序排序的数字
        int[] a = new int[n];
        b = new int[n];
        c = new int[n];
        flag = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = i + 1;
        }

        System.out.println("请输入当前字典序数：");
        for (int i = 0; i < n; i++) {
            c[i] = scan.nextInt();
        }

        pailie(a, n);
    }

    /**
     * 字典序排列
     * 这个的话直接模拟一边过程吧，感觉找不出什么规律
     * @param a 要进行字典排序的数列
     * @param n 要进行字典排序的数列位数
     */
    public static void pailie(int[] a, int n) {
        // 第一层循环是确定当前这一层这个位置的数用哪一个为基准开始找
        for (int i = 0; i < n; i++) {
            // 从当前位置的数开始向后找
            for (; i < n && flag[i] == 1; i++){}
            // 保证当前的i没有超出范围，因为上面这个循环只是为了找到未使用过的数字，并没有控制是否已经超过了len
            if (i != n) {
                // 将这个数字置为使用过
                flag[i] = 1;
                // 将这个数放进b数组准备输出
                b[index++] = a[i];
                // 当b数组中存放的数字数量和要字典序排序的位数一样了，说明可以输出了
                if (index == n) {
                    if (flag1) {
                        output(b, n);
                        System.exit(0);
                    }
                    if (compare(b, n)) {
                        flag1 = true;
                        System.out.println(num);
                    }
                    num++;
                }
                // 将这一层的的数字已经存到了b中，现在进入下一层递归对后面的数进行输出
                pailie(a, n);
                // 返回的时候把b中的数字取出来
                index--;
                // 将这个数标记为没有使用过
                flag[i] = 0;
                // 在后面的循环中使用另一个数字情况进行字典序排列
            }
        }
    }

    /**
     * 输出
     * @param b
     * @param n
     */
    public static void output(int[] b, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(b[i] + " ");
        }
        System.out.println();
    }

    /**
     * 比较当前找到的字典序数列是不是和输入的字典序数列一致
     * @param b 当前字典序数列
     * @param n 要排列的字典序数列个数
     * @return 一致返回true 不一致返回false
     */
    public static boolean compare(int[] b, int n) {
        for (int i = 0; i < n; i++) {
            if (b[i] != c[i]) {
                return false;
            }
        }
        return true;
    }
}
