// assumption: the cd actions move to valid dirs (no validation here)
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.TreeMap;
import java.lang.Integer;
import java.util.Map;

class TreeMapImpl {
    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new FileReader("./inputs.txt"))) {
            String line;
            TreeMap<String, Integer> map = new TreeMap<>();
            String currentPath = "./root";
            map.put(currentPath, 0);
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(" ");
                if (tokens[0].equals("$")) {
                    if (tokens[1].equals("cd")) {
                        if (tokens[2].equals("..")) currentPath = currentPath.substring(0, currentPath.lastIndexOf("/"));
                        else if (tokens[2].equals("/")) currentPath = "./root";
                        else {
                            currentPath = currentPath + "/" + tokens[2];
                            map.put(currentPath, 0);
                        }
                    };
                } else if (!tokens[0].equals("dir")) {
                    String parent = currentPath;
                    int traversals = currentPath.replaceAll("[^/]", "").length();
                    for (int i = 0; i < traversals; i++) {
                        map.put(parent, map.get(parent) + Integer.parseInt(tokens[0]));
                        parent = parent.substring(0, parent.lastIndexOf("/"));
                    }
                }
            }
            int fin = 0;
            for (Map.Entry<String,Integer> entry : map.entrySet()) {
                if (entry.getValue() <= 100000) fin = fin + entry.getValue();
            }
            System.out.println(fin);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}