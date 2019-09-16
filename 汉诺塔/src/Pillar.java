public class Pillar {
    private char name;
    private int[] pillar;
    private int index;

    public Pillar(char name, int n) {
        this.name = name;
        this.index = 0;

        pillar = new int[n + 5];
        for (int i = n; i > 0; i--) {
            pillar[this.index++] = i;
        }
    }

    public Pillar(char name, int n, Object flag) { // 表示创建没有盘子的柱子
        this.name = name;
        int index = 0;

        pillar = new int[n + 5];
    }

    public char getName() {
        return name;
    }

    public int peek() {
        return pillar[index - 1];
    }

    public int pop() {
        return pillar[--index];
    }

    public void push(int n) {
        pillar[index++] = n;
    }

    public boolean empty() {
        if (index == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void output() {
        System.out.print(name + ":");
        for (int i = 0; i < index; i++) {
            System.out.print(pillar[i] + " ");
        }
        System.out.println("");
    }
}
