// Codeforces Round #150
// Problem B -- Hydra
#include <cstdio>
#include <cstring>
#include <vector>
#include <utility>
#include <iostream>
#include <algorithm>

#define SIZE(v) (int)((v).size())
#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

using std::vector;

const int N = 100000;

int n, m, h, t, a[N], b[N], mark[N];
vector <int> neighbours[N];

void add_neighbour(int u, int v) {
    if ((int)neighbours[u].size() < h + t + 1) {
        neighbours[u].push_back(v);
    }
}

bool exists(const vector <int> &v, int k) {
    return std::find(v.begin(), v.end(), k) != v.end();
}

int union_size(const vector <int> &a, const vector <int> &b) {
    int n = (int)a.size();
    int m = (int)b.size();
    int size = 0;
    for (int i = 0, j = 0; i < n || j < m; size ++) {
        if (i < n && j < m && a[i] == b[j]) {
            i ++;
            j ++;
        } else {
            int &k = j == m || i < n && a[i] < b[j] ? i : j;
            k ++;
        }
    }
    return size;
}

std::ostream &operator <<(std::ostream &out, const vector <int> &v) {
    out << "[";
    foreach (iter, v) {
        out << *iter << ", ";
    }
    return out << "]";
}

int main() {
    scanf("%d%d%d%d", &n, &m, &h, &t);
    for (int i = 0; i < m; ++ i) {
        scanf("%d%d", a + i, b + i);
        a[i] --;
        b[i] --;
        add_neighbour(a[i], b[i]);
        add_neighbour(b[i], a[i]);
    }
    for (int i = 0; i < n; ++ i) {
        std::sort(neighbours[i].begin(), neighbours[i].end());
    }
    for (int k = 0; k < m << 1; ++ k) {
        int u = a[k >> 1];
        int v = b[k >> 1];
        if (k & 1) {
            std::swap(u, v);
        }
        int x = exists(neighbours[u], v);
        int y = exists(neighbours[v], u);
        if (h <= SIZE(neighbours[u]) - x 
                && t <= SIZE(neighbours[v]) - y
                && h + t <= union_size(neighbours[u], neighbours[v]) - x - y) {
            puts("YES");
            printf("%d %d\n", 1 + u, 1 + v);
            memset(mark, 0, sizeof(mark));
            foreach (iter, neighbours[v]) {
                mark[*iter] = 2;
            }
            mark[u] = mark[v] = 1;
            int need = h;
            foreach (iter, neighbours[u]) {
                if (!need) {
                    break;
                }
                if (!mark[*iter]) {
                    mark[*iter] = 1;
                    need --;
                    printf("%d ", 1 + *iter);
                }
            }
            foreach (iter, neighbours[u]) {
                if (!need) {
                    break;
                }
                if (mark[*iter] != 1) {
                    mark[*iter] = 1;
                    need --;
                    printf("%d ", 1 + *iter);
                }
            }
            puts("");
            need = t;
            foreach (iter, neighbours[v]) {
                if (!need) {
                    break;
                }
                if (mark[*iter] != 1) {
                    mark[*iter] = 1;
                    need --;
                    printf("%d ", 1 + *iter);
                }
            }
            puts("");
            return 0;
        }
    }
    puts("NO");
    return 0;
}
