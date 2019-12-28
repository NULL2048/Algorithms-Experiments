import java.util.Scanner;

public class Permutations {
    public static int[] list = new int[50];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        for (int i = 0; i < n; i++) {
            int temp = scan.nextInt();
            list[i] = temp;
        }
        perm(0, n - 1);
    }
    /**
     *交换
     * @param i
     * @param t
     */
    public static void swap(int i, int t) {
        int temp = 0;
        temp = list[i];
        list[i] = list[t];
        list[t] = temp;
    }

    /**
     * 全排列递归算法
     * @param k
     * @param m
     */
    public static void perm(int k, int m) {
        if (k == m) {
            for (int i = 0; i <= m; i++) {
                System.out.print(list[i]);
            }
            System.out.println();
        } else {
            for (int i = k; i <= m; i++) {
                /**
                 * 基本思想：去重的全排列就是从第一个数字起每个数分别与它后面非重复出现的数字交换；
                 * 增加了判断条件，在两个值交换之前首先判断者两个数值是否相等，若相等则不交换，不相等则进行交换
                 */
                if (i == k || list[i] != list[k]) {
                    swap(i, k);
                    perm(k + 1, m);
                    swap(i, k);
                }
            }
        }
    }
}
