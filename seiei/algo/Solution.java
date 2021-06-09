package algo;

class Solution {
    public static void reverseWords(char[] s) {
        int len = s.length;
        if (len <= 0) return;
        int head = 0, tail = len - 1;
        // 先翻转字符串
        while (head < tail) {
            char temp = s[head];
            s[head] = s[tail];
            s[tail] = temp;
            head++;
            tail--;
        }

        // 再翻转单词
        head = 0;
        tail = 0;
        while (tail < len) {
            if (s[tail] == ' ') {
                reverse(s, head, tail);
                head = tail + 1;
            }
            tail++;
        }
        reverse(s, head, tail);
    }

    private static void reverse(char[] s, int wordHead, int wordTail) {
        wordTail--;
        while (wordHead < wordTail) {
            char temp = s[wordHead];
            s[wordHead] = s[wordTail];
            s[wordTail] = temp;
            wordHead++;
            wordTail--;
        }
    }

    public static void main(String[] args) {
        String str = "the sky is blue";
        char[] array = str.toCharArray();
        for (char c: array) System.out.print(c);
        System.out.println();
        reverseWords(array);
        for (char c: array) System.out.print(c);
    }
}