#include <cstdio>
#include <cstring>
#include <climits>
#include <vector>
#include <algorithm>
using namespace std;

const int N = 20000;

int n, edgeCount, firstEdge[N], to[N], nextEdge[N], length[N], result;

void addEdge(int u, int v) {
    to[edgeCount] = v;
    nextEdge[edgeCount] = firstEdge[u];
    firstEdge[u] = edgeCount ++;
}

int parent[N];

int find(int i) {
    if (i < 0) {
        return -1;
    }
    if (parent[i] != i) {
        parent[i] = find(parent[i]);
    }
    return parent[i];
}

void dfs(int p, int u, int limit) {
    vector <int> lengths;
    for (int iter = firstEdge[u]; iter != -1; iter = nextEdge[iter]) {
        int v = to[iter];
        if (v != p) {
            dfs(u, v, limit);
            lengths.push_back(length[v] + 1);
        }
    }
    sort(lengths.begin(), lengths.end());
    for (int i = 0; i < (int)lengths.size(); ++ i) {
        parent[i] = i;
    }
    int size = lengths.size();
    while (size > 1) {
        int l = lengths.back();
        lengths.pop_back();
        int i = lengths.size(); 
        if (parent[i] == i) {
            parent[i] = i - 1;
            size --;
            result ++;
            int j = find((int)(upper_bound(lengths.begin(), lengths.end(), limit - l) - lengths.begin()) - 1);
            if (j != -1) {
                size --;
                parent[j] = j - 1;
            }
        }
    }
    vector <int> newLengths;
    for (int i = 0; i < (int)lengths.size(); ++ i) {
        if (parent[i] == i) {
            newLengths.push_back(lengths[i]);
        }
    }
    if (!newLengths.empty() && newLengths.back() == limit) {
        result ++;
        newLengths.pop_back();
    }
    length[u] = newLengths.empty()? 0: newLengths.back();
}

int solve(int limit) {
    result = 0;
    dfs(-1, 0, limit);
    if (length[0] > 0) {
        result ++;
    }
    return result;
}

int main() {
    scanf("%d", &n);
    edgeCount = 0;
    memset(firstEdge, -1, sizeof(firstEdge));
    for (int i = 0; i < n - 1; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        a --;
        b --;
        addEdge(a, b);
        addEdge(b, a);
    }
    int minimum = solve(INT_MAX);
    int low = 1;
    int high = n;
    while (low < high) {
        int middle = (low + high) >> 1;
        if (solve(middle) == minimum) {
            high = middle;
        } else {
            low = middle + 1;
        }
    }
    printf("%d %d\n", minimum, high);
    return 0;
}
