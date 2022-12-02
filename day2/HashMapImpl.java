import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;

class HashMapImpl {
    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        map.put("X", 1);
        map.put("Y", 2);
        map.put("Z", 3);

        int result = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("./inputs.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int p1 = (int) map.get(line.substring(0,1));
                int p2 = (int) map.get(line.substring(2,3));
                if (p1 == p2) result = result + p2 + 3;
                else if (p2 == 3 && p1 == 2) result = result + p2 + 6;
                else if (p2 == 2 && p1 == 1) result = result + p2 + 6;
                else if (p2 == 1 && p1 == 3) result = result + p2 + 6;
                else result = result + p2 + 0;
            }
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}