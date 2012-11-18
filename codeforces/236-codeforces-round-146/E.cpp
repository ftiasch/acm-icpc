// Codeforces Round #146
// Problem E -- Number Challenge
#include <cstdio>
#include <cstring>
#include <ext/hash_map>
#include <vector>
#include <algorithm>
using namespace std;
using namespace __gnu_cxx;

const int N = 2000;

int a, b, c;

bool is_prime(int n) {
    for (int i = 2; i * i <= n; ++ i) {
        if (n % i == 0) {
            return false;
        }
    }
    return true;
}

vector <int> primes;

struct Data {
    int a, b, c, p;

    Data(int a, int b, int c, int p) : a(a), b(b), c(c), p(p) {}
};

bool operator == (const Data &x, const Data &y) {
    return x.a == y.a && x.b == y.b && x.c == y.c && x.p == y.p;
}

struct Hash {
    int operator()(const Data &o) const {
        return ((o.a * (N + 1) + o.b) * (N + 1) + o.c) * (N + 1) + o.p;
    }
};

hash_map <Data, unsigned, Hash> memory;

unsigned solve(int a, int b, int c, int id) {
    if (id < 0) {
        return 1;
    }
    Data d(a, b, c, id);
    if (memory.find(d) == memory.end()) {
        int p = primes[id];
        unsigned &answer = memory[d] = 0;
        for (int i = a, x = 0; i > 0; ++ x, i /= p) {
            for (int j = b, y = 0; j > 0; ++ y, j /= p) {
                for (int k = c, z = 0; k > 0; ++ z, k /= p) {
                    answer += solve(i, j, k, id - 1) * (x + y + z + 1);
                }
            }
        }
    }
    return memory[d];
}

int main() {
    scanf("%d%d%d", &a, &b, &c);
    int max_p = max(max(a, b), c);
    for (int p = 2; p <= max_p; ++ p) {
        if (is_prime(p)) {
            primes.push_back(p);
        }
    }
    printf("%u\n", solve(a, b, c, (int)primes.size() - 1) & (1 << 30) - 1);
    return 0;
}
