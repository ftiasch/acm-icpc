// SGU 101 -- Domino
#include <cassert>
#include <cstdio>
#include <cstring>
#include <list>
#include <vector>
using namespace std;

const int N = 7;
const int M = 100 << 1;

int n, edge_count, first_edge[N], to[M], next_edge[M], parent[N], 
    degree[N], ways[M];
bool exists[N];
list <int> order;

int find(int i) {
    if (parent[i] != i) {
        parent[i] = find(parent[i]);
    }
    return parent[i];
}

void merge(int i, int j) {
    if (find(i) != find(j)) {
        parent[find(i)] = find(j);
    }
}

void add_edge(int u, int v) {
    to[edge_count] = v;
    next_edge[edge_count] = first_edge[u];
    first_edge[u] = edge_count ++;
}

void dfs(int u) {
//printf(">> %d\n", u);
    for (int iter = first_edge[u]; iter != -1; iter = next_edge[iter]) {
        if (ways[iter >> 1] == -1) {
            ways[iter >> 1] = iter & 1;
            int v = to[iter];
            dfs(v);
            order.push_front(iter >> 1);
        }
    }
}

void my_assert(bool b) {
    if (!b) {
        vector <int> v;
        printf("%d\n", (int)v[0]);
    }
}

int main() {
    scanf("%d", &n);
//my_assert(n >= 1);
    edge_count = 0;
    memset(first_edge, -1, sizeof(first_edge));
    for (int i = 0; i < N; ++ i) {
        parent[i] = i;
    }
    memset(exists, 0, sizeof(exists));
    memset(degree, 0, sizeof(degree));
    for (int i = 0; i < n; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        add_edge(a, b);
        add_edge(b, a);
        merge(a, b);
        exists[a] = exists[b] = true;
        degree[a] ++;
        degree[b] ++;
    }
    if (n > 0) {
        int pivot = 0;
        while (pivot < N && !exists[pivot]) {
            pivot ++;
        }
my_assert(pivot < N);
        bool no_solution = false;
        for (int i = 0; i < N; ++ i) {
            if (exists[i] && find(i) != find(pivot)) {
                no_solution = true;
            }
        }
        int odd_degree_count = 0;
        for (int i = 0; i < N; ++ i) {
            odd_degree_count += degree[i] & 1;
        }
        if (odd_degree_count != 0 && odd_degree_count != 2) {
            no_solution = true;
        }
        if (no_solution) {
            puts("No solution");
        } else {
            if (odd_degree_count == 2) {
                while (pivot < N && !exists[pivot] || (degree[pivot] & 1) == 0) {
                    pivot ++;
                }
            }
my_assert(pivot < N);
            memset(ways, -1, sizeof(ways));
            dfs(pivot);
            for (list <int> :: iterator iter = order.begin(); \
                    iter != order.end(); ++ iter) {
                int i = *iter;
                printf("%d %c\n", i + 1, ways[i] == 0? '+': '-');
            }
        }
    }
    return 0;
}
