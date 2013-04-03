// HDOJ 3530 -- Subsequence
#include <cstdio>
#include <cstring>
#include <deque>

const int N = 100000;

int n, low, high, a[N];

int main() {
    while (scanf("%d%d%d", &n, &low, &high) == 3) {
        int answer = 0;
        std::deque <int> min_ids, max_ids;
        for (int i = 0, j = 0; j < n; ++ j) {
            scanf("%d", a + j);
            while (!min_ids.empty() && a[min_ids.back()] >= a[j]) {
                min_ids.pop_back();
            }
            min_ids.push_back(j);
            while (!max_ids.empty() && a[max_ids.back()] <= a[j]) {
                max_ids.pop_back();
            }
            max_ids.push_back(j);
            while (i <= j && a[max_ids.front()] - a[min_ids.front()] > high) {
                if (!min_ids.empty() && min_ids.front() == i) {
                    min_ids.pop_front();
                }
                if (!max_ids.empty() && max_ids.front() == i) {
                    max_ids.pop_front();
                }
                i ++;
            }
            if (i <= j && a[max_ids.front()] - a[min_ids.front()] >= low) {
                answer = std::max(answer, j - i + 1);
            }
        }
        printf("%d\n", answer);
    }
    return 0;
}
