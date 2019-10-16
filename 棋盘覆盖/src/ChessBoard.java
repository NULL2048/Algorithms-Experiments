import java.util.Scanner;

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

    public static void chessBoard(int tr, int tc, int dr, int dc, int size) {
        if (size == 1) {
            return;
        }
        int t = tile++;
        int s = size / 2;

        if (dr < tr + s && dc < tc + s) {
            chessBoard(tr, tc, dr, dc, s);
        } else {
            board[tr + s - 1][tc + s - 1] = t;
            chessBoard(tr, tc, tr + s - 1, tc + s - 1, s);
        }

        if (dr < tr + s && dc >= tc + s) {
            chessBoard(tr, tc + s, dr, dc, s);
        } else {
            board[tr + s - 1][tc + s] = t;
            chessBoard(tr, tc + s, tr + s - 1, tc + s, s);
        }

        if (dr >= tr + s && dc < tc + s) {
            chessBoard(tr + s, tc, dr, dc, s);
        } else {
            board[tr + s][tc + s - 1] = t;
            chessBoard(tr + s, tc, tr + s, tc + s - 1, s);
        }

        if (dr >= tr + s && dc >= tc + s) {
            chessBoard(tr + s, tc + s, dr, dc, s);
        } else {
            board[tr + s][tc + s] = t;
            chessBoard(tr + s, tc + s, tr + s, tc + s, s);
        }
    }
}
