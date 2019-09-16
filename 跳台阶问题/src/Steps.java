import java.util.Scanner;

public class Steps {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        int cnt = f(n);
        System.out.println(cnt);
    }

    public static int f(int a) {
        if (a == 2) {
            return 2;
        }

        if (a == 1) {
            return 1;
        }

        return f(a - 1) + f(a - 2);
    }
}
