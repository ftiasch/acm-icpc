// Codeforces Beta Round #33 
// Problem B -- String Problem
#include <cstdio>
#include <cstring>
#include <climits>
#include <algorithm>

const int N = 100000 + 1;
const int INF = 1000000000;

char source[N], target[N];
int transform[26][26], cost[26][26], candidate[26][26];

int main() {
    scanf("%s%s", source, target);
    for (int i = 0; i < 26; ++ i) {
        for (int j = 0; j < 26; ++ j) {
            transform[i][j] = i == j ? 0 : INF;
        }
    }
    int m;
    scanf("%d", &m);
    while (m --) {
        char from[2], to[2];
        int cost;
        scanf("%s%s%d", from, to, &cost);
        int &t = transform[*from - 'a'][*to - 'a'];
        t = std::min(t, cost);
    }
    for (int k = 0; k < 26; ++ k) {
        for (int i = 0; i < 26; ++ i) {
            for (int j = 0; j < 26; ++ j) {
                transform[i][j] = std::min(transform[i][j], transform[i][k] + transform[k][j]);
            }
        }
    }
    for (int i = 0; i < 26; ++ i) {
        for (int j = 0; j < 26; ++ j) {
            cost[i][j] = INF;
            for (int k = 0; k < 26; ++ k) {
                if (transform[i][k] + transform[j][k] < cost[i][j]) {
                    cost[i][j] = transform[i][k] + transform[j][k];
                    candidate[i][j] = k;
                }
            }
        }
    }
    if (strlen(source) != strlen(target)) {
        puts("-1");
        return 0;
    }
    int n = strlen(source);
    int total = 0;
    for (int i = 0; i < n; ++ i) {
        total += cost[source[i] - 'a'][target[i] - 'a'];
        if (total >= INF) {
            puts("-1");
            return 0;
        }
    }
    printf("%d\n", total);
    for (int i = 0; i < n; ++ i) {
        putchar(candidate[source[i] - 'a'][target[i] - 'a'] + 'a');
    }
    puts("");
    return 0;
}
