public class chess {

    int[] x;

    public chess(int N) {
        x = new int[N];
    }

    public boolean canPlaceQueen(int r, int c) {
        //Returns TRUE if a queen can be placed in row r and column c, returns FALSE otherwise

        for (int i = 0; i < r; i++) {
            if (x[i] == c || (i - r) == (x[i] - c) || (i - r) == (c - x[i])) {
                return false;
            }
        }
        return true;
    }

    public void printQueens(int[] x) {
        int N = x.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (x[i] == j) {
                    System.out.print("Q ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void placeNqueens(int r, int n) {
         //algorithm uses backtracking to show which queens aren't attacking
        for (int c = 0; c < n; c++) {
            if (canPlaceQueen(r, c)) {
                x[r] = c;
                if (r == n - 1) {
                    printQueens(x);
                } else {
                    placeNqueens(r + 1, n);
                }
            }
        }
    }

    public void callplaceNqueens() {
        placeNqueens(0, x.length);
    }

    public static void main(String args[]) {
        chess Q = new chess(8);
        Q.callplaceNqueens();

    }

}