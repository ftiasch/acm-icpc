#include <algorithm>
#include <cstdio>
#include <vector>

const int N = 5000000;

int  n, fail[N], state[N + 1];
char pattern[N + 1], text[N + 1];

int transfer(int u, char c)
{
    if (u + 1 < n && pattern[u + 1] == c) {
        return u + 1;
    }
    while (~u) {
        u = fail[u];
        if (pattern[u + 1] == c) {
            return u + 1;
        }
    }
    return -1;
}

int main()
{
    while (scanf("%s%s", pattern, text) == 2) {
        n = strlen(pattern);
        memset(fail, -1, sizeof(*fail) * n);
        for (int i = 0; i + 1 < n; ++ i) {
            int j = i;
            while (~j) {
                j = fail[j];
                if (pattern[j + 1] == pattern[i + 1]) {
                    fail[i + 1] = j + 1;
                    break;
                }
            }
        }
        int top = 0;
        state[0] = -1;
        for (int i = 0; text[i]; ++ i) {
            state[top + 1] = transfer(state[top], text[i]);
            if (state[top + 1] == n - 1) {
                top -= n - 1;
            } else {
                text[top ++] = text[i];
            }
        }
        text[top] = '\0';
        puts(text);
    }
    return 0;
}
