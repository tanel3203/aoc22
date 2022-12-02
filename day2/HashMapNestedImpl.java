import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;

class HashMapNestedImpl {
    public static void main(String[] args) {
        HashMap loseMap = new HashMap();
        loseMap.put("A", 0 + 3);
        loseMap.put("B", 0 + 1);
        loseMap.put("C", 0 + 2);

        HashMap drawMap = new HashMap();
        drawMap.put("A", 3 + 1);
        drawMap.put("B", 3 + 2);
        drawMap.put("C", 3 + 3);

        HashMap winMap = new HashMap();
        winMap.put("A", 6 + 2);
        winMap.put("B", 6 + 3);
        winMap.put("C", 6 + 1);

        HashMap map = new HashMap();
        map.put("X", loseMap);
        map.put("Y", drawMap);
        map.put("Z", winMap);

        int result = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("./inputs.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                HashMap r1 = (HashMap) map.get(line.substring(2,3));
                int r2 = (int) r1.get(line.substring(0,1));
                result = result + r2;
            }
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}