import java.util.Scanner;

/**
 * 加满油之后可行驶n公里，
 * 对于给定的n和k个加油站位置，计算最少加油次数。
 * 基本思路：经过加油站时如果汽车有油，且能到达下一个加油站，
 * 就不加油，否则，加油
 * 设定数组来记录经过加油站时是否加油
 */
public class GasProblem {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入汽车可行驶公里数n以及途径多少个加油站：");
        // 加满油能行驶多远
        int n = scan.nextInt();
        // 一共有多少个加油站
        int k = scan.nextInt();

        // distance[k] = s 标识k加油站距离k-1加油站的距离为s
        // 0和k+1表示起始点和终点
        int[] distance = new int[100];
        System.out.println("请输入加油站之间的距离");
        for (int i = 0; i <= k; i++) {
            distance[i] = scan.nextInt();
        }

        // 记录总到了第几个加油站
        int i = 0;
        // 记录当前汽车还能行驶的距离
        int temp = 0;
        // 记录汽车的加油次数
        int cnt = 0;

        while (i <= k) {
            // 如果加满油都到不了下一个加油站，说明就到不了目的地
            if (distance[i] > n) {
                System.out.println("No Solution");
                break;
            }

            // 如果当前汽车所能形式的距离不能到达下一个位置，就需要加满油
            if (temp < distance[i]) {
                cnt++;
                temp += n;
            }

            // 如果汽车当前能行驶到下一个加油站，就将当前加油站位置自增，并且减去汽车行驶剩余里程的消耗
            if (temp >= distance[i]) {
                temp -= distance[i];
                i++;
            }
            System.out.println(i + " " + temp + " " + cnt + " " + distance[i]);
        }

        System.out.println(cnt);
    }
}
/*
7 7
1 2 3 4 5 1 6 6
*/
