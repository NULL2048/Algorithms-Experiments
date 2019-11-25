import java.util.Scanner;

public class Knapsack {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("输入可选的物品数：");
        int n = scan.nextInt();

        // 存储物品的价值
        int[] v = new int[n + 5];
        // 存储物品的重量
        int[] w = new int[n + 5];

        System.out.println("依次输入物品的价值：");
        for (int i = 0; i < n; i++) {
            v[i] = scan.nextInt();
        }
        System.out.println("依次输入物品的重量");
        for (int i = 0; i < n; i++) {
            w[i] = scan.nextInt();
        }

        System.out.println("请输入背包的承重：");
        int knapsackMax = scan.nextInt();
    }
}
