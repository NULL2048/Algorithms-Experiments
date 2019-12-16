/**
 * 给定n个互异的关键字组成的序列K=<k1,k2,...,kn>，且关键字有序（k1<k2<...<kn），我们想从这些关键字中构造一棵二叉查找树。
 * 对每个关键字ki，一次搜索搜索到的概率为pi。
 * 可能有一些搜索的值不在K内，因此还有n+1个“虚拟键”d0,d1,...,dn，他们代表不在K内的值。
 * 具体：d0代表所有小于k1的值，dn代表所有大于kn的值。
 * 而对于i = 1,2,...,n-1,虚拟键di代表所有位于ki和ki+1之间的值。
 * 对于每个虚拟键，一次搜索对应于di的概率为qi。
 * 要使得查找一个节点的期望代价（代价可以定义为：比如从根节点到目标节点的路径上节点数目）最小，就需要建立一棵最优二叉查找树。
 */
public class OptimalBinarySearchTree {
    // 极大值
    private static final int MAX = 9999;
    // 结点个数
    private static int n = 5;
    // 对第i个关键字搜索到的概率为p  关键字是在二叉搜索树中的值
    private static double[] p={0,0.15,0.10,0.05,0.10,0.20};
    // 对第i个虚拟键搜索到的概率为p  虚拟键是不在二叉搜索树中的值
    private static double[] q={0.05 ,0.10 ,0.05 ,0.05 ,0.05 ,0.10};
    // 记录子树期望代价
    private static double[][] e = new double [100][100];
    // 子树概率总和
    private static double[][] w = new double[100][100];
    // 记录根结点
    private static int[][] root = new int[100][100];

    public static void main(String[] args) {
        // 假设一次搜索的实际代价为检查的节点的个数，即所发现的节点的深度加1
        System.out.println("最优期望搜索代价：" + OBST(n));
        outputOBST(1, n);
    }

    private static double OBST(int n) {
        // 初始化只包括虚拟键的子树
        for (int i = 1; i <= n + 1; i++) {
            w[i][i - 1] = q[i - 1];
            e[i][i - 1] = q[i - 1];
        }

        // 自底向上，由左到右逐步计算
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n - i + 1; j++) {
                int k = i + j - 1;
                e[j][k] = MAX;
                w[j][k] = w[j][k - 1] + p[k] + q[k];
                // 求取最小代价的子树的根
                for (int l = j; l <= k; l++) {
                    double temp = e[j][l - 1] + e[l + 1][k] + w[j][k];
                    if (temp < e[j][k]) {
                        e[j][k] = temp;
                        root[j][k] = l;
                    }
                }
            }
        }
        return e[1][n];
    }

    /**
     * 输出最优二叉树的结构
     * 打印出[i,j]子树，它是根r的左子树和右子树
     * @param i
     * @param j
     */
    private static void outputOBST(int i, int j) {
        if (i == 1 && j == n) {
            System.out.printf("K%d为根\n", root[i][j]);
        }
        if (i < j) {
            System.out.printf("K%d是K%d的左孩子\n", root[i][root[i][j] - 1], root[i][j]);
            outputOBST(i,root[i][j] - 1);

            if(root[i][j]+1 < j) {
                System.out.printf("K%d是K%d的右孩子\n", root[root[i][j] + 1][j], root[i][j]);
            }

            outputOBST(root[i][j] + 1,j);
        }

        if (i == j) {
            System.out.printf("d%d是K%d的左孩子\n", i - 1, i);
            System.out.printf("d%d是K%d的右孩子\n", i, i);
        }
        if (i > j) {
            System.out.printf("d%d是K%d的右孩子\n", j, j);
        }
    }
}
