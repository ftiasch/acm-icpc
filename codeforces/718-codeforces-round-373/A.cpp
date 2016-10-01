#include <algorithm>
#include <cstdio>
#include <cstring>

int main()
{
#ifdef LOCAL_JUDGE
    freopen("A.in", "r", stdin);
#endif
    int n, t;
    static char s[200002];
    while (scanf("%d%d%s", &n, &t, s + 1) == 3) {
        int dot = std::find(s + 1, s + 1 + n, '.') - s;
        int i = std::find_if(s + dot, s + 1 + n, [](char c) { return c >= '5'; }) - s;
        char* ss = s + 1;
        while (dot < i && i <= n && s[i] >= '5' && t) {
            s[i --] = '\0';
            if (dot < i) {
                s[i] ++;
            } else {
                int delta = 1;
                for (int j = dot - 1; j >= 1; -- j) {
                    delta += s[j] - '0';
                    s[j] = delta % 10 + '0';
                    delta /= 10;
                }
                if (delta) {
                    ss --;
                    *ss = delta + '0';
                }
            }
            t --;
        }
        n = strlen(ss);
        if (ss[n - 1] == '.') {
            ss[n - 1] = '\0';
        }
        puts(ss);
    }
}
