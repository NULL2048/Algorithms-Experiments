import java.util.Scanner;

public class CycleTable {
    private static int[][] arr = null;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("请输入选手人数：");
        int n = scan.nextInt();
        arr = new int[n][n];

        double temp = Math.sqrt((double) n);
        int k = (int) temp;

        for (int i = 0; i < k; i++) {
            arr[0][i] = i + 1;
        }
        table(0, 0, k);
    }

    /**
     *
     * @param r 表示当前分割的表格的左上角的行
     * @param l 表示当前分割的表格的左上角的列
     * @param k 表示当前整个表还有多少个方块
     */
    public static void table(int r, int l, int k) {
        if (k >= 4) {
            table(r, l, k / 2);
            table(r, l + (k / 2), k / 2);
        }

        for (int i = r + (k / 2); i < l + k; i++) {

        }

    }

}
