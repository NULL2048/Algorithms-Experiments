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

        quickSort(num, 0, n - 1, n); // 注意这里传入的是数组最后一个元素的下标n-1，而不是n

        System.out.print("排序之后：");
        for (int i = 0; i < n; i++) {
            System.out.print(num[i] + " ");
        }
    }
    /**
     * 快速排序驱动
     * @param num 
     * @param left 表示分割左数组的左边界
     * @param right 表示分割右数组的右边界
     * 
     * 快速排序是将数据分成了三部分，一个左数组，一个中间的数，一个右数组，其实快速排序和归并排序的思路很想，也是通过分割缩小数据规模，对小规模的数据进行比较排序
     * 但是它比较快的是因为它直接在分割的过程时就在原数组上排序了
     */
    public static void quickSort(int[] num, int left, int right, int n) {
        int p;
        if (left < right) { // 当left和right重合之后说明已经排序完成了
            p = partition(num, left, right);

            for (int i = 0; i < n; i++) {
                System.out.print(num[i] + " ");
            }
            System.out.println();

            quickSort(num, left, p - 1, n);
            quickSort(num, p + 1, right, n);
        }
    }
    
    public static int partition(int[] num, int left, int right) {
        int i = left - 1; // 指向要分割的位置，i右边的元素都应该比x的值大
        int j = left; // 指向当前要配置的位置
        int x = num[right]; // 以最右面的元素为基准，比x小的放到i下标的左面,比x大放到i下标的右面
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
        // 将最右边的元素放到i下标的位置
        t = num[i + 1];
        num[i + 1] = num[right];
        num[right] = t;
        // 返回i+1是分割位置
        return i+1;
    }
}
