// Northeastern Europe 2007
// Problem E -- Equation
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Stack;
import java.util.ArrayList;

class Polynomial {
    final static Polynomial ONE = new Polynomial(0, 1);

    long a, b;

    Polynomial(long a, long b) {
        this.a = a;
        this.b = b;
    }

    Polynomial add(Polynomial that) {
        return new Polynomial(this.a + that.a, this.b + that.b);
    }

    Polynomial multiply(Polynomial that) {
        return new Polynomial(this.a * that.b + that.a * this.b, this.b * that.b);
    }

    Polynomial negate() {
        return new Polynomial(-a, -b);
    }
}

class Fraction {
    Polynomial p, q;

    Fraction(long n) {
        this.p = new Polynomial(0, n);
        this.q = Polynomial.ONE;
    }

    Fraction(Polynomial p, Polynomial q) {
        this.p = p;
        this.q = q;
    }

    Fraction add(Fraction that) {
        return new Fraction(this.p.multiply(that.q).add(that.p.multiply(this.q)), this.q.multiply(that.q));
    }

    Fraction negate() {
        return new Fraction(p.negate(), q);
    }

    Fraction subtract(Fraction that) {
        return this.add(that.negate());
    }

    Fraction multiply(Fraction that) {
        return new Fraction(this.p.multiply(that.p), this.q.multiply(that.q));
    }
    
    Fraction reciprocal() {
        return new Fraction(q, p);
    }

    Fraction divide(Fraction that) {
        return this.multiply(that.reciprocal());
    }
}

class Solver {
    long gcd(long a, long b) {
        return b == 0? a: gcd(b, a % b);
    }

    void run(InputReader in, PrintWriter out) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());
        ArrayList <Polynomial> denominators = new ArrayList <Polynomial>();
        Stack <Fraction> stack = new Stack <Fraction>();
        while (tokenizer.hasMoreTokens()) {
            char token = tokenizer.nextToken().charAt(0);
            if (token == 'X') {
                stack.push(new Fraction(new Polynomial(1, 0), Polynomial.ONE));
            } else if ('0' <= token && token <= '9') {
                stack.push(new Fraction(new Polynomial(0, token - '0'), Polynomial.ONE));
            } else {
                Fraction rightValue = stack.pop();
                Fraction leftValue = stack.pop();
                if (token == '+') {
                    stack.push(leftValue.add(rightValue));
                }
                if (token == '-') {
                    stack.push(leftValue.subtract(rightValue));
                }
                if (token == '*') {
                    stack.push(leftValue.multiply(rightValue));
                }
                if (token == '/') {
                    stack.push(leftValue.divide(rightValue));
                    denominators.add(rightValue.p);
                }
            }
        }
        Polynomial finalExpresssion = stack.pop().p;
        if (finalExpresssion.a == 0) {
            if (finalExpresssion.b == 0) {
                out.println("MULTIPLE");
            } else {
                out.println("NONE");
            }
        } else {
            Fraction solution = new Fraction(new Polynomial(0, -finalExpresssion.b), new Polynomial(0, finalExpresssion.a));
            boolean check = true;
            for (Polynomial denominator : denominators) {
                if ((new Fraction(denominator.a).multiply(solution).add(new Fraction(denominator.b))).p.b == 0) {
                    check = false;
                }
            }
            if (check) {
                long p = solution.p.b;
                long q = solution.q.b;
                long d = gcd(p, q);
                p /= d;
                q /= d;
                if (q < 0) {
                    p *= -1;
                    q *= -1;
                }
                out.printf("X = %d/%d\n", p, q);
            } else {
                out.println("NONE");
            }
        }
        out.flush();
    }
}

public class Main {
    public static void main(String args[]) {
        try {
            new Solver().run(new InputReader(System.in), new PrintWriter(System.out));
        } catch (IOException e) {
        }
    }
}

class InputReader {
    BufferedReader reader;

    InputReader(InputStream in) {
        reader = new BufferedReader(new InputStreamReader(in));
    }

    String readLine() throws IOException {
        return reader.readLine();
    }
}
