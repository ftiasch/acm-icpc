// Codeforces Round #189
// Problem B -- Psychos in a Line
#include <cstdio>
#include <cstring>
#include <set>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

const int N = 100000;

int n, a[N];

int main() {
    scanf("%d", &n);
    std::set <int> positions;
    std::set <int> queue;
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
        positions.insert(i);
        if (i && a[i - 1] > a[i]) {
            queue.insert(i);
        }
    }
    int answer = 0;
    while (!queue.empty()) {
        answer ++;
        std::set <int> new_queue;
        foreach (iter, queue) {
            positions.erase(*iter);
        }
        foreach (iter, queue) {
            std::set <int> :: iterator this_iter = positions.lower_bound(*iter);
            if (this_iter != positions.end() && this_iter != positions.begin()) {
                std::set <int> :: iterator prev_iter = this_iter;
                prev_iter --;
                if (a[*prev_iter] > a[*this_iter]) {
                    new_queue.insert(*this_iter);
                }
            }
        }
        queue = new_queue;
    }
    printf("%d\n", answer);
    return 0;
}
