// SGU 196 -- Matrix Multiplication
#include <cstdio>
#include <cstring>
#include <iostream>
using namespace std;

const int N = 10001;

int n, m, degree[N];

int main() {
    scanf("%d%d", &n, &m);
    memset(degree, 0, sizeof(degree));
    for (int i = 0; i < m; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        degree[a] ++;
        degree[b] ++;
    }
    long long result = 0;
    for (int i = 1; i <= n; ++ i) {
        result += degree[i] * degree[i];
    }
    cout << result << endl;
    return 0;
}
