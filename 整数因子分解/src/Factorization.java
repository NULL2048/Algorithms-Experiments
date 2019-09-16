import java.util.Scanner;

public class Factorization {
    public static int[] a = new int[50];
    public static int index = 0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入要分解的整数：");
        int n = scan.nextInt();
        resolve(n);
    }

    public static void resolve(int n) {
        for (int i = 2; i <= n; i++) {
            if (i == n) {
                for (int t = 0; t <= index; t++) {
                    System.out.print(t + " ");
                }
                System.out.println();
                index--;
                return;
            }
            if (n % i == 0) {
                a[index++] = n / i;
                resolve(n / i);
            }
        }
    }
}
