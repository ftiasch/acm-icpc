#include <cstdio>
#include <cstring>
#include <climits>
#include <vector>
#include <functional>
#include <iostream>
#include <algorithm>

#define ALL(v) (v).begin(), (v).end()
#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

const int N = 8;

int n;
long long a[N];
std::vector <long long> factors;

void factorize(long long n) {
    for (long long i = 2; i * i <= n; ++ i) {
        while (n % i == 0) {
            factors.push_back(i);
            n /= i;
        }
    }
    if (n > 1) {
        factors.push_back(n);
    }
}

int answer, parent[N];

void update() {
    long long b[N];
    memcpy(b, a, sizeof(a));
    for (int i = n - 1; i >= 0; -- i) {
        if (parent[i] != i) {
            if (b[parent[i]] % a[i] != 0) {
                return;
            }
            b[parent[i]] /= a[i];
        }
    }
    int rootCount = 0;
    for (int i = 0; i < n; ++ i) {
        rootCount += parent[i] == i;
    }
    int count = rootCount > 1;
    for (int i = 0; i < n; ++ i) {
        int childrenCount = 0;
        long long backup = b[i];
        foreach (iter, factors) {
            long long p = *iter;
            while (b[i] % p == 0) {
                childrenCount ++;
                b[i] /= p;
            }
        }
        if (a[i] == backup && childrenCount <= 1) {
            continue;
        }
        count += childrenCount;
    }
    answer = std::min(answer, count + n);
}

void search(int i) {
    if (i < n) {
        for (parent[i] = 0; parent[i] <= i; ++ parent[i]) {
            search(i + 1);
        }
    } else {
        update();
    }
}

int main() {
    std::cin >> n;
    for (int i = 0; i < n; ++ i) {
        std::cin >> a[i];
    }
    std::sort(a, a + n, std::greater <long long>());
    n = std::unique(a, a + n) - a;
    for (int i = 0; i < n; ++ i) {
        factorize(a[i]);
    }
    std::sort(ALL(factors));
    factors.erase(std::unique(ALL(factors)), factors.end());
    answer = INT_MAX;
    search(0);
    printf("%d\n", answer);
    return 0;
}
