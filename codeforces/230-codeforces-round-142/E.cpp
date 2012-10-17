// Codeforces Round #142
// Problem E -- Triangles
#include <cstdio>
#include <cstring>
#include <iostream>
using namespace std;

const int N = 1000000;

int n, m, degree[N];

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; ++ i) {
        degree[i] = 0;
    }
    for (int i = 0; i < m; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        degree[-- a] ++;
        degree[-- b] ++;
    }
    long long result = 0;
    for (int i = 0; i < n; ++ i) {
        result += (long long)degree[i] * (n - 1 - degree[i]);
    }
    cout << (long long)n * (n - 1) * (n - 2) / 6 - result / 2 << endl;
    return 0;
}
