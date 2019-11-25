import java.util.Scanner;
// 1A2C3D4B56
// B1D23CA45B6A
public class LongestCommonSuborder {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入序列X和序列Y");
        String x = scan.next();
        String y = scan.next();

        int m = x.length();
        int n = y.length();
        int[][] c = new int[m + 5][n + 5];
        int[][] b = new int[m + 5][n + 5];


        LCSLength(m, n, x, y, c, b);
        System.out.println("最长子序列长度为：" + c[m][n]);
        System.out.print("最长子序列为：");
        LCS(m, n, x, b);
    }

    /**
     * 计算最长公共子序列长度算法
     * @param m x序列的长度
     * @param n y序列的长度
     * @param x x序列
     * @param y y序列
     * @param c c[i][j]存储xi和yj的最长公共子序列长度
     * @param b b[i][j]记录c[i][j]的值是由哪一个子问题得到的
     */
    public static void LCSLength(int m, int n, String x, String y, int[][] c, int[][] b) {
        // 初始化下面两个，是为了自底向上有一个起始点，因为动态规划一定是从序列长度为0开始计算的
        // c[i][0]表示xi和y0的所有子序列长度，因为y序列这里取0长度，所以他们的子序列长度一定都是0
        for (int i = 1; i <= m; i++) {
            c[i][0] = 0;
        }
        // c[0][i]表示x0和yi的所有子序列长度，因为x序列这里取0长度，所以他们的子序列长度一定都是0
        for (int i = 1; i <= n; i++) {
            c[0][i] = 0;
        }

        // 从x长度为1，y长度为1开始自底向上计算最长公共子序长度
        // 原理还是最容易想到的遍历所有情况，找所有情况的子序长度，但是因为这个问题有最优子结构的性质，即子结构的最优解包含在父结构的最优解中
        // 因为遍历的时候有子问题重叠的性质，所以这里就利用动态规划，自底向上，存储已经计算过的最优子结构，如果以后计算再次用到的时候，就可以直接用，避免了重复计算
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 当发现两个序列当前位置的字符一样了，就表示找到了一个子序列,注意这里要用i-1和j-1，因为这里的i和j表示的是序列长度，不是字符的下标，所以要减一，否则出现越界错误
                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    // 根据递归式可知如果当前遍历的两个位置字符一样的话当么当前序列xi和yj的最长子序长度就是xi-1 和yj-1 的最长子序长度加一，很好理解
                    c[i][j] = c[i - 1][j - 1] + 1;
                    // 记录xi和xj最长公共子序列长度是通过第一种子问题得到的，用来以后输出最长子序
                    b[i][j] = 1;
                    // 如果xi-1 和 yj的的最长公共子序长度大于等于xi和yj-1的最长子序长度，就说明当前序列xi和yj的最长公共子序长度应该使用xi-1 和 yj的的最长公共子序长度，这就是最优子结构性质，当前序列的最优解在它的子结构中
                  } else if (c[i - 1][j] >= c[i][j - 1]) {
                    // 将xi-1 和 yj的的最长公共子序长度赋值给序列xi和yj的最长公共子序长度
                    c[i][j] = c[i - 1][j];
                    // 记录xi和yj序列的最长公共子序长度是通过第二个子问题得到的
                    b[i][j] = 2;
                    // 如果xi-1 和 yj的的最长公共子序长度小于xi和yj-1的最长公共子序长度，就说明当前序列xi和yj的最长公共子序长度应该使用xi 和 yj-1的的最长公共子序长度
                } else {
                    c[i][j] = c[i][j - 1];
                    b[i][j] = 3;
                }
            }
        }
    }

    /**
     * 输出最长子公共序列
     * @param i 当前递归到了xi序列  表示的是序列长度
     * @param j 当前递归到了yi序列 表示的是序列长度
     * @param x 因为输出的是公共子序列，所以只传进来x序列就行了，要输出的内容在x序列中都有
     * @param b 判断两个距离最长公共子序是通过哪个子问题得出的
     */
    public static void LCS(int i, int j, String x, int[][] b) {
        // 递归出口，当递归到有一个序列长度为0就可以停止递归了
        if (i == 0 || j == 0) {
            return;
        }
        // 如果xi和yj序列的最长子序列长度是通过子问题1得到的，说明当前i-1这个位置的字符就是子序列的一部分，需要输出
        if (b[i][j] == 1) {
            // 将i - 1和j - 1传入继续递归
            LCS(i - 1, j - 1, x, b);
            // 因为子问题1是当两个序列的最后一个字符一样情况，所以这个字符就是子序列的一部分，要输出
            System.out.print(x.charAt(i - 1));
            // 如果是子问题2，就不用输出，因为xi和yj序列的最长公共子序是通过xi-1和yj的最长公共子序求出来的，所以直接将i-1和j传入继续递归
        } else if (b[i][j] == 2) {
            LCS(i - 1, j, x, b);
            // 原理同上
        } else {
            LCS(i, j - 1, x, b);
        }
    }
}
