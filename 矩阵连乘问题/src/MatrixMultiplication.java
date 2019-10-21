import java.util.Scanner;

public class MatrixMultiplication {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
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

    }
}
