// assumption: the cd actions move to valid dirs (no validation here)
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.TreeMap;
import java.lang.Integer;
import java.util.Map;
import java.util.TreeSet;

class TreesImpl {
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
                        if (tokens[2].equals("..")) {
                            currentPath = currentPath.substring(0, currentPath.lastIndexOf("/"));
                        } else if (tokens[2].equals("/")) {
                            currentPath = "./root";
                        } else {
                            currentPath = currentPath + "/" + tokens[2];
                            map.put(currentPath, 0);
                        }
                    };
                } else {
                    if (!tokens[0].equals("dir")) {
                        int total = map.get(currentPath);
                        total = total + Integer.parseInt(tokens[0]);
                        map.put(currentPath, total);
                        String parent = currentPath.substring(0, currentPath.lastIndexOf("/"));
                        int traversals = currentPath.replaceAll("[^/]", "").length()-1;
                        for (int i = 0; i < traversals; i++) {
                            int total2 = map.get(parent);
                            total2 = total2 + Integer.parseInt(tokens[0]);
                            map.put(parent, total2);
                            parent = parent.substring(0, parent.lastIndexOf("/"));
                        }
                    }
                }
            }
            TreeSet<Integer> sizes = new TreeSet<>();
            for (Map.Entry<String,Integer> entry : map.entrySet()) {
                sizes.add(entry.getValue());
            }
            System.out.println(sizes.ceiling(30_000_000 - (70_000_000 - map.get("./root"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}