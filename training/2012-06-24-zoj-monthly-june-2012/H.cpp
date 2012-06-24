// Problem H -- Hash Function
#include <cstdio>
#include <cstring>
#include <map>
using namespace std;

const int N = 11111;

int base, prime, target;
char text[N];
map <int, int> hash;

int pow_mod(int a, int n, int m) {
    int result = 1 % m;
    while (n > 0) {
        if ((n & 1) == 1) {
            result = (long long)result * a % m;
        }
        a = (long long)a * a % m;
        n >>= 1;
    }
    return result;
}

int main() {
    int test_count;
    scanf("%d", &test_count);
    while (test_count --) {
        fgets(text, N, stdin);
        fgets(text, N, stdin);
        int n = strlen(text) - 1;
        scanf("%d%d%d", &base, &prime, &target);
        if (base == 0) {
            int result = 0;
            for (int i = 0; i < n; ++ i) {
                if (text[i] % prime == target) {
                    result += i + 1;
                }
            }
            printf("%d\n", result);
        } else {
            int result = 0;
            hash.clear();
            int times = 1;
            int delta = 0;
            for (int i = 0; i < n; ++ i) {
                times = (long long)times * base % prime;
                delta = (long long)delta * base % prime;
                delta = ((long long)delta + text[i]) % prime;
                hash[((long long)text[i] + prime - delta) % prime * pow_mod(times, prime - 2, prime) % prime] ++;
                result += hash[((long long)target + prime - delta) % prime * pow_mod(times, prime - 2, prime) % prime];
            }
            printf("%d\n", result);
        }
    }
    return 0;
}
