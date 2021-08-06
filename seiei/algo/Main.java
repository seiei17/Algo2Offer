package algo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> factors = new ArrayList<>();
        int t = n;
        while (t != 1) {
            for (int i = 2; i <= t; i++) {
                if (isMatter(i)) {
                    if (t % i == 0) {
                        factors.add(i);
                        t /= i;
                        break;
                    }
                }
            }
        }
        System.out.print(n + "=");
        for (int i = 0; i < factors.size(); i++) {
            if (i != 0) System.out.print("*");
            System.out.print(factors.get(i));
        }
    }

    private static boolean isMatter(int num) {
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
