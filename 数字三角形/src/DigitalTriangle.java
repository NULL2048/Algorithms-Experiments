import java.util.Scanner;

public class DigitalTriangle {
    /**
     * 存储数字三角形
     */
    private static int[][] triangle = new int[100][100];
    /**
     * 数字三角形行数
     */
    private static int n;
    /**
     * maxSum[i][j]表示走到第i行第j列的最大和
     */
    private static int[][] maxSum = new int[100][100];
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // 输入三角形有多少行
        n = scan.nextInt();

        // 初始化maxSum，-1表示该位置还没有计算
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                maxSum[i][j] = -1;
            }
        }

        // 输入数字三角形，下标从1算起
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                triangle[i][j] = scan.nextInt();
            }
        }
        System.out.println(MaxSum(1, 1));
    }

    /**
     * 这道题只有两种路线
     * 注意：路径上的每一步只能从一个数走到下一层上和它最近的左边的那个数或者右边的那个数。
     * 所以只能向下走，不能平移
     * 先递归进去划分问题规模，当划分到最后一层，就是划分到最小了，然后再自底向上开始返回，进行动态规划
     * @param i
     * @param j
     * @return
     */
    private static int MaxSum(int i, int j) {
        // 递归出口，当进行到最后一行的时候直接返回当前位置的数，作为当前位置的第一个加和，自底向上来
        // 递归所有分支向下递归，最终的效果就是最后一层的所有的数的位置的maxSum都被返回了当前位置的值作为初始值，然后向上回溯判断那种路线是和最大的
        if (i == n) {
            return triangle[i][j];
        }

        // 如果当前数的正下方的数还没有被计算过，就递归进去计算这个数
        if (maxSum[i + 1][j] == -1) {
            maxSum[i + 1][j] = MaxSum(i + 1, j);
        }

        // 如果当签署下方靠右的的数还没有被计算过，就递归进去计算这个数
        if (maxSum[i + 1][j + 1] == -1) {
            maxSum[i + 1][j + 1] = MaxSum(i + 1, j + 1);
        }

        // 如果正下方的数大于下方右边的数，就将正下方的数作为要走过的路径，将triangle[i][j]位置的数加上去
        // 返回给的是maxSum[i][j]这个元素，i和j是相对于这一层来说的
        if (maxSum[i + 1][j] > maxSum[i + 1][j + 1]) {
            return maxSum[i + 1][j] + triangle[i][j];
        }

        // 如果下方右边的数大于正下方的数，就将下方右边的数作为要走过的路径，将triangle[i][j]位置的数加上去
        return maxSum[i + 1][j + 1] + triangle[i][j];
    }
}
/*
5
7
3 8
8 1 0
2 7 4 4
4 5 2 6 5

应输出30
*/