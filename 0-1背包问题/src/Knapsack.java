import java.util.Scanner;

// v 6,3,5,4,6
// w 2,2,6,5,4
// 10
public class Knapsack {
    public static int[][] dp = new int[100][100];

    public static int[] a = new int[100];

    public static int[] v = null;

    public static int[] w = null;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("输入可选的物品数：");
        int n = scan.nextInt();

        // 存储物品的价值
        v = new int[n + 5];
        // 存储物品的重量
        w = new int[n + 5];

        System.out.println("依次输入物品的价值：");
        for (int i = 0; i < n; i++) {
            v[i] = scan.nextInt();
        }
        System.out.println("依次输入物品的重量");
        for (int i = 0; i < n; i++) {
            w[i] = scan.nextInt();
        }

        System.out.println("请输入背包的承重：");
        int knapsackMax = scan.nextInt();

        System.out.println(getAns(n, knapsackMax));
    }

    /**
     * dp数组的第一维表示的物品编号，第二维表示的背包承重  dp[i][j]表示当背包承重为j时，有[1,i]号物品可供选择时的能装物品的最大价值数
     * 因为能承重10的背包想要求的结果都在其最优子解中，所以要先把比他小的承重的背包的结果求出来
     *
     * @param n           物品数
     * @param knapsackMax 背包承重
     * @return
     */
    public static int getAns(int n, int knapsackMax) {
        // 初始化dp数组
        // 当背包承重为0时，肯定什么物品都装不进去，所以这里都置为零
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }
        // 当一个物品都没有时，不管多大的背包也肯定什么也装不进去
        for (int i = 0; i <= knapsackMax; i++) {
            dp[0][i] = 0;
        }

        // 外层循环遍历的是n个物品  内层循环遍历的是当确定要装什么物品的时候，自己拥有的背包承重是多少
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= knapsackMax; j++) {
                // 这里的w[i - 1]和v[i - 1]之所以要减一是因为dp的一维表示的物品编号是从1开始的，但是输入w和v的时候下标是从0开始的
                // 比较当前背包承重是否大于当前的物品重量，如果大于，就说明至少这个背包可以装下这个物品，那么就去比较一下如果装了这个物品，比不装他得到的价值大不大
                if (j >= w[i - 1]) {
                    // 将相同承重背包不装i物品时的最大价值和如果将背包以前装过的物品取出来用来装入i物品的价值比较，哪个大就用哪个
                    // dp[][j - w[i-1]]二维的那个操作就是把之前装过的东西减出来，空出位置装i物品，再加上i物品的价值v[i-1]
                    dp[i][j] = Math.max(dp[i - 1][j], v[i - 1] + dp[i - 1][j - w[i - 1]]);
                } else {
                    // 如果当前背包的承重只装i这一个物品都装不下，那么直接就不用去装这个物品了，直接复用dp[i-1][j]，即有[1,i-1]号物品供咱们选择的时候装的最大价值数就可以了
                    dp[i][j] = dp[i - 1][j];
                }
                System.out.printf("%4d", dp[i][j]);
            }
            System.out.println();
        }

        // 记录背包剩余的承重空间
        int b = knapsackMax;
        // 必须从物品n开始向下遍历，因为最大的价值肯定在最前面，不能从后面向上遍历
        for (int i = n; i >= 1; i--) {
            // 如果发现当前放这个物品的价值大于前面的价值，说明这个物品就已经放进去过了，所以将a数组这个物品标记为1，表明已经被取用
            if (dp[i][b] > dp[i - 1][b]) {
                a[i] = 1;
                // 减掉已经加入到背包的物品的重量，求得背包还剩下的承重
                b -= w[i - 1];
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();

        return dp[n][knapsackMax];
    }
}
