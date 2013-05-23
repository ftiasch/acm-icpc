// School Team Contest #3
// Problem G -- Prime Problem
#include <cstdio>
#include <cstring>

const int N = 6000;

int n, group[N + 1];

bool is_prime(int n) {
    if (n <= 1) {
        return false;
    }
    for (int i = 2; i * i <= n; ++ i) {
        if (n % i == 0) {
            return false;
        }
    }
    return true;
}

int main() {
    scanf("%d", &n);
    int sum = n * (n + 1) / 2;
    for (int i = 1; i <= n; ++ i) {
        group[i] = 1;
    }
    if (!is_prime(sum)) {
        if (is_prime(sum - 2)) {
            group[2] = 2;
        } else {
            if (sum % 2 == 1) {
                int p = n;
                while (!is_prime(p)) {
                    p --;
                }
                group[p] = 3;
                sum -= p;
            } 
            int p = 2;
            while (!is_prime(p) || !is_prime(sum - p)) {
                p ++;
            }
            for (int i = n; i >= 1; -- i) {
                if (group[i] == 1 && p >= i) {
                    p -= i;
                    group[i] = 2;
                }
            }
        }
    }
    for (int i = 1; i <= n; ++ i) {
        printf("%d%c", group[i], " \n"[i == n]);
    }
    return 0;
}
