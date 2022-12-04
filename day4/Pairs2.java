import java.io.BufferedReader;
import java.io.FileReader;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.lang.Integer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Pairs2 {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("./inputs.txt"))) {
            String line;
            int res = 0;
            while ((line = reader.readLine()) != null) {
                String first = line.substring(0,line.indexOf(","));
                String second = line.substring(line.indexOf(",")+1, line.length());
                int start1 = Integer.parseInt(first.substring(0, first.indexOf("-")));
                int end1 = Integer.parseInt(first.substring(first.indexOf("-")+1, first.length()));
                int start2 = Integer.parseInt(second.substring(0, second.indexOf("-")));
                int end2 = Integer.parseInt(second.substring(second.indexOf("-")+1, second.length()));
                if (start1 <= start2 && end1 >= start2) res = res + 1;
                else if (start1 <= end2 && end1 >= end2)  res = res + 1;
                else if (start2 <= start1 && end2 >= start1) res = res + 1;
                else if (start2 <= end1 && end2 >= end1)  res = res + 1;
            }
            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}