import java.util.Scanner;

public class Hanoi4 {
    public static int k = 0; // 将起始柱子上的盘子分成两份的间隔界限
    public static int step = 0; // 记录移动盘子的次数
    public static Pillar a;
    public static Pillar b;
    public static Pillar c;
    public static Pillar d;
    public static boolean flag = false; // true表示正式进行盘子移动，需要输出，false表示只是在寻找使step最小的k的值，不需要输出

    /**
     * 将a上的盘子通过c,d柱子移动到b柱上
     * 算法思想：
     * ①：将柱子上的盘子进行一个划分，n个盘子以第k个进行划分，分为上方的n-k个和下方的k个
     * ②：利用fourhonoi算法将n-k个A上的盘子利用B,C柱移动到D柱上
     * ③：用普通的threehonoi算法利用C柱将A柱剩下的k个盘子移动到B柱子上
     * ④：再次利用fourhonoi算法将D柱上n-k个盘子利用A,C移动到B柱子上
     * @param args
     */
    public static void main(String[] args) {
        int n; // 起始a柱上有多少个盘子
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();

        k = getMinK(n);
        System.out.println("k: " + k);
        step = 0; // 因为在查找应该使用的k值时改变了step，所以这里step要初始化为0
        flag = true; // 开始进入正式移动

        a = new Pillar('a', n);
        b = new Pillar('b', n, null);
        c = new Pillar('c', n, null);
        d = new Pillar('d', n, null);

        hannoi4(n, a, b, c, d);

        output();
        System.out.println("step: " + step);
    }

    public static void hannoi3(int n, Pillar a, Pillar b, Pillar c) {
        if (n > 0) {
            hannoi3(n - 1, a, c, b);
            move(a, b, c);
            hannoi3(n - 1, c, b, a);
        }
    }

    /**
     * 这个方法的含义把a的上面n个通过c和d移到b上
     *
     * 还是用学习递归的原则，不去关心递归时内部发生了什么，只需要关心整体过程到底要做什么就行
     * 只考虑第一次调用递归时第一层发生了什么就行，第一层发生的事情，和在以后的每一层递归调用中发生的事情其实时是一样的，只不过是规模变小
     *
     * 这个四柱汉诺塔的思路就是先把a柱上的n-k个盘子（k为分隔）通过b,c柱移动到d柱上-----对应第一次调用hannoi4
     * 再通过C柱，将a柱上剩下的k个盘子移动到b柱上-----对应下面那个hannoi3
     * 最后再d柱上的n-k个盘子通过a,c柱移动到b柱上
     * @param n
     * @param a
     * @param b
     * @param c
     * @param d
     */
    public static void hannoi4(int n, Pillar a, Pillar b, Pillar c, Pillar d) {
        if (n >= k) {
            hannoi4(n - k, a, d, b, c);
            hannoi3(k, a, b, c);
            hannoi4(n - k, d, b, a, c);
        } else {
            hannoi3(n, a, b, c);
        }
    }

    public static void move(Pillar a, Pillar b, Pillar c) {
        step++;

        if (flag == true) {
            output();
        }

        b.push(a.pop());

        if (flag == true) {
            System.out.println("[" + a.getName() + "->" + b.getName() + "]");
            System.out.println("-------------------------------------");
        }
    }

    public static void output() {
        a.output();
        b.output();
        c.output();
        d.output();
    }

    /**
     * 算法分析：设fourhonoi算法移动耗时f(i),threehonoi算法耗时g(i)
     * 地球人都知道的一个问题是在threehonoi算法盘子移动的耗时是2g(n-1)+1,
     * 因此整个算法的时间代价为2f(n-k)+2g(k)+1=>2f(n-k)+2k-1.
     * 因此存在一个最优子结构，找到使时间最少的划分点k即可求解问题
     * 这个方法就是查找能使step最小的k
     * @param n
     * @return
     */
    public static int getMinK(int n) {
        int[] kn = new int[1000]; // 存储每一种k值对应的移动次数
        k = 0;

        for (int i = 1; i <= n; i++) {
            Pillar a = new Pillar('a', n);
            Pillar b = new Pillar('b', n, null);
            Pillar c = new Pillar('c', n, null);
            Pillar d = new Pillar('d', n, null);

            step = 0;
            k = i;
            hannoi4(n, a, b, c, d);
            kn[i] = step;
        }
        // 找到对应最少移动次数的k
        int min = 999999;
        int t = 0;
        for (int i = 1; i <= n; i++) {
            if (kn[i] < min) {
                min = kn[i];
                t = i;
            }
        }
        return t;
    }
}
