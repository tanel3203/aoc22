//same as SortedMapImpl.java just cleaned up
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.lang.Integer;

class NavigableMapImpl {
    public static void main(String[] args) {
        NavigableMap map = new TreeMap();
        try (BufferedReader reader = new BufferedReader(new FileReader("./inputs.txt"))) {
            String line;
            int currentSum = 0;
            int currentItem = 1;
            while ((line = reader.readLine()) != null) {
                if (line.length() == 0) {
                    map.put(currentSum, currentItem);
                    currentSum = 0;
                    currentItem += 1;
                } else {
                    currentSum += Integer.parseInt(line);
                }
            }
            map.forEach((k,v) -> System.out.println(k + "-" + v));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}