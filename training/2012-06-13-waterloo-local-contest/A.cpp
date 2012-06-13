#include <cstdio>
#include <cstring>
#include <queue>
using namespace std;

int main() {
    int test_count;
    scanf("%d", &test_count);
    while (test_count > 0) {
        test_count --;
        int limit, n;
        scanf("%d%d", &limit, &n);
        limit *= 100;
        queue <int> q[2];
        for (int i = 0; i < n; ++ i) {
            int length;
            char type[22];
            scanf("%d%s", &length, type);
            if (*type == 'l') {
                q[0].push(length);
            } else {
                q[1].push(length);
            }
        }
        int result = 0;
        int current = 0;
        while (!q[0].empty() || !q[1].empty()) {
            int capacity = limit;
            while (!q[current].empty() && capacity >= q[current].front()) {
                capacity -= q[current].front();
                q[current].pop();
            }
            result ++;
            current ^= 1;
        }
        printf("%d\n", result);
    }
    return 0;
}
