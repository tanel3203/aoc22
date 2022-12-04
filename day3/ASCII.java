import java.io.BufferedReader;
import java.io.FileReader;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.lang.Integer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class ASCII {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("./inputs.txt"))) {
            String line;
            int res = 0;
            while ((line = reader.readLine()) != null) {
                List<String> f = Arrays.asList(line.substring(0,line.length()/2).split(""));
                List<String> s = Arrays.asList(line.substring(line.length()/2, line.length()).split(""));
                int val = ((byte) f.stream().filter(p -> s.contains(p)).collect(Collectors.toList()).get(0).charAt(0)) - 38;
                if (val > 52) val = val - 58;
                res = res + val;
            }
            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}