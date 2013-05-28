// Codeforces Beta Round #31
// Problem C -- Schedule
#include <cstdio>
#include <cstring>
#include <climits>
#include <vector>
#include <algorithm>

const int N = 5000;

int n, a[N], b[N], order[N];

bool compare(int i, int j) {
    if (a[i] == a[j]) {
        return b[i] < b[j];
    }
    return a[i] < a[j];
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d%d", a + i, b + i);
        order[i] = i;
    }
    std::sort(order, order + n, compare);
    std::vector <int> answer;
    for (int i = 0; i < n; ++ i) {
        bool valid = true;
        int last = INT_MIN;
        for (int j = 0; j < n; ++ j) {
            if (order[j] == i) {
                continue;
            }
            valid &= last <= a[order[j]];
            last = b[order[j]];
        }
        if (valid) {
            answer.push_back(i);
        }
    }
    printf("%d\n", (int)answer.size());
    for (int i = 0; i < (int)answer.size(); ++ i) {
        printf("%d%c", answer[i] + 1, " \n"[i == (int)answer.size() - 1]);
    }
    return 0;
}
