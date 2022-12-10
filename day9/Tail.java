import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.Integer;
import java.lang.Math;
import java.util.Set;
import java.util.HashSet;

class Tail {
    public static void main(String[] args) {

        Set<String> places = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("./inputs.txt"))) {
            String line;
            int startX = 0;
            int startY = 0;
            int headX = 0;
            int headY = 0;
            int tailX = 0;
            int tailY = 0;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(" ");
                int moveX = 0;
                int moveY = 0;
                int moves = Integer.parseInt(tokens[1]);
                if (tokens[0].equals("R") || tokens[0].equals("L")) {
                    moveX = tokens[0].equals("L") ? -moves : moves;
                    moveY = 0;
                } else {
                    moveX = 0;
                    moveY = tokens[0].equals("U") ? -moves : moves;
                }
                if (tokens[0].equals("R") || tokens[0].equals("L")) {
                    for (int i = 0; i < Math.abs(moveX); i++) {
                        headX = moveX < 0 ? headX-1 : headX+1;
                        if (Math.abs(Math.abs(headX)-Math.abs(tailX)) > 1 || (headX > 0 && tailX < 0) || (headX < 0 && tailX > 0)) {
                            if (headY != tailY) {
                                tailX = headX < tailX ? tailX-1 : tailX+1;
                                tailY = headY < tailY ? tailY-1 : tailY+1;
                            } else tailX = moveX < 0 ? tailX-1 : tailX+1;
                        }
                        places.add(tailX+":"+tailY);
                    }
                } else {
                    for (int i = 0; i < Math.abs(moveY); i++) {
                        headY = moveY < 0 ? headY-1 : headY+1;
                        if (Math.abs(Math.abs(headY)-Math.abs(tailY)) > 1  || (headY > 0 && tailY < 0) || (headY < 0 && tailY > 0)) {
                            if (headX != tailX) {
                                tailX = headX < tailX ? tailX-1 : tailX+1;
                                tailY = headY < tailY ? tailY-1 : tailY+1;
                            } else tailY = moveY < 0 ? tailY-1 : tailY+1;
                        }
                        places.add(tailX+":"+tailY);
                    }
                }
            }
            System.out.println(places.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}