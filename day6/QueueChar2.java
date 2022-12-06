// identical copy of QueueChar.java, except value 4 changed to 14
import java.io.FileReader;
import java.util.Queue;
import java.util.LinkedList;
import java.lang.Character;
import java.util.Set;
import java.util.HashSet;

class QueueChar2 {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("./inputs.txt")) {
            Queue<Character> fifo = new LinkedList<>();
            int c;
            int counter = 0;
            while ((c = reader.read()) != -1) {
                counter = counter + 1;
                fifo.add((char) c);
                if (fifo.size() > 14) {
                    fifo.poll();
                    Set<Character> uniques = new HashSet<Character>(fifo);
                    if (uniques.size() == 14) break;
                }
            }
            System.out.println(counter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}