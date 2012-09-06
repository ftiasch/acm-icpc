#include <cstdio>
#include <cstring>
using namespace std;

const int N = 100000;
const int MOD = 100000007;

int n, cnt[N];

void insert(int k, int v) {
    for (int i = k; i < n; i += ~i & i + 1) {
        cnt[i] += v;
        if (cnt[i] >= MOD) {
            cnt[i] -= MOD;
        }
    }
}

int query(int k) {
    int result = 0;
    for (int i = k; i >= 0; i -= ~i & i + 1) {
        result += cnt[i];
        if (result >= MOD) {
            result -= MOD;
        }
    }
    return result;
}

int a[N], down[N];

int main() {
    int testCount;
    scanf("%d", &testCount);
    for (int t = 1; t <= testCount; ++ t) {
        scanf("%d", &n);
        for (int i = n - 1; i >= 0; -- i) {
            int x;
            scanf("%d", &x);
            a[i] = n - x;
        }
        memset(cnt, 0, sizeof(cnt));
        for (int i = 0; i < n; ++ i) {
            down[i] = query(a[i]);
            insert(a[i], 1);
        }
        memset(cnt, 0, sizeof(cnt));
        int result = 0;
        for (int i = 0; i < n; ++ i) {
            result -= query(a[i]);
            if (result < 0) {
                result += MOD;
            }
            insert(a[i], down[i]);
            result += (long long)down[i] * (down[i] - 1) / 2 % MOD;
            if (result >= MOD) {
                result -= MOD;
            }
        }
        printf("Case #%d: %d\n", t, result);
    }
    return 0;
}
