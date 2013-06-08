// Codeforces Round #187
// Problem A -- Sereja and Contest
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>

#define SIZE(v) (int)(v).size()

const int N = 200000;

int n, k, a[N + 1];

int main() {
    scanf("%d%d", &n, &k);
    for (int i = 1; i <= n; ++ i) {
        scanf("%d", a + i);
    }
    std::vector <int> choices;
    int now = 1;
    int now_n = n;
    long long sum = 0;
    for (int i = 1; i <= n; ++ i) {
        long long now_d = sum - (long long)(now_n - now) * (now - 1) * a[i];
        if (now_d < k) {
            choices.push_back(i);
            now_n --;
        } else {
            sum += (long long)(now - 1) * a[i];
            now ++;
        }
    }
    for (int i = 0; i < SIZE(choices); ++ i) {
        printf("%d\n", choices[i]);
    }
    return 0;
}
