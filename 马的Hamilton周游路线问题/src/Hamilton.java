import java.util.Scanner;

/**
 * 国际象棋的马是走日字的，所以马每一次有八个位置可以走
 * 本题的题意其实就是每次马走一步有三种情况
 * 1，判断走的这个位置是否已经走出棋盘外
 * 2，判断走的这个位置是否已经到达过了
 * 3，判断走的位置是否回到了起点
 *
 * 棋盘左下角是坐标轴的原点
 */
public class Hamilton {
    // 记录棋子可以到达的位置
    private static int[] px = {-1, -2, 1, 2, -2, -1, 1, 2};
    private static int[] py = {2, 1, 2, 1, -1, -2, -2, -1};
    // 记录当前位置是否已经走过并且记录棋子走到这个位置用的步数，未走过为0，走过为非0
    private static int[][] count = null;
    // 记录棋子整个周游过程中走过的坐标，下标表示第几步
    private static int[] dx = new int[500];
    private static int[] dy = new int[500];

    // 记录棋盘大小
    private static int m = 0;
    private static int n = 0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("请输入棋盘的大小（m*n）：");
        m = scan.nextInt();
        n = scan.nextInt();
        count = new int[m][n];

        dx[1] = 0;
        dy[1] = 0;
        move(0, 0, 1);

    }

    /**
     * 移动程序
     * @param x 当前横坐标
     * @param y 当前纵坐标
     * @param cnt 当前走的步数
     */
    public static void move(int x, int y, int cnt) {
        if (cnt == m * n && isOrigin(x, y)) {
            output(cnt);
            System.exit(0);
        }

        for (int i = 0; i < 8; i++) {
            int nextX = x + px[i];
            int nextY = y + py[i];
            if (!isOut(nextX, nextY) && count[nextX][nextY] == 0) {
                count[nextX][nextY] = cnt + 1;
                dx[cnt + 1] = nextX;
                dy[cnt + 1] = nextY;
                move(nextX, nextY, cnt + 1);
                count[nextX][nextY] = 0;
            }
        }
    }

    /**
     * 判断当前位置是否出界
     * @param x
     * @param y
     * @return 出界返回true 未出界返回false
     */
    public static boolean isOut(int x, int y) {
        if (x < 0 || x >= m || y < 0 || y >= n) {
            return true;
        }
        return false;
    }

    /**
     * 判断当前这个位置可不可以回到原点
     * @param x
     * @param y
     * @return 可以返回true 不可以返回false
     */
    public static boolean isOrigin(int x, int y) {
        for (int i = 0; i < 8; i++) {
            if ((x + px[i] == 0) && (y + py[i] == 0)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 输出
     */
    public static void output(int cnt) {
        for (int i = 0; i < m; i++) {
            for (int t = 0; t < n; t++) {
                System.out.print(count[i][t] + " ");
            }
            System.out.println();
        }

        for (int i = 1; i <= cnt; i++) {
            System.out.print("(" + dx[i] + "," + dy[i] + ") ");
            if (i % m == 0) {
                System.out.println();
            }
        }
    }
}
