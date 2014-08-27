#include <cstdlib>
#include <cstdio>
#include <cstring>
#include <algorithm>

const int C = 26;
const int N = 100000;
const int S = N + 2 + C;

char string[N + 2];
int s, length[S], suffix[S], go[S][C];

int extend(int p, int i)
{
    while (string[i - 1 - length[p]] != string[i]) {
        p = suffix[p];
    }
    int q = suffix[p];
    while (string[i - 1 - length[q]] != string[i]) {
        q = suffix[q];
    }
    int c = string[i] - 'a';
    int pp = go[p][c];
    int qq = go[q][c];
    if (pp == -1) {
        length[pp = go[p][c] = s ++] = length[p] + 2;
        suffix[pp] = qq;
        memset(go[pp], -1, sizeof(go[pp]));
    }
    return pp;
}

int main()
{
    int tests;
    scanf("%d", &tests);
    for (int t = 1; t <= tests; ++ t) {
        printf("Case #%d: ", t);
        for (int i = 0; i < C + 2; ++ i) {
            suffix[i] = 1;
            length[i] = std::min(i - 1, 1);
            memset(go[i], -1, sizeof(go[i]));
        }
        suffix[0] = suffix[1] = 0;
        for (int i = 0; i < C; ++ i) {
            go[0][i] = 2 + i;
        }
        s = C + 2;
        string[0] = '#';
        scanf("%s", string + 1);
        int n = strlen(string + 1);
        int p = 0;
        for (int i = 1; i <= n; ++ i) {
            p = extend(p, i);
        }
        int result = s - (C + 2);
        std::sort(string + 1, string + n + 1);
        result += std::unique(string + 1, string + n + 1) - string - 1;
        printf("%d\n", result);
    }
    return 0;
}
