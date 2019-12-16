import java.util.Arrays;
import java.util.Scanner;

public class IntervalCoverage {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // 直线上有n个点
        int n = scan.nextInt();
        // 用长度为k的闭区间覆盖这n个点
        int k = scan.nextInt();

        int[] coordinates = new int[100];
        // 输入n个点在直线上的坐标，可能相同
        for (int i = 0; i < n; i++) {
            coordinates[i] = scan.nextInt();
        }

        // 排序
        Arrays.sort(coordinates, 0, n);

        // 所需要的闭区间数，从1算起
        int cnt = 1;
        // 当前该覆盖哪一个点，从1算起
        int i = 1;
        // 第一根闭区间覆盖第一个点之后，另一端所能达到的位置
        int temp = coordinates[0] + k;
        System.out.println(coordinates[0] + " " + temp + " " + cnt + " " + i);
        while (i < n) {
            // 如果当前该覆盖点的位置已经被当前有的区间覆盖，就直接i++,不用添加新的区间
            if (coordinates[i] <= temp) {
                i++;
            // 如果当前该覆盖点的位置没有被当前有的区间覆盖，就需要再添加一个区间
            } else if (coordinates[i] > temp) {
                // 从这个点接着算起
                temp = coordinates[i];
                // 记录最新区间所能达到的最远点
                temp += k;
                // 区间数加一
                cnt++;
            }

            System.out.println(coordinates[i] + " " + temp + " " + cnt + " " + i);
        }
        System.out.println(cnt);
    }
}
/*
7 3
1 2 3 4 5 -2 6
*/