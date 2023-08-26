package samsaydali.l.codegen;

public class Main {

    public static void main(String[] args) {
        int x = 2;
        int y;
        y = x * 2 + x;
        mul(x, y + 2);

    }

    private static int mul(int x, int y) {
        return x * y;
    }
}
