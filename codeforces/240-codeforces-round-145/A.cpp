// Codeforces Round #145
// Problem A -- Cinema
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

const int N = 100;

int m, k, n, limit[N][2];
bool favourite[N];

int main() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    scanf("%d%d", &m, &k);
    memset(favourite, 0, sizeof(favourite));
    for (int i = 0; i < k; ++ i) {
        int a;
        scanf("%d", &a);
        favourite[-- a] = true;
    }
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        int d;
        char name[10 + 1];
        scanf("%s%d", name, &d);
        int counter = 0;
        int empty = 0;
        int left[2] = {m - k, k};
        for (int j = 0; j < d; ++ j) {
            int b;
            scanf("%d", &b);
            if (b) {
                b --;
                left[favourite[b]] --;
                counter += favourite[b];
            } else {
                empty ++;
            }
        }
        limit[i][0] = counter + max(empty - left[0], 0);
        limit[i][1] = counter + min(left[1], empty);
    }
    for (int i = 0; i < n; ++ i) {
        bool valid = true;
        for (int j = 0; j < n; ++ j) {
            valid &= i == j || limit[i][0] >= limit[j][1];
        }
        if (valid) {
            puts("0");
        } else {
            bool found = false;
            for (int j = 0; j < n; ++ j) {
                found |= i != j && limit[i][1] < limit[j][0];
            }
            if (found) {
                puts("1");
            } else {
                puts("2");
            }
        }
    }
    return 0;
}
