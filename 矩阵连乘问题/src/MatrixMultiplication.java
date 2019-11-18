import java.util.Scanner;
// 最终版本
public class MatrixMultiplication {
    public static int N = 7;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] p = {30, 35, 15, 5, 10, 20, 25};
        int[][] m = new int[N][N];
        int[][] s = new int[N][N];

        matrixChain(p, m, s, N);
        output(s, 1, N - 1);
    }

    /**
     * 矩阵相乘
     * @param ra a矩阵的行
     * @param ca a矩阵的列
     * @param rb b矩阵的行
     * @param cb b矩阵的列
     * @param a a矩阵二维数组
     * @param b b矩阵二维数组
     * @return 返回矩阵相乘结果矩阵
     */
    public static int[][] matrixMultiply(int ra, int ca, int rb, int cb, int[][] a, int[][] b) {
        // 得到的结果c矩阵的行和a矩阵的行一样，列和b矩阵的列一样
        int[][] c = new int[ra][cb];

        if (ca != rb) {
            throw new IllegalArgumentException("矩阵不可乘！");
        } else {
            for (int i = 0; i < ra; i++) {
                for (int t = 0; t < cb; t++) {
                    int sum = 0;
                    for (int j = 0; j < ra; j++) {
                        sum += (a[i][j] * b[j][t]);
                    }
                    c[i][t] = sum;
                }
            }
        }
        return c;
    }

    /**
     * 自底向上计算
     * @param p p为矩阵链 p[0],p[1]代表第一个矩阵的行数和列数，p[1].p[2]代表第二个矩阵的行数和列数
     * @param m 存储最优结果（矩阵相乘计算次数最少的次数）的二维矩阵  m[i][j]表示从第i个矩阵乘到第j个矩阵计算的最少次数
     * @param s 存储选择最优结果路线，s[i][j]表示从第i个矩阵乘到第j个矩阵计算求得的最少次数是在i-j间使用哪个分割点k得到的。用来得到最优解的括号分割位置
     * @param length p数组的长度  如果是6个矩阵，那么length=7
     */
    public static void matrixChain(int[] p, int[][] m, int[][] s, int length) {
        // n表示n个矩阵
        int n = length - 1;
        // m[i][i]只有一个矩阵，一个矩阵的相乘次数为0，即m[i][i]=0
        for (int i = 1; i < length; i++) {
            m[i][i] = 0;
        }

        // l表示当前要求的矩阵链长度  如l=2时， 计算所有的m[i][i+1],即长度为2的链的最小代价（最少计算次数）
        // 只有两个以上的矩阵才可以相乘，所以从2开始计算
        for (int l = 2; l <= n; l++) {
            // 当前就是要计算所有链长为l的链的连乘最小计算代价 这里的i表示的时矩阵链从哪一个矩阵开始算起，即m[i][i+l] 把所有的i的起始点都计算一遍
            // 循环以length-1结尾是因为l为要计算的矩阵链长度，n是当前的矩阵个数，计算一下最后一个可以使m[i][i+l]中i+l还在p数组长度范围中的i，不能让i+l超出了p数组的长度
            // 这个循环就列出了所有可以求l这个范围的链长的所有有效的起始点i
            for (int i = 1; i <= length - l; i++) {
                // m[i][j]  j就是要计算以i为矩阵链起始位置时的矩阵链的末尾位置
                // 起始位置加上要计算的链长l减1就是末尾的位置
                int j = i + l - 1;
                // 将最优乘积代价设置为极大值，表示待计算
                m[i][j] = 0x7fffffff;
                // 固定起始点i和末尾点j后，开始对m[i][j]这个范围进行分割 分割点为k,把所有k的情况全部列出来
                // 起始点可能的位置就是[i,j-1]  k不能等于j，因为分割点在末尾点相当于没有分割，和最开始分割点在起始点就重了
                for (int k = i; k <= j - 1; k++) {
                    // 这个就是计算连乘次数的递归式  将[i,j]范围内按分割点k进行分割 那么当前m[i][j]最优代价就是m[i][k]+m[k+1][j]+分隔的两部分求得的两个矩阵相乘的计算次数（p[i - 1] * p[k] * p[j] 这个就是两个矩阵相乘时要计算的次数，很容易想明白）
                    int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];

                    // 如果计算的计算次数比以前记录在m[i][j]中的最小次数小，说明找到了[i,j]间隔中代价更小的值，更新最小计算次数和最优分割点k
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }
        // 输出整个矩阵链连乘的最少计算次数
        System.out.println(m[1][N - 1]);
    }

    /**
     * 输出最优的加括号方法
     * @param s 记录每个区间的最优分割
     * @param i 要进行分割区间的左边界
     * @param j 要进行分割区间的右边界
     */
    public static void output(int[][] s, int i, int j) {
        // i==j表明分割已经结束了，可以输出矩阵标号了，这就是递归出口
        if(i == j) {
            System.out.print("A" + i);
        } else {
            System.out.print("(");
            // 使用递归进到分割点k的左边的区间继续进行递归
            output(s, i, s[i][j]);

            // 进入分割点k右边的区间继续进行递归
            output(s,s[i][j] + 1, j);

            System.out.print(")");
        }
    }
}
