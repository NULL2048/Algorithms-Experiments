import java.util.Scanner;

public class QuickSort {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("请输入排序的数的个数：");
        int n = scan.nextInt();

        int[] num = new int[n + 5];
        System.out.print("请输入要排序的数字：");
        for (int i = 0; i < n; i++) {
            num[i] = scan.nextInt();
        }

        quickSort(num, 0, n - 1);

        System.out.print("排序之后：");
        for (int i = 0; i < n; i++) {
            System.out.print(num[i] + " ");
        }
    }

    public static void quickSort(int[] num, int left, int right) {
        int p;
        if (left < right) {
            p = partition(num, left, right);
            quickSort(num, left, p - 1);
            quickSort(num, p + 1, right);
        }
    }

    public static int partition(int[] num, int left, int right) {
        int i = left - 1;
        int j = left;
        int x = num[right];
        int t;

        while (j < right) {
            if (num[j] <= x) {
                i++;
                t = num[j];
                num[j] = num[i];
                num[i] = t;
            }
            j++;
        }

        t = num[i + 1];
        num[i + 1] = num[right];
        num[right] = t;

        return i+1;
    }
}
