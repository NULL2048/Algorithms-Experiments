import java.util.Arrays;
import java.util.Scanner;

/**
 * 这个虽然是一道动态规划题，但是这里最主要的就是应用到了johnson法则
 * 不需要知道johnson法则是怎么推出来的，只需要知道结论
 * 在同一个流水线，两个加工机器，先从a机器加工，再从b机器加工
 * 让a加工时间比b加工时间短的任务在流水线的前面，让a加工时间比b加工时间长的任务在流水线后面，这样的排列顺序得到的总加工时间是最短的
 * 前提是进行这个johnson重排列之前，需要按照每一个任务的最短加工时间的升序排列一下才可以
 */
public class FlowShop {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // 有多少个作业
        int n = scan.nextInt();

        // 作业在M1的加工时间
        int[] a = new int[n];
        // 作业在M2的加工时间
        int[] b = new int[n];
        // 用来存放流水线的排列序列
        int[] c = new int[n];

        // 输入每一个任务的a和b的加工时间
        for (int i = 0; i < n; i++) {
            a[i] = scan.nextInt();
            b[i] = scan.nextInt();
        }

        // 输出最少加工时间
        System.out.println(flowShop(a, b, c));

        // 输出流水线上的任务顺序
        for (int i = 0; i < n; i++) {
            System.out.print((c[i] + 1) + " ");
        }
    }

    /**
     *
     * @param a
     * @param b
     * @param c 流水线顺序数组
     * @return
     */
    public static int flowShop(int[] a, int[] b, int[] c) {
        // n表示总共有多少个任务
        int n = a.length;

        // 建立任务数组
        Job[] d = new Job[n];
        for (int i = 0; i < n; i++) {
            // 记录a与b的最短时间
            int time = a[i] > b[i] ? b[i] : a[i];
            // 记录该任务哪一个机器的加工时间最短
            boolean flag = a[i] <= b[i];
            // 将在数组中的下标i存入index
            d[i] = new Job(time, i, flag);
        }

        // 根据所有作业的time进行从小到大排序
        Arrays.sort(d);

        // 标记流水线起始位置
        int j = 0;
        // 标记流水线的终点位置
        int k = n - 1;

        //将作业按照Johnson法则排序放入c中
        for (int i = 0; i < n; i++) {
            // 如果该任务是a的加工时间小于b的加工时间，按照johnson法则，将将其当道流水顺序的前面
            if (d[i].flag) {
                c[j++] = d[i].index;//如果ai<=bi,将其作业序号放入c数组中（从头开始放）
            // 否则将其放到流输顺序的后面
            } else {
                c[k--] = d[i].index;//否则
            }
        }

        //真正的动态规划部分！
        // 记录第一个任务在M1上的加工时间   注意这个j也相当于第二个任务在a加工前的等待时间
        j = a[c[0]];
        // 第一个作业处理完所需时间
        k = j + b[c[0]];
        for (int i = 1; i < n; i++) {
            //第i个作业在机器上加工完成所需时间，c[i]中存的是流水线中第i的任务在a数组中的下标，也就是输入顺序的序号
            j += a[c[i]];
            // 如果i任务完成a加工之后，i-1号任务（i任务的前一个任务）的b加工还没有完成(即j<k)，那么该i任务的总的完成时间应该是k+b[c[i]](即i-1任务完成的时间包括了i任务的在a加工前的等待时间，a加工的时间和b加工前的等待时间，然后再加上i任务的b加工时间，就是i任务完成所用的总时间)
            // 如果i任务完成a加工之后，i-1号任务已经完成了b加工，那么i任务无需等待，直接进入b加工，所以总时长就是j + b[c[i]]
            k = j < k ? k + b[c[i]] : j + b[c[i]];

            // 这里的每一轮循环j是累加的，记录当前任务完成a加工，所等待的时间和加工时间之和
            // 在更新k之前，k表示前一个任务完成需要的总时间，包括等待时间
            // 跟新之后就表示的当前i任务完成所需要的总时间，用于下一轮循环的判断
        }
        // 最短加工总时长
        return k;
    }

    public static class Job implements Comparable<Job> {
        // 存放的是该任务a和b机器中最短的一个加工时间
        private int time;
        // 存储该任务在任务数组中的下标
        private int index;
        // 如果a小于b的时间，就记为true，反之为false
        private boolean flag;

        public Job(int time, int index, boolean flag) {
            this.time = time;
            this.index = index;
            this.flag = flag;
        }

        @Override
        public int compareTo(Job o) {
            if (this.time < o.getTime()) {
                return -1;
            } else if (this.time > o.getTime()) {
                return 1;
            } else {
                return 0;
            }
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }
    }
}
/*
6
2 5
7 3
6 2
4 7
6 9
8 2

运行的最优时间为：
35
运行次序为：
1 4 5 2 6 3
*/