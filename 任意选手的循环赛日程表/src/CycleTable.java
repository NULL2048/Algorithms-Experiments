import java.util.Scanner;

public class CycleTable {
    private static int[][] arr = null;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("请输入选手人数：");
        int n = scan.nextInt();

        arr = new int[n + 5][n + 5];

        cut(n);

        output(n);
    }

    /**
     * 输出
     * 要对奇数选手和偶数选手分别讨论
     * @param n
     */
    public static void output(int n) {
        if (judgeOdd(n)) {
            for (int i = 1; i <= n; i++) {
                for (int t = 1; t <= n + 1; t++) { // 这里要输出n + 1天比赛
                    // 当人数时奇数的时候，因为添加了n + 1号虚拟选手，所以要把这个虚拟选手过滤掉
                    if (arr[i][t] == n + 1) {
                        System.out.print("-\t");
                    } else {
                        System.out.print(arr[i][t] + "\t");
                    }
                }
                System.out.println();
            }
        } else {
            for (int i = 1; i <= n; i++) {
                for (int t = 1; t <= n; t++) {
                    System.out.print(arr[i][t] + "\t");
                }
                System.out.println();
            }
        }

    }

    /**
     * 判断奇偶
     * @param num
     * @return 奇数返回true 否则返回false
     */
    public static boolean judgeOdd(int num) {
        if (num % 2 != 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 分割方法
     * @param n 每一层分割的人数
     */
    public static void cut(int n) {
        // 这是递归出口
        if (n == 1) {
            arr[1][1] = 1;
            return;
        }
        // 奇数情况
        if (judgeOdd(n)) {
            cut(n + 1);
            return;
        }
        // 偶数情况
        cut(n / 2);
        // 根据n的奇偶数情况和n / 2的奇偶数情况进行分类讨论拷贝数字
        makeCopy(n);
    }

    public static void makeCopy(int n) {
        // n / 2是奇数
        if (n / 2 > 1 && judgeOdd(n / 2)) {
            copyOdd(n);
        } else { // 偶数普通情况
            copy(n);
        }
    }

    /**
     * 对偶数选手数进行数字块的拷贝
     * @param n 对n个数字块进行拷贝
     */
    public static void copy(int n) {
        int m = n / 2;
        // 将分割的方块进行数字拷贝
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= m; j++) {
                // 将左上角已经有的数字加m赋值给右上角方块
                arr[i][j + m] = arr[i][j] + m;
                // 将右上角已经有的数字赋值给左下角方块
                arr[i + m][j] = arr[i][j + m];
                // 将左上角已经有的数字赋值给右下角
                arr[i + m][j + m] = arr[i][j];
            }
        }
        System.out.println("B");
        output(10);
    }

    /**
     * 对奇数选手进行数字拷贝
     * 前n / 2 轮比赛空选手与下一个未参赛的选手进行比赛
     * @param n 对n个数字块进行拷贝
     */
    public static void copyOdd(int n) {
        // 存储后n / 2轮比赛的选手号 这其中包含了虚拟选手号，存储的就是前n / 2轮没有参赛的选手
        int[] temp = new int[n + 5];
        int m = n / 2;
        for (int i = 0; i <= m; i++) {
            // 将后m号选手存到b数组的前m个
            temp[i] = m + i;
            // 将b数组前m个元素的值赋值给后m个元素赋
            temp[m + i] = temp[i];
        }

        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= m + 1; j++) {
                if (arr[i][j] > m) { // 对手是后m号
                    arr[i][j] = temp[i]; // 将前m号选手的对手赋值
                    // 多加一列
                    arr[m + i][j] = (temp[i] + m) % n; // 将后m号选手的对手赋值，这里为了循环从temp里面取选手好用到了求余
                } else { // 左上角的赋值给左下角
                    arr[m + i][j] = arr[i][j] + m;
                }
            }
            System.out.println("A");
            output(10);
            // 因为是奇数所以添加一个虚拟选手
            for (int j = 2; j <= m; j++) {
                // 右上角
                arr[i][m + j] = temp[i + j - 1];
                // 多加一行
                arr[temp[i + j - 1]][m + j] = i;
            }
        }
    }
}




