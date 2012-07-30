// POI X Stage III -- Monkeys(mal)
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>
using namespace std;

const int N = 200000;
const int M = 400000;

int n, m, hand[N][2], events[M], parent[N], result[N];
bool released[N][2];
vector <int> elements[N];

void mark(int v, int t) {
    for (int i = 0; i < (int)elements[v].size(); ++ i) {
        result[elements[v][i]] = t;
    }
}

void merge(int i, int j, int t) {
    i = parent[i];
    j = parent[j];
    if (i != j) {
        if (i == parent[0]) {
            mark(j, t);
        }
        if (j == parent[0]) {
            mark(i, t);
        }
        if (elements[i].size() > elements[j].size()) {
            swap(i, j);
        }
        for (int k = 0; k < (int)elements[i].size(); ++ k) {
            elements[j].push_back(elements[i][k]);
            parent[elements[i][k]] = j;
        }
    }
}

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < 2; ++ j) {
            scanf("%d", &hand[i][j]);
            if (hand[i][j] != -1) {
                hand[i][j] --;
            }
        }
    }
    memset(released, 0, sizeof(released));
    for (int i = 0; i < m; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        released[-- a][-- b] = true;
        events[i] = (a << 1) | b;
    }
    for (int i = 0; i < n; ++ i) {
        parent[i] = i;
        elements[i].push_back(i);
    }
    memset(result, -1, sizeof(result));
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < 2; ++ j) {
            if (!released[i][j] && hand[i][j] != -1) {
                merge(i, hand[i][j], -1);
            }
        }
    }
    for (int i = m - 1; i >= 0; -- i) {
        int e = events[i];
        merge(e >> 1, hand[e >> 1][e & 1], i);
    }
    for (int i = 0; i < n; ++ i) {
        printf("%d\n", result[i]);
    }
    return 0;
}
