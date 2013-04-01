// SGU 279 -- Bipermutations
#include <cstdio>
#include <cstring>
#include <deque>
#include <queue>

const int N = 1000;

int n, a[N + 1];
bool visit[N + 1];

int main() {
    scanf("%d", &n);
    for (int i = 1; i <= n; ++ i) {
        scanf("%d", a + i);
    }
    std::deque <int> ways;
    std::queue <int> queue;
    memset(visit, 0, sizeof(visit));
    for (int _ = 0; _ < n << 1; ++ _) {
        int i = 1;
        while (i <= n && (visit[i] || a[i] != 0)) {
            i ++;
        }
        if (i <= n) {
            ways.push_front(i);
            visit[i] = true;
            queue.push(i);
            for (int j = 1; j < i; ++ j) {
                a[j] --;
            }
        } else {
            if (queue.empty()) {
                puts("NO");
                return 0;
            }
            i = queue.front();
            queue.pop();
            ways.push_front(-i);
            for (int j = i + 1; j <= n; ++ j) {
                a[j] --;
            }
        }
    }
    puts("YES");
    for (int i = 0; i < n << 1; ++ i) {
        printf("%d%c", ways[i], i == (n << 1) - 1 ? '\n' : ' ');
    }
    return 0;
}
