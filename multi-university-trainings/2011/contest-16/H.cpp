#include <cstdio>
#include <cstring>
#include <set>
#include <queue>
using namespace std;

const int N = 100000;

int n, order[N], children[N][2];

int main() {
    while (scanf("%d", &n) == 1) {
        for (int i = 0; i < n; ++ i) {
            scanf("%d", order + i);
            order[i] --;
        }
        memset(children, -1, sizeof(children));
        set <int> keys;
        keys.insert(order[0]);
        for (int i = 1; i < n; ++ i) {
            set <int> :: iterator succ = keys.lower_bound(order[i]);
            set <int> :: iterator pred = succ;
            pred --;
            if (succ == keys.end() || children[*succ][0] != -1) {
                children[*pred][1] = order[i];
            } else {
                children[*succ][0] = order[i];
            }
            keys.insert(order[i]);
        }
        priority_queue <int> heap;
        heap.push(-order[0]);
        for (int i = 0; i < n; ++ i) {
            int u = -heap.top();
            heap.pop();
            printf("%d%c", u + 1, i == n - 1? '\n': ' ');
            for (int j = 0; j < 2; ++ j) {
                if (children[u][j] != -1) {
                    heap.push(-children[u][j]);
                }
            }
        }
    }
    return 0;
}
