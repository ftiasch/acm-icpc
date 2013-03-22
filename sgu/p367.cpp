// SGU 367 -- Contest
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

const int N = 1000;

int n, t, m, a[N], degree[N], position[N];
std::vector <int> tos[N];

bool compare(int i, int j) {
    if (a[i] == a[j]) {
        return position[i] < position[j];
    }
    return a[i] < a[j];
}

int main() {
    scanf("%d%d", &n, &t);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    scanf("%d", &m);
    memset(degree, 0, sizeof(degree));
    while (m --) {
        int a, b;
        scanf("%d%d", &a, &b);
        a --;
        b --;
        tos[a].push_back(b);
        degree[b] ++;
    }
    std::vector <int> queue;
    for (int i = 0; i < n; ++ i) {
        if (!degree[i]) {
            queue.push_back(i);
        }
    }
    memset(position, -1, sizeof(position));
    for (int head = 0; head < (int)queue.size(); ++ head) {
        int u = queue[head];
        position[u] = head;
        foreach (iter, tos[u]) {
            if (!-- degree[*iter]) {
                queue.push_back(*iter);
            }
        }
    }
    std::vector <int> order;
    for (int i = 0; i < n; ++ i) {
        if (position[i] != -1) {
            order.push_back(i);
        }
    }
    std::sort(order.begin(), order.end(), compare);
    int now = 0;
    int penalty = 0;
    std::vector <int> choices;
    foreach (iter, order) {
        if (now + a[*iter] > t) {
            break;
        }
        now += a[*iter];
        penalty += now;
        choices.push_back(*iter);
    }
    printf("%d %d\n", (int)choices.size(), penalty);
    foreach (iter, choices) {
        printf("%d ", 1 + *iter);
    }
    puts("");
    return 0;
}
