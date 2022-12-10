import java.io.FileReader;

class Recurse {

    static final int l = 99;
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("./inputs.txt")) {
            int n;
            char[][] matrix = new char[l][l];
            int i = 0;
            int j = 0;
            while ((n = reader.read()) != -1) {
                char t = (char) n;
                if (t >= '0') {
                    matrix[i][j] = t;
                    if (i == l-1) {
                        i = 0;
                        j = j + 1;
                    } else i = i + 1;
                }
            }
            int vis = 0;
            for (int z = 0; z <= l-1; z++) {
                for (int k = 0; k <= l-1; k++) {
                    if (recurse(z,k,matrix)) {
                        vis = vis + 1;
                    }
                }
            }
            System.out.println(vis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean recurse(int currX, int currY, char[][] matrix) {
        char currValue = matrix[currX][currY];
        boolean up = recurse(0, -1, currX, currY-1, matrix, currValue);
        boolean down = recurse(0, 1, currX, currY+1, matrix, currValue);
        boolean right = recurse(1, 0, currX+1, currY, matrix, currValue);
        boolean left = recurse(-1, 0, currX-1, currY, matrix, currValue);
        return up || down || right || left;
    }

    private static boolean recurse(int byX, int byY, int currX, int currY, char[][] matrix, char start) {
        if (currX < 0 || currY < 0 || currX > l-1 || currY > l-1) return true;
        if (start <= matrix[currX][currY]) return false;
        if (currX == 0 || currY == 0 || currX == l-1 || currY == l-1) return start > matrix[currX][currY];
        return recurse(byX, byY, currX+byX, currY+byY, matrix, start);
    }
}