// Codeforces Round #187
// Problem C -- Sereja and Subsequences
#include <cstdio>
#include <cstring>

const int N = 100000;
const int M = 1000000 + 1;
const int MOD = (int)1e9 + 7;

int n, a[N], last[M], product[N];
int count[M];

void add(int k, int v) {
    for (; k < M; k += -k & k) {
        (count[k] += v) %= MOD;
    }
}

int ask(int k) {
    int ret = 0;
    for (; k; k -= -k & k) {
        (ret += count[k]) %= MOD;
    }
    return ret;
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    memset(last, -1, sizeof(last));
    for (int i = 0; i < n; ++ i) {
        product[i] = (long long)(ask(a[i]) + 1) * a[i] % MOD;
        int &j = last[a[i]];
        if (j != -1) {
            add(a[j], MOD - product[j]);
            product[j] = 0;
        }
        add(a[i], product[i]);
        j = i;
    }
    int answer = 0;
    for (int i = 0; i < n; ++ i) {
        (answer += product[i]) %= MOD;
    }
    printf("%d\n", answer);
    return 0;
}
