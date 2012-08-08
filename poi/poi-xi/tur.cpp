// POI XI Stage II -- The Tournament
#include <cstdio>
#include <cstring>
#include <climits>
#include <vector>
#include <algorithm>
using namespace std;

const int L = 2000;
const int N = 100000;
const int M = 1000000;

int n, m, a[M], b[M], groupCount, groupID[N], degree[N], labelCount, label[L], lowest[L], componentCount, componentID[L];
bool isAdjacent[N], graph[L][L];
vector <int> stack;

void dfs(int u) {
    if (label[u] == -1) {
        int ret = label[u] = lowest[u] = labelCount ++;
        stack.push_back(u);
        for (int v = 0; v < groupCount; ++ v) {
            if (graph[u][v]) {
                dfs(v);
                ret = min(ret, lowest[v]);
            }
        }
        lowest[u] = ret;
        if (label[u] == lowest[u]) {
            while (true) {
                int v = stack.back();
                stack.pop_back();
                lowest[v] = INT_MAX;
                componentID[v] = componentCount;
                if (u == v) {
                    break;
                }
            }
            componentCount ++;
        }
    }
}

int main() {
    scanf("%d", &n);
    m = 0;
    for (int i = 0; i < n; ++ i) {
        int k;
        scanf("%d", &k);
        while (k --) {
            int j;
            scanf("%d", &j);
            a[m] = i;
            b[m ++] = -- j;
        }
    }
    if (n <= L) {
        groupCount = n;
        for (int i = 0; i < n; ++ i) {
            groupID[i] = i;
        }
    } else {
        memset(degree, 0, sizeof(degree));
        for (int i = 0; i < m; ++ i) {
            degree[a[i]] ++;
            degree[b[i]] ++;
        }
        int x = min_element(degree, degree + n) - degree;
        memset(isAdjacent, 0, sizeof(isAdjacent));
        for (int i = 0; i < m; ++ i) {
            if (a[i] == x) {
                isAdjacent[b[i]] = true;
            }
            if (b[i] == x) {
                isAdjacent[a[i]] = true;
            }
        }
        groupCount = 1;
        for (int i = 0; i < n; ++ i) {
            if (isAdjacent[i]) {
                groupID[i] = groupCount ++;
            } else {
                groupID[i] = 0;
            }
        }
    }
    memset(graph, 1, sizeof(graph));
    for (int i = 0; i < m; ++ i) {
        graph[groupID[b[i]]][groupID[a[i]]] = false;
    }
    labelCount = componentCount = 0;
    memset(label, -1, sizeof(label));
    for (int i = 0; i < groupCount; ++ i) {
        dfs(i);
    }
    vector <int> result;
    for (int i = 0; i < n; ++ i) {
        if (componentID[groupID[i]] == componentCount - 1) {
            result.push_back(i + 1);
        }
    }
    printf("%d", (int)result.size());
    for (int i = 0; i < (int)result.size(); ++ i) {
        printf(" %d", result[i]);
    }
    putchar('\n');
    return 0;
}
