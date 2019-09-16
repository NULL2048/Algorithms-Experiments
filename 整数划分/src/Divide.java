import java.util.Scanner;

public class Divide {
    public static int num;
    public static int[] a = new int[100];
    public static int index = 0;
    public static int sum = 0;
    public static int cnt = 0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("请输入要划分的整数：");
        num = scan.nextInt();

        divide(1);

        System.out.println("划分式个数：" + cnt);
    }

    public static void divide(int m) {
        if (sum == num) {
            System.out.print(sum + " = ");
            for (int i = 0; i < index; i++) {
                System.out.print(a[i] + " ");
                if (i != index - 1) {
                    System.out.print("+ ");
                }
            }
            System.out.println();
            cnt++;
        }
        if (sum > num) {
            return;
        }
        for (int i = m; i <= num; i++) {
            sum += i;
            a[index++] = i;
            divide(i);
            sum -= i;
            index--;
        }
    }
}
