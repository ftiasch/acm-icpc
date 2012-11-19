// Codeforces Round #110
// Problem D -- Suspects
#include <cassert>
#include <cstdio>
#include <cstring>
#include <set>
#include <algorithm>
using namespace std;

const int N = 100000 + 1;

int n, m, a[N];

int positive[N], negative[N];

const char* format(bool isTrue, bool isFalse) {
    if (isTrue && isFalse) {
        return "Not defined";
    }
    if (isTrue) {
        return "Truth";
    }
    if (isFalse) {
        return "Lie";
    }
    assert(false);
}

int main() {
    scanf("%d%d", &n, &m);
    memset(positive, 0, sizeof(positive));
    memset(negative, 0, sizeof(negative));
    int negativeCount = 0;
    for (int i = 1; i <= n; ++ i) {
        scanf("%d", a + i);
        if (a[i] > 0) {
            positive[a[i]] ++;
        } else {
            negativeCount ++;
            negative[-a[i]] ++;
        }
    }
    set <int> suspects;
    for (int x = 1; x <= n; ++ x) {
        int counter = positive[x] + negativeCount - negative[x];
        if (counter == m) {
            suspects.insert(x);
        }
    }
    for (int i = 1; i <= n; ++ i) {
        if (a[i] > 0) {
            bool isTrue = suspects.count(a[i]);
            bool isFalse = suspects.size() - suspects.count(a[i]) >= 1;
            puts(format(isTrue, isFalse));
        } else {
            bool isTrue = suspects.size() - suspects.count(-a[i]) >= 1;
            bool isFalse = suspects.count(-a[i]);
            puts(format(isTrue, isFalse));
        }
    }
    return 0;
}
