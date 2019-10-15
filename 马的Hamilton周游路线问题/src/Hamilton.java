import java.util.Scanner;

/**
 * 国际象棋的马是走日字的，所以马每一次有八个位置可以走
 * 本题的题意其实就是每次马走一步有三种情况
 * 1，判断走的这个位置是否已经走出棋盘外
 * 2，判断走的这个位置是否已经到达过了
 * 3，判断走的位置是否回到了起点
 *
 * 棋盘左上角是坐标轴的原点
 */
public class Hamilton {
    private static int[] px = {-1, -2, 1, 2, -2, -1, 1, 2};
    private static int[] py = {2, 1, 2, 1, -1, -2, -2, -1};
    private static int m = 0;
    private static int n = 0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("请输入棋盘的大小（m*n）：");
        m = scan.nextInt();
        n = scan.nextInt();

        move(0, 0, 1);

    }

    public static void move(int x, int y, int num) {

    }

    public static boolean isOut(int nextX, int nextY) {
        if (nextX < 0 || nextX > m) {
            if (nextY > 0 || nextY < (-n)) {
                return true;
            }
        }
    }
}
