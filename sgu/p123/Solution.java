// SGU 123 -- The sum
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.math.BigInteger;

public class Solution {
    public static void main(String args[]) throws IOException {
        int n = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        if (n == 1) {
            System.out.println(BigInteger.ONE);
        } else {
            BigInteger[] fibonacci = new BigInteger[n + 1];
            fibonacci[1] = fibonacci[2] = BigInteger.ONE;
            for (int i = 3; i <= n; ++ i) {
                fibonacci[i] = fibonacci[i - 1].add(fibonacci[i - 2]);
            }
            BigInteger result = BigInteger.ZERO;
            for (int i = 1; i <= n; ++ i) {
                result = result.add(fibonacci[i]);
            }
            System.out.println(result);
        }
    }
}
