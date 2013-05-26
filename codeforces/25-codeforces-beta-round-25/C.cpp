// Codeforces Beta Round #25
// Problem C -- Roads in Berland
#include <cstdio>
#include <cstring>
#include <iostream>
#include <algorithm>

const int N = 300;

int n, dist[N][N];

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            scanf("%d", &dist[i][j]);
        }
    }
    int m;
    scanf("%d", &m);
    while (m --) {
        int a, b, c;
        scanf("%d%d%d", &a, &b, &c);
        a --;
        b --;
        long long answer = 0;
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < i; ++ j) {
                dist[i][j] = dist[j][i] = std::min(dist[i][j], std::min(dist[i][a] + dist[b][j], dist[i][b] + dist[a][j]) + c);
                answer += dist[i][j];
            }
        }
        std::cout << answer << std::endl;
    }
    return 0;
}
