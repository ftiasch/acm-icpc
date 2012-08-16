// SGU 298 -- King Berl VI
#include <cstdio>
#include <cstring>
#include <climits>
#include <vector>
#include <algorithm>
using namespace std;

const int N = 10000;
const int M = 100000;

int n, m, a[M], b[M], c[M];
vector <int> adjacent[N];

int stampCount, stamp[N], lowest[N], componentCount, componentID[N];
vector <int> stack;

int classCount, classID[N];
int edgeCount, firstEdge[N], to[M], length[M], nextEdge[M];
int longest[N];

void dfs(int u) {
    if (stamp[u] == -1) {
        int ret = stamp[u] = lowest[u] = stampCount ++;
        stack.push_back(u);
        for (vector <int> :: iterator iter = adjacent[u].begin(); 
                iter != adjacent[u].end(); ++ iter) {
            int v = *iter;
            dfs(v);
            ret = min(ret, lowest[v]);
        }
        lowest[u] = ret;
        if (stamp[u] == lowest[u]) {
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

void update(int i) {
    for (int iter = firstEdge[i]; iter != -1; iter = nextEdge[iter]) {
        longest[to[iter]] = max(longest[to[iter]], longest[i] + length[iter]);
    }
}

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < m; ++ i) {
        scanf("%d%d%d", b + i, a + i, c + i);
        a[i] --, b[i] --;
    }
    for (int i = 0; i < m; ++ i) {
        if (c[i] == 0) {
            adjacent[a[i]].push_back(b[i]);
        }
    }
    stampCount = componentCount = 0;
    memset(stamp, -1, sizeof(stamp));
    for (int i = 0; i < n; ++ i) {
        dfs(i);
    }
    classCount = componentCount;
    copy(componentID, componentID + n, classID);
    for (int i = 0; i < classCount; ++ i) {
        adjacent[i].clear();
    }
    bool acyclic = true;
    for (int i = 0; i < m; ++ i) {
        int u = classID[a[i]];
        int v = classID[b[i]];
        if (u == v) {
            acyclic &= c[i] == 0;
        } else {
            adjacent[u].push_back(v);
        }
    }
    stampCount = componentCount = 0;
    memset(stamp, -1, sizeof(stamp));
    for (int i = 0; i < classCount; ++ i) {
        dfs(i);
    }
    acyclic &= componentCount == classCount;
    bool valid = acyclic;
    if (acyclic) {
        edgeCount = 0;
        memset(firstEdge, -1, sizeof(firstEdge));
        for (int i = 0; i < m; ++ i) {
            int u = componentID[classID[a[i]]];
            int v = componentID[classID[b[i]]];
            if (u != v) {
                to[edgeCount] = v;
                length[edgeCount] = c[i];
                nextEdge[edgeCount] = firstEdge[u];
                firstEdge[u] = edgeCount ++;
            }
        }
        memset(longest, 0, sizeof(longest));
        for (int i = componentCount - 1; i >= 0; -- i) {
            update(i);
        }
        valid &= *max_element(longest, longest + classCount) <= 20000;
        for (int i = 0; i < componentCount; ++ i) {
            longest[i] = INT_MIN;
        }
        longest[componentID[classID[0]]] = 0;
        for (int i = componentCount - 1; i >= 0; -- i) {
            update(i);
        }
        int extra = *max_element(longest, longest + componentCount) - 20000;
        for (int i = componentCount - 1; i >= 0; -- i) {
            longest[i] = max(longest[i], extra);
            update(i);
        }
    }
    if (valid) {
        int lower = *min_element(longest, longest + componentCount);
        for (int i = 0; i < n; ++ i) {
            printf("%d%c", longest[componentID[classID[i]]] - lower - 10000, i == n - 1? '\n': ' ');
        }
    } else {
        puts("-1");
    }
    return 0;
}
