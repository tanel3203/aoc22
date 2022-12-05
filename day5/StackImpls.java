import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Stack;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

class StackImpls {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("./inputs.txt"))) {
            String line;
            List<String> s = new ArrayList();
            boolean trigger = false;
            HashMap<Integer, Stack<String>> map = new HashMap();
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    trigger = true;
                    String last = s.get(s.size()-1);
                    int columns = Integer.parseInt(last.substring(last.length()-3, last.length()).replaceAll(" ", ""))-1;
                    for (int i = s.size()-1-1; i >= 0; i--) {
                        int currentPos = 1;
                        for (int j = 0; j <= columns; j++) {
                            if (currentPos <= s.get(i).length()-1) {
                                Stack<String> cur = map.get(j);
                                if (cur == null) {
                                    cur = new Stack();
                                }
                                String currElem = s.get(i).substring(currentPos,currentPos+1);
                                if (currElem.replaceAll(" ", "").length() != 0) {
                                    cur.push(currElem);
                                    map.put(j, cur);
                                }
                                currentPos = currentPos + 4;
                            }
                        }
                    }
                } else if (!trigger) {
                    s.add(line);
                } else {
                    int moves = Integer.parseInt(line.substring(5, line.indexOf(" f")));
                    int from = Integer.parseInt(line.substring(line.indexOf("m ")+2,line.indexOf(" t")))-1;
                    int to = Integer.parseInt(line.substring(line.indexOf("o ")+2, line.length()))-1;
                    Stack<String> intermediate = new Stack();
                    for (int i = 1; i <= moves; i++) {
                        String gotElem = map.get(from).pop();
                        intermediate.push(gotElem);
                    }
                    Stack<String> target = map.get(to);
                    int size = intermediate.size();
                    for (int i = 0; i < size; i++) {
                        String currr = intermediate.pop();
                        target.push(currr);
                    }
                    map.put(to,target);
                }
            }
            for (Map.Entry<Integer,Stack<String>> entry : map.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}