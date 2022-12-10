import java.io.FileReader;
import java.util.TreeSet;
import java.lang.Integer;

class Recurse2 {

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
            TreeSet<Integer> scores = new TreeSet<>();
            for (int z = 0; z <= l-1; z++) {
                for (int k = 0; k <= l-1; k++) {
                    int sco = recurse(z,k,matrix);
                    scores.add(sco);
                }
            }
            System.out.println(scores);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int recurse(int currX, int currY, char[][] matrix) {
        char currValue = matrix[currX][currY];
        int up = recurse(1,0, -1, currX, currY-1, matrix, currValue);
        int down = recurse(1,0, 1, currX, currY+1, matrix, currValue);
        int right = recurse(1,1, 0, currX+1, currY, matrix, currValue);
        int left = recurse(1,-1, 0, currX-1, currY, matrix, currValue);
        return up * down * right * left;
    }

    private static int recurse(int score, int byX, int byY, int currX, int currY, char[][] matrix, char start) {
        if (currX < 0 || currY < 0 || currX > l-1 || currY > l-1) return 0;
        if (start <= matrix[currX][currY]) return 1;
        if (currX == 0 || currY == 0 || currX == l-1 || currY == l-1) return 1;
        return score + recurse(score,byX, byY, currX+byX, currY+byY, matrix, start);
    }
}