import java.util.Scanner;

/**
 * 整个算法依旧是采用分治法通过减少规模来解决棋盘覆盖问题
 * 和之前那一道马的周游路线问题一样，也是实际上就分了4种情况，也就是就是按照特殊方块在四块分割区域的哪一块来分的四种情况，具体在书上有
 * 然后就可以开始根据这四种情况来判断分解了
 */
public class ChessBoard {
    // 记录棋盘
    private static int[][] board = null;
    // L型骨牌的编号
    private static int tile = 1;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("请输入特殊方块的位置：");
        int x = scan.nextInt();
        int y = scan.nextInt();
        System.out.println("请输入棋盘大小");
        int size = scan.nextInt();


        if (size <= 0) {
            System.out.println("输入棋盘大小不合法");
        } else if (x < 0 || y < 0 || x >= size || y >= size) {
            System.out.println("输入特殊方块位置不合法");
        } else {
            board = new int[size + 5][size + 5];
            chessBoard(0, 0, x, y, size);

            for (int i = 0; i < size; i++) {
                for (int t = 0; t < size; t++) {
                    System.out.print(board[i][t] + " \t");
                }
                System.out.println();
            }
        }
    }

    /**
     * 分治法分解棋盘
     * @param tr 棋盘左上角行号
     * @param tc 棋盘左上角列号
     * @param dr 特殊方块行号
     * @param dc 特殊方块列好
     * @param size 棋盘尺寸
     */
    public static void chessBoard(int tr, int tc, int dr, int dc, int size) {
        // 当棋盘尺寸分解成1的时候，说明可以开始返回合并了
        if (size == 1) {
            return;
        }
        // 开始分解
        int t = tile++;
        int s = size / 2;
        // 下面是判断特殊方块是在哪一个区域
        if (dr < tr + s && dc < tc + s) { // 特殊方块在左上角区域
            chessBoard(tr, tc, dr, dc, s);
        } else { // 特殊方块不在左上角，就将左上角区域的与其他三个区域的汇合处设为左上角区域的特殊方块
            // 将连接处方块标记为特殊方块
            board[tr + s - 1][tc + s - 1] = t;
            // 进入下一层，继续对其进行分解，使用新的方块左上角行列号和新的特殊方块位置，新的尺寸大小
            chessBoard(tr, tc, tr + s - 1, tc + s - 1, s);
        }
        // 原理同上，这个是判断特殊方块在不在右上角区域
        if (dr < tr + s && dc >= tc + s) {
            chessBoard(tr, tc + s, dr, dc, s);
        } else {
            board[tr + s - 1][tc + s] = t;
            chessBoard(tr, tc + s, tr + s - 1, tc + s, s);
        }
        // 左下角
        if (dr >= tr + s && dc < tc + s) {
            chessBoard(tr + s, tc, dr, dc, s);
        } else {
            board[tr + s][tc + s - 1] = t;
            chessBoard(tr + s, tc, tr + s, tc + s - 1, s);
        }
        // 右下角
        if (dr >= tr + s && dc >= tc + s) {
            chessBoard(tr + s, tc + s, dr, dc, s);
        } else {
            board[tr + s][tc + s] = t;
            chessBoard(tr + s, tc + s, tr + s, tc + s, s);
        }
    }
}
