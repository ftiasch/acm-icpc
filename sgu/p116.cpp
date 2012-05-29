// SGU 116 -- Index of super-prime
#include <cstdio>
#include <cstring>
#include <vector>
#include <functional>
#include <algorithm>
using namespace std;

bool is_prime(int n) {
    if (n == 1) {
        return false;
    }
    for (int i = 2; i * i <= n; ++ i) {
        if (n % i == 0) {
            return false;
        }
    }
    return true;
}

const int N = 10001;
const int INF = 1000000000;

int n, minimum[N], choice[N];
vector <int> super_primes;

int main() {
    scanf("%d", &n);
    int prime_count = 0;
    for (int i = 1; i <= n; ++ i) {
        if (is_prime(i)) {
            prime_count ++;
            if (is_prime(prime_count)) {
                super_primes.push_back(i);
            }
        }
    }
    minimum[0] = 0;
    for (int i = 1; i <= n; ++ i) {
        minimum[i] = INF;
        for (vector <int> :: iterator iter = super_primes.begin(); 
                iter != super_primes.end(); ++ iter) {
            if (*iter <= i && minimum[i - *iter] + 1 < minimum[i]) {
                choice[i] = *iter;
                minimum[i] = minimum[i - *iter] + 1;
            }
        }
    }
    if (minimum[n] == INF) {
        puts("0");
    } else {
        printf("%d\n", minimum[n]);
        vector <int> result;
        for (int i = n; i > 0; i -= choice[i]) {
            result.push_back(choice[i]);
        }
        sort(result.begin(), result.end(), greater <int>());
        for (int i = 0; i < (int)result.size(); ++ i) {
            printf("%d%c", result[i], i == (int)result.size() - 1? '\n': ' ');
        }

    }
    return 0;
}
