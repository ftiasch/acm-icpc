// SGU 384 -- Country
#include <cstdio>
#include <cstring>
#include <climits>
#include <algorithm>

const int N = 100001;
const int M = 3 * N;
const int INF = 1000000000;

int n, m, a[M], b[M], degree[N], root, parent[N], twin[N], twin_node[N];
bool deleted[M];

int query(int a, int b) {
    if (a == b) {
        return 0;
    }
    if (b == root) {
        return query(b, a);
    }
    if (a == root) {
        int ret = INF;
        if (!deleted[parent[b]]) {
            ret = std::min(ret, 1);
        }
        if (!deleted[twin[b]] && !deleted[parent[twin_node[b]]]) {
            ret = std::min(ret, 2);
        }
        return ret;
    }
    int ret = query(root, a) + query(root, b);
    if (!deleted[twin[a]] && twin_node[a] == b) {
        ret = std::min(ret, 1);
    }
    return ret;
}

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 1; i <= m; ++ i) {
        scanf("%d%d", a + i, b + i);
        degree[a[i]] ++;
        degree[b[i]] ++;
    }
    root = std::max_element(degree + 1, degree + 1 + n) - degree;
    for (int i = 1; i <= m; ++ i) {
        if (b[i] == root) {
            std::swap(a[i], b[i]);
        }
        if (a[i] == root) {
            parent[b[i]] = i;
        } else {
            twin[a[i]] = twin[b[i]] = i;
            twin_node[a[i]] = b[i];
            twin_node[b[i]] = a[i];
        }
    }
    char buffer[7];
    while (scanf("%s", buffer) == 1) {
        if (*buffer == 'L') {
            int a, b;
            scanf("%d%d", &a, &b);
            int ret = query(a, b);
            printf("%d\n", ret < INF ? ret : -1);
        } else {
            int id;
            scanf("%d", &id);
            deleted[id] = true;
        }
    }
    return 0;
}
