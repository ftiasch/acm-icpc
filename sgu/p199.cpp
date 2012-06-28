// SGU 199 -- Beautiful People
#include <cstdio>
#include <cstring>
#include <climits>
#include <vector>
#include <algorithm>
using namespace std;

const int N = 111111;

int n, a[N], b[N], o[N], g[N], p[N];

bool compare(int i, int j) {
    if (a[i] == a[j]) {
        return b[i] > b[j];
    }
    return a[i] < a[j];
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d%d", a + i, b + i);
        o[i] = i;
    }
    sort(o, o + n, compare);
    b[n] = INT_MIN;
    b[n + 1] = INT_MAX;
    g[0] = n;
    for (int i = 1; i <= n; ++ i) {
        g[i] = n + 1;
    }
    int best = 0;
    int choice = -1;
    for (int iter = 0; iter < n; ++ iter) {
        int i = o[iter];
        int lower = 0;
        int upper = n;
        while (lower < upper) {
            int mider = (lower + upper + 1) >> 1;
            if (b[g[mider]] < b[i]) {
                lower = mider;
            } else {
                upper = mider - 1;
            }
        }
        p[i] = g[lower];
        if (b[i] < b[g[lower + 1]]) {
            g[lower + 1] = i;
        }
        if (lower + 1 > best) {
            best = lower + 1;
            choice = i;
        }
    }
    printf("%d\n", best);
    vector <int> result;
    for (int i = choice; i < n; i = p[i]) {
        result.push_back(i);
    }
    sort(result.begin(), result.end());
    for (int i = 0; i < (int)result.size(); ++ i) {
        printf("%d%c", result[i] + 1, i == (int)result.size() - 1? '\n': ' ');
    }
    return 0;
}
