package algo;

import java.util.*;

class Solution {
    public int openLock(String[] deadends, String target) {
        if (target.equals("0000")) return 0;
        Set<String> deads = new HashSet<>();
        for (String d : deadends) {
            if (d.equals("0000")) return -1;
            deads.add(d);
        }
        deads.add("0000");
        PriorityQueue<Astar> queue = new PriorityQueue<>((a, b) -> a.f - b.f);
        queue.offer(new Astar("0000", target, 0));
        while (!queue.isEmpty()) {
            Astar node = queue.poll();
            for (String status : get(node.status)) {
                if (!deads.contains(status)) {
                    if (status.equals(target)) return node.g + 1;
                    queue.offer(new Astar(status, target, node.g + 1));
                    deads.add(status);
                }
            }
        }
        return -1;
    }

    public char numPrev(char x) {
        return x == '0' ? '9' : (char) (x - 1);
    }

    public char numSucc(char x) {
        return x == '9' ? '0' : (char) (x + 1);
    }

    public List<String> get(String status) {
        List<String> ret = new ArrayList<String>();
        char[] array = status.toCharArray();
        for (int i = 0; i < 4; ++i) {
            char num = array[i];
            array[i] = numPrev(num);
            ret.add(new String(array));
            array[i] = numSucc(num);
            ret.add(new String(array));
            array[i] = num;
        }
        return ret;
    }

    private class Astar {
        String status;
        int f, g, h;

        Astar(String status, String target, int g) {
            this.status = status;
            this.g = g;
            this.h = getH(status, target);
            this.f = this.g + this.h;
        }

        private int getH(String status, String target) {
            int cost = 0;
            for (int index = 0; index < 4; index++) {
                int dist = Math.abs(status.charAt(index) - target.charAt(index));
                cost += Math.min(dist, 10 - dist);
            }
            return cost;
        }
    }
}