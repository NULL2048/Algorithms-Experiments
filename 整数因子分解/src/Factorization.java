import java.util.Scanner;

public class Factorization {
    public static int[] a = new int[50];
    public static int index = 0;
    public static int num;
    public static int cnt = 0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("请输入要分解的整数：");
        int n = scan.nextInt();
        num = n;
        resolve(n);

        System.out.println("分解因式个数：" + cnt);
    }

    public static void resolve(int n) {
        for (int i = 2; i <= n; i++) {
            if (i == n) {
                a[index++] = i;
                System.out.print(num + " = ");
                for (int t = 0; t < index; t++) {
                    System.out.print(a[t] + " ");
                    if (t != index - 1) {
                        System.out.print("* ");
                    }
                }
                System.out.println();
                index--;
                cnt++;
                return;
            }
            if (n % i == 0) {
                a[index++] = i;
                resolve(n / i);
                index--;
            }
        }
    }
}
