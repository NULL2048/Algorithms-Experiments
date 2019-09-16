import java.util.Scanner;

public class Hanoi3 {
    public static int step = 0; // 记录移动盘子的次数
    public static Pillar a;
    public static Pillar b;
    public static Pillar c;

    /**
     * 要求a柱通过c柱将其上面的盘子全部移动到b柱上
     * @param args
     */
    public static void main(String[] args) {
        int n; // 起始a柱上有多少个盘子
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();

        a = new Pillar('a', n);
        b = new Pillar('b', n, null);
        c = new Pillar('c', n, null);

        hannoi3(n, a, b, c);

        output();
        System.out.println("step: " + step);
    }

    /**
     * threeHannoi算法
     * 表示将a自上而下叠在一起的n个盘子通过c柱按照移动规则移动到b上
     * 注意：a和b这两个对象并不一定是代表a柱和b柱
     * @param n
     * @param a
     * @param b
     * @param c
     */
    public static void hannoi3(int n, Pillar a, Pillar b, Pillar c) {
        // 但是递归学习不能去关心递归内部到底发生了什么，不用去管递归时代码到底是怎么运行的
        // 只需要知道递归表达式，和递归结束条件就行，这才符合人类的思维
        // 所以真正的学习递归的方法就看第一层首次进入递归时的情况就行了
        // 实际上就是先将a柱上n-1个盘子都移动到c柱中----对应第一个hannoi3调用
        // 然后再将a柱上最下面那个最大的盘子从a柱移动到b柱-----对应下面那个move
        // 然后再将c柱上的n-1个盘子移动到b柱上-----对应最后一个hannoi3
        // 就是这么一个过程，然后当n<=0结束递归，向上返回，这就是整个递归算法的思路，不用管递归内部发生了什么
        if (n > 0) {
            // 理解这一行代码就先从第一次调用递归开始，表示将a柱（就是指的a柱）最上面的n-1个盘子移到过渡柱子c上
            // 因为是递归，所以会从这里进入到一层递归当中，再进去就成了a柱的盘子放到c柱
            // 这样递归进入这一层总共会经历n-1此，在经历n-1次c和b的交换后，最后一次必定是a柱盘子移动到b柱
            // 这就符合了盘子的移动规则
            hannoi3(n - 1, a, c, b);
            // 在最后一层递归中就会执行到这里，此时对象a,b分别代表a柱和b柱
            // 所以就是先把a柱盘子放到b柱，符合移动规则
            move(a, b);
            hannoi3(n - 1, c, b, a);
        }
    }

    /**
     * 将将a代表的柱子最上面的盘子移到b代表的柱子上
     * 注意：a和b这两个对象并不一定是代表a柱和b柱
     * @param a
     * @param b
     */
    public static void move(Pillar a, Pillar b) {
        step++;
        output();
        b.push(a.pop());
        System.out.println("[" + a.getName() + "->" + b.getName() + "]");
        System.out.println("-------------------------------------");
    }

    /**
     * 输出a,b,c柱上的盘子情况
     */
    public static void output() {
        a.output();
        b.output();
        c.output();
    }
}