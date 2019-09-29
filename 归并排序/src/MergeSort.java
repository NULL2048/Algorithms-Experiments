import java.util.Scanner;

public class MergeSort {
    private static final int max = 500000; // 输入数据量的最大值
    private static final int sentinel = 999999999; // 这是插在数组最右端的一个极大数，必须保证比前面所有的数据都要大
    private static int[] l = new int[max / 2 + 2]; // 将拆分的数字分成左右两半
    private static int[] r = new int[max / 2 + 2];

    public static void main(String[] args) {
        int[] num = new int[max];
        int n = 0;

        System.out.print("请输入数据个数：");
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();

        System.out.print("请输入数据：");
        for (int i = 0; i < n; i++) {
            num[i] = scan.nextInt();
        }

        mergeSort(num, n, 0, n);

        System.out.print("排序结果");
        for (int i = 0; i < n; i++) {
            System.out.print(num[i] + " ");
        }
    }

    /**
     * 递归驱动
     * @param num
     * @param n
     * @param left 表示分隔的左数组的左边界
     * @param right 表示分隔的右数组的右边界
     */
    public static void mergeSort(int[] num, int n, int left, int right) {
        if (left + 1 < right) {
            int mid = (left + right) / 2;
            mergeSort(num, n, left, mid);
            mergeSort(num, n, mid, right);
            merge(num, n, left, mid, right);
        }
    }

    private static void merge(int[] num, int n, int left, int mid, int right) {
        int n1 = mid - left; // 这是左数组要存储数据的个数
        int n2 = right - mid; // 这是右数组要存储数据的个数

        // 将当前分隔区间中在num数组中原有的数据分别存储到临时开辟的两个数组中
        for (int i = 0; i < n1; i++) {
            l[i] = num[left + i];
        }
        for (int i = 0; i < n2; i++) {
            r[i] = num[mid + i];
        }

        l[n1] = r[n2] = sentinel; // 设置极大标记

        int i = 0;
        int t = 0;
        // 开始进行比较运算，只是在当前分隔区间进行重新划分排序
        for (int j = left; j < right; j++) {
            if (l[i] <= r[t]) {
                num[j] = l[i++];
            } else {
                num[j] = r[t++];
            }
        }
    }

}
