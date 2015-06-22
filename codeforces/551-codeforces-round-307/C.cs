using System;

public class C
{
    int[] ReadInts()
    {
        string[] tokens = Console.ReadLine().Split();
        int n = tokens.Length;
        int[] ints = new int[n];
        for (int i = 0; i < n; ++ i) {
            ints[i] = int.Parse(tokens[i]);
        }
        return ints;
    }

    bool Check(int m, int[] a, long t)
    {
        long left = 0;
        for (int i = a.Length - 1; i >= 0; -- i) {
            if (left < a[i]) {
                long p = t - i - 1;
                if (p <= 0) {
                    return false;
                }
                long n = (a[i] - left + p - 1) / p;
                if (n > m) {
                    return false;
                }
                m -= (int)n;
                left += n * p;
            }
            left -= a[i];
        }
        return true;
    }

    public void Run()
    {
        int m = ReadInts()[1];
        int[] a = ReadInts();
        int n = a.Length;
        long low  = 0;
        long high = n;
        for (int i = 0; i < n; ++ i) {
            high += a[i];
        }
        while (low + 1 < high)
        {
            long middle = low + high >> 1;
            if (Check(m, a, middle)) {
                high = middle;
            } else {
                low = middle;
            }
        }
        Console.WriteLine(high);
    }

    public static void Main(string[] args)
    {
        new C().Run();
    }
}
