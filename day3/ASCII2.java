import java.io.BufferedReader;
import java.io.FileReader;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.lang.Integer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class ASCII2 {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("./inputs.txt"))) {
            String line;
            int res = 0;
            int lineNr = 0;
            String first = "";
            String second = "";
            String third = "";
            while ((line = reader.readLine()) != null) {
                lineNr = lineNr + 1;
                if (lineNr%3==1) first = line;
                if (lineNr%3==2) second = line;
                if (lineNr%3==0) {
                    third = line;
                    List<String> f = Arrays.asList(first.split(""));
                    List<String> s = Arrays.asList(second.split(""));
                    List<String> t = Arrays.asList(third.split(""));
                    String r = f.stream().filter(p -> s.contains(p) && t.contains(p)).collect(Collectors.toList()).get(0);
                    int val = ((byte) r.charAt(0)) - 38;
                    if (val > 52) val = val - 58;
                    res = res + val;
                };
            }
            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}