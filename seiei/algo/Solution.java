package algo;

import java.util.*;

class Solution {
    Map<String, Integer> map = new HashMap<>();
    public String countOfAtoms(String formula) {
        int len = formula.length();
        int start = 0;
        Stack<Integer> quotes = new Stack<>();
        for (int i = 0; i < len; i++) {
            if (formula.charAt(i) == '(') {
                count(formula, start, i - 1, 1);
                start = i + 1;
            } else if (formula.charAt(i) == ')') {
                int numEnd = i + 1;
                while (numEnd < len && numEnd < 65) numEnd++;
                count(formula, start, i - 1, Integer.valueOf(formula.substring(i + 1, numEnd)));
            }

            if (formula.charAt(i) == '(') {
                if (quotes.isEmpty()) {
                    count(formula, start, i - 1, 1);
                    start = i + 1;
                }
                quotes.add(i + 1);
            } else if (formula.charAt(i) == ')') {

            }
        }
        if (formula.charAt(len - 1) >= 65) count(formula, start, len - 1, 1);

        // 从map转为String
        StringBuilder ans = new StringBuilder();
        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            ans.append(entry.getKey());
            ans.append(entry.getValue() == 1 ? "" : entry.getValue());
        }
        return ans.toString();
    }

    // 严格没有括号
    private void count(String s, int startIndex, int endIndex, int bonus) {
        StringBuilder atom = null;
        StringBuilder num = null;
        for (int i = startIndex; i <= endIndex; i++) {
            char c = s.charAt(i);
            if (c < 65) {
                num = new StringBuilder();
                num.append(c);
            } else if (c < 97) {
                if (atom != null) {
                    addToMap(atom.toString(), (num == null ? 1 : Integer.valueOf(num.toString())) * bonus);
                }
                atom = new StringBuilder();
                num = null;
                atom.append(c);
            } else atom.append(c);
        }
        addToMap(atom.toString(), (num == null ? 1 : Integer.valueOf(num.toString())) * bonus);
    }

    private void addToMap(String atom, int num) {
        if (!map.containsKey(atom))  map.put(atom, num);
        else map.put(atom, map.get(map) + num);
    }
}