import java.util.Scanner;

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
                // 当发现两个序列当前位置的字符一样了，就表示找到了一个子序列
                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                    b[i][j] = 1;
                  } else if (c[i - 1][j] >= c[i][j - 1]) {
                    c[i][j] = c[i - 1][j];
                    b[i][j] = 2;
                } else {
                    c[i][j] = c[i][j - 1];
                    b[i][j] = 3;
                }
            }
        }
    }

    public static void LCS(int i, int j, String x, int[][] b) {
        if (i == 0 || j == 0) {
            return;
        }

        if (b[i][j] == 1) {
            LCS(i - 1, j - 1, x, b);
            System.out.print(x.charAt(i - 1));
        } else if (b[i][j] == 2) {
            LCS(i - 1, j, x, b);
        } else {
            LCS(i, j -1, x, b);
        }
    }
}
