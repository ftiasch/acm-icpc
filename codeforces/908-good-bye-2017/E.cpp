#include <cstdio>
#include <bitset>

const int N = 50;
const int M = 1001;
const int MOD = (int)1e9 + 7;

using Bitset = std::bitset<M>;

int n, m, s[M][M], bell[M];
char buf[M];
bool vis[M];
Bitset st[N], st2[N];

void update(int& x, int a)
{
    x += a;
    if (x >= MOD) {
        x -= MOD;
    }
}

int main()
{
    s[0][0] = 1;
    for (int i = 1; i < M; ++ i) {
        bell[i] = 0;
        for (int j = 1; j <= i; ++ j) {
            s[i][j] = (1LL * s[i - 1][j] * j + s[i - 1][j - 1]) % MOD;
            update(bell[i], s[i][j]);
        }
    }
    scanf("%d%d", &m, &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%s",  buf);
        for (int j = 0; j < m; ++ j) {
            if (buf[j] == '1') {
                st[i].set(j);
            }
        }
        st2[i] = st[i];
        st2[i].flip();
    }
    int result = 1;
    for (int i = 0; i < m; ++ i) {
        if (!vis[i]) {
            Bitset b;
            b.flip();
            for (int j = 0; j < n; ++ j) {
                if (st[j].test(i)) {
                    b &= st[j];
                } else {
                    b &= st2[j];
                }
            }
            int sz = 0;
            for (int j = 0; j < m; ++ j) {
                if (b.test(j)) {
                    vis[j] = true;
                    sz ++;
                }
            }
            result = 1LL * result * bell[sz] % MOD;
        }
    }
    printf("%d\n", result);
}
