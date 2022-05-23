import java.util.*;

class File implements Comparable<File> {
    String head;
    String number;
    String tail;
    int order;
    
    File(String head, String number, String tail, int order) {
        this.head = head;
        this.number = number;
        this.tail = tail;
        this.order = order;
    }
    
    @Override
    public int compareTo(File f) {
        if(this.head.compareToIgnoreCase(f.head) > 0) {
            return 1;
        }
        else if(this.head.compareToIgnoreCase(f.head) == 0) {
            if(Integer.parseInt(this.number) > Integer.parseInt(f.number)) {
                return 1;
            }
            else if(Integer.parseInt(this.number) == Integer.parseInt(f.number)) {
                if(this.order > f.order) {
                    return 1;
                }
            }
        }
        return -1;
    }
}

class Solution {
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        PriorityQueue<File> pq = new PriorityQueue<File>();
        for(int i=0; i<files.length; i++) {
            String curFile = files[i];
            StringBuilder headBuilder = new StringBuilder();
            StringBuilder numBuilder = new StringBuilder();
            StringBuilder tailBuilder = new StringBuilder();
            boolean headFlag = false;
            boolean numFlag = false;
            
            for(int j=0; j<curFile.length(); j++) {
                if(!headFlag && !Character.isDigit(curFile.charAt(j))) {
                    headBuilder.append(curFile.charAt(j));
                }
                else if(!numFlag && Character.isDigit(curFile.charAt(j))) {
                    headFlag = true;
                    numBuilder.append(curFile.charAt(j));
                }
                else if(headFlag && !Character.isDigit(curFile.charAt(j))) {
                    numFlag = true;
                    tailBuilder.append(curFile.charAt(j));
                }
                else {
                    tailBuilder.append(curFile.charAt(j));
                }
            }
            pq.add(new File(headBuilder.toString(), numBuilder.toString(), tailBuilder.toString(), i));
        }
        
        int idx = 0;
        while(!pq.isEmpty()) {
            File f = pq.poll();
            StringBuilder sb = new StringBuilder(f.head);
            sb.append(f.number);
            sb.append(f.tail);
            answer[idx++] = sb.toString();
        }
        
        return answer;
    }
}
