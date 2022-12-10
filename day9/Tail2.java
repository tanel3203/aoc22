import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.Integer;
import java.lang.Math;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

class Tail2 {
    public static void main(String[] args) {

        Set<String> places = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("./inputs.txt"))) {
            String line;
            int elems = 10;
            int[][] l = new int[elems][2];
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(" ");
                int moveX = 0;
                int moveY = 0;
                int m = Integer.parseInt(tokens[1]);
                if (tokens[0].equals("R") || tokens[0].equals("L")) {
                    moveX = tokens[0].equals("L") ? -m : m;
                    moveY = 0;
                } else {
                    moveX = 0;
                    moveY = tokens[0].equals("U") ? -m : m;
                }
                int moves = moveX == 0 ? Math.abs(moveY) : Math.abs(moveX);
                for (int step = 0; step < moves; step++) {
                    l[0][0] = moveX == 0 ? l[0][0] : moveX < 0 ? l[0][0]-1 : l[0][0]+1;
                    l[0][1] = moveY == 0 ? l[0][1] : moveY < 0 ? l[0][1]-1 : l[0][1]+1;
                    for (int elem = 1; elem < elems; elem++) {
                        int[] ret = move(l[elem-1], l[elem], moveX, moveY);
                        l[elem][0] = ret[0];
                        l[elem][1] = ret[1];
                        if (elem == elems-1) places.add(ret[0] + ":" + ret[1]);

                    }
                }
            }
            System.out.println(places.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int[] move(int[] headMoved, int[] tail, int moveX, int moveY) {
        int CONSTANT = 100_000;
        int headX = headMoved[0] + CONSTANT;
        int headY = headMoved[1] + CONSTANT;
        int tailX = tail[0] + CONSTANT;
        int tailY = tail[1] + CONSTANT;
        int[] ret = new int[2];
        ret[0] = tailX;
        ret[1] = tailY;
        boolean diagFar = (Math.abs(Math.abs(headX) - Math.abs(tailX)) + Math.abs(Math.abs(headY) - Math.abs(tailY))) > 2;
        if (headX != tailX && headY != tailY && diagFar) {
            ret[0] = headX < tailX ? tailX-1 : tailX+1;
            ret[1] = headY < tailY ? tailY-1 : tailY+1;
        } else if (headX != tailX && Math.abs((Math.abs(headX) - Math.abs(tailX))) > 1) {
            ret[0] = headX < tailX ? tailX-1 : tailX+1;
        } else if (headY != tailY && Math.abs((Math.abs(headY) - Math.abs(tailY))) > 1) {
            ret[1] = headY < tailY ? tailY-1 : tailY+1;
        }
        ret[0] = ret[0] - CONSTANT;
        ret[1] = ret[1] - CONSTANT;
        return ret;
    }
}