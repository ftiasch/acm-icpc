// Codechef April Challenge 2012
// Greatest Dumpling Fight
import java.io.*;
import java.math.*;
import java.util.*;

public class Main implements Runnable {
    long gcd(long a, long b) {
        return b == 0? a: gcd(b, a % b);
    }

    BigInteger lcm(long a, long b) {
        return BigInteger.valueOf(a / gcd(a, b)).multiply(BigInteger.valueOf(b));
    }

    public void run() {
        try {
            Scanner scanner = new Scanner(System.in);
            int testCount = scanner.nextInt();
            while (testCount > 0) {
                testCount --;
                long a = scanner.nextLong();
                long b = scanner.nextLong();
                long c = scanner.nextLong();
                long d = scanner.nextLong();
                long k = scanner.nextLong();
                System.out.println(BigInteger.valueOf(k).divide(lcm(gcd(a, b), gcd(c, d))).longValue() * 2 + 1);
            }
        } catch (Exception e) {
        }
    }

    public static void main(String args[]) {
        new Thread(new Main()).run();
    }
}
