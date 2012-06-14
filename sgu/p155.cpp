// SGU 155 -- Cartesian Tree
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

const int N = 55555;

int n, key[N], weight[N], order[N], children[N][2], parent[N], stack_size, stack[N];

bool compare(int i, int j) {
    return key[i] < key[j];
}

int main() {
    scanf("%d", &n);
    for (int i = 1; i <= n; ++ i) {
        scanf("%d%d", key + i, weight + i);
    }
    for (int i = 0; i < n; ++ i) {
        order[i] = i + 1;
    }
    sort(order, order + n, compare);
    memset(children, 0, sizeof(children));
    memset(parent, 0, sizeof(parent));
    stack_size = 0;
    for (int iter = 0; iter < n; ++ iter) {
        int i = order[iter];
        int p = 0;
        while (stack_size > 0 && weight[stack[stack_size - 1]] > weight[i]) {
            int j = stack[-- stack_size];
            children[j][1] = p;
            p = j;
        }
        children[i][0] = p;
        stack[stack_size ++] = i;
    }
    int p = 0;
    while (stack_size > 0) {
        int j = stack[-- stack_size];
        children[j][1] = p;
        p = j;
    }
    for (int i = 1; i <= n; ++ i) {
        for (int j = 0; j < 2; ++ j) {
            parent[children[i][j]] = i;
        }
    }
    puts("YES");
    for (int i = 1; i <= n; ++ i) {
        printf("%d %d %d\n", parent[i], children[i][0], children[i][1]);
    }
    return 0;
}
