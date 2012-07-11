// Problem A -- Fraction
#include <cstdio>
#include <cstring>
#include <vector>
using namespace std;

char getCode(int n) {
    return n < 10? '0' + n: 'A' + n - 10;
}

void print(int n, int k) {
    if (n > 0) {
        print(n / k, k);
        putchar(getCode(n % k));
    }
}

const int M = 10000;

int occurance[M];

int main() {
    while (true) {
        int a, b;
        scanf("%d%d", &a, &b);
        if (a == 0 && b == 0) {
            break;
        }
        int k;
        scanf("%d", &k);
        if (a / b == 0) {
            putchar('0');
        } else {
            print(a / b, k);
        }
        if (a % b != 0) {
            putchar('.');
            memset(occurance, -1, sizeof(occurance));
            vector <int> period;
            int remainder = a % b;
            bool found = false;
            for (int i = 0; remainder != 0; ++ i) {
                if (occurance[remainder] != -1) {
                    found = true;
                    for (int i = 0; i < occurance[remainder]; ++ i) {
                        putchar(getCode(period[i]));
                    }
                    putchar('(');
                    for (int i = occurance[remainder]; i < (int)period.size(); ++ i) {
                        putchar(getCode(period[i]));
                    }
                    putchar(')');
                    break;
                }
                occurance[remainder] = i;
                remainder *= k;
                period.push_back(remainder / b);
                remainder %= b;
            }
            if (!found) {
                for (int i = 0; i < (int)period.size(); ++ i) {
                    putchar(getCode(period[i]));
                }
            }
        }
        puts("");
    }
    return 0;
}
