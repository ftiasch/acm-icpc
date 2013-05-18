// Codeforces Beta Round #86 
// Problem B -- Petr#
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>

const int N = 2000 * 3 + 1;
const int MAGIC = (int)1e5 + 3;

char str[N];
unsigned long long hash[N], power[N];

int main() {
    power[0] = 1;
    for (int i = 1; i < N; ++ i) {
        power[i] = power[i - 1] * MAGIC;
    }
    scanf("%s", str);
    int n = strlen(str);
    scanf("%s", str + n);
    int m = strlen(str + n);
    scanf("%s", str + n + m);
    int l = strlen(str + n + m);
    hash[n + m + l] = 0;
    for (int i = n + m + l - 1; i >= 0; -- i) {
        hash[i] = hash[i + 1] * MAGIC + str[i];
    }
    unsigned long long prefix_hash = hash[n] - hash[n + m] * power[m];
    unsigned long long suffix_hash = hash[n + m] - hash[n + m + l] * power[l];
    std::vector <unsigned long long> values;
    for (int i = 0; i < n; ++ i) {
        for (int j = i + std::max(m, l); j <= n; ++ j) {
            if (hash[i] - hash[i + m] * power[m] == prefix_hash && hash[j - l] - hash[j] * power[l] == suffix_hash) {
                values.push_back(hash[i] - hash[j] * power[j - i]);
            }
        }
    }
    std::sort(values.begin(), values.end());
    printf("%d\n", (int)(std::unique(values.begin(), values.end()) - values.begin()));
    return 0;
}
