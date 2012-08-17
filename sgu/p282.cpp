// SGU 282 -- Isomorphism
#include <cstdio>
#include <cstring>
#include <vector>
#include <map>
using namespace std;

const int N = 53;
int n, m, p;
int choose[N + 1][N + 1];

void add(int &x, int a) {
    x += a;
    if (x >= p) {
        x -= p;
    }
}

vector <int> summation;
vector <vector <int> > summations;

void enumerate(int n, int low) {
    if (n == 0) {
        summations.push_back(summation);
    }
    for (int i = low; i <= n; ++ i) {
        summation.push_back(i);
        enumerate(n - i, i);
        summation.pop_back();
    }
}

int powMod(int a, int n, int p) {
    int ret = 1 % p;
    while (n) {
        if (n & 1) {
            ret = (long long)ret * a % p;
        }
        a = (long long)a * a % p;
        n >>= 1;
    }
    return ret;
}

int fact[N + 1], invFact[N + 1], gcd[N + 1][N + 1], power[N * (N - 1) / 2 + 1];

int solve(const vector <int> &summation) {
    int times = 1;
    int m = summation.size();
    for (int i = 0, left = n; i < m; ++ i) {
        times = (long long)times * choose[left][summation[i]] % p;
        times = (long long)times * fact[summation[i] - 1] % p;
        left -= summation[i];
    }
    for (int i = 0; i < m; ++ i) {
        int j = i;
        while (j < m && summation[i] == summation[j]) {
            j ++;
        }
        times = (long long)times * invFact[j - i] % p;
        i = j - 1;
    }
    int classCount = 0;
    for (int i = 0; i < m; ++ i) {
        for (int j = 0; j < i; ++ j) {
            classCount += gcd[summation[i]][summation[j]];
        }
        classCount += summation[i] / 2;
    }
    return (long long)times * power[classCount] % p;
}

int solveGCD(int a, int b) {
    return b? solveGCD(b, a % b): a;
}

int main() {
    scanf("%d%d%d", &n, &m, &p);
    enumerate(n, 1);
    memset(choose, 0, sizeof(choose));
    for (int i = 0; i <= n; ++ i) {
        choose[i][0] = 1 % p;
        for (int j = 1; j <= i; ++ j) {
            choose[i][j] = (choose[i - 1][j - 1] + choose[i - 1][j]) % p;
        }
    }
    fact[0] = invFact[0] = 1;
    for (int i = 1; i <= n; ++ i) {
        fact[i] = (long long)fact[i - 1] * i % p;
        invFact[i] = powMod(fact[i], p - 2, p);
    }
    for (int i = 1; i <= n; ++ i) {
        for (int j = 1; j <= n; ++ j) {
            gcd[i][j] = solveGCD(i, j);
        }
    }
    power[0] = 1;
    for (int i = 1; i <= n * (n - 1) / 2; ++ i) {
        power[i] = (long long)power[i - 1] * m % p;
    }
    int result = 0;
    for (int k = 0; k < (int)summations.size(); ++ k) {
        result += solve(summations[k]);
        result %= p;
    }
    result = (long long)result * invFact[n] % p;
    printf("%d\n", result);
    return 0;
}
