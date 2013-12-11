// VK Cup 2012 Round 2
// Problem E -- e-Government
#include <cstdio>
#include <cstring>
#include <iostream>
#include <queue>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

const int N = 100000;
const int L = 1000000;
const int C = 26;

char buffer[L + 2];

int n, q;

int m, end[L + 1], go[L + 1][C], fail[L + 1];

void build() {
    std::queue <int> queue;
    for (int c = 0; c < C; ++ c) {
        int &v = go[0][c];
        if (v) {
            fail[v] = 0;
            queue.push(v);
        } 
    }
    while (!queue.empty()) {
        int u = queue.front();
        queue.pop();
        for (int c = 0; c < C; ++ c) {
            int &v = go[u][c];
            if (v) {
                queue.push(v);
            }
            (v ? fail[v] : v) = go[fail[u]][c];
        }
    }
}

std::vector <int> children[L + 1];

int position[L + 1], size[L + 1];

void dfs(int u) {
    static int node_count = 0;
    position[u] = node_count ++;
    size[u] = 1;
    foreach (iter, children[u]) {
        int &v = *iter;
        dfs(v);
        size[u] += size[v];
    }
}

int bit[L + 1];

void add(int k, int v) {
    for (; k < m; k += ~k & k + 1) {
        bit[k] += v;
    }
}

int query(int k) {
    int result = 0;
    for (; k >= 0; k -= ~k & k + 1) {
        result += bit[k];
    }
    return result;
}

int status[N];

int main() {
    scanf("%d%d", &q, &n);
    m = 1;
    memset(go, 0, sizeof(go));
    for (int i = 0; i < n; ++ i) {
        scanf("%s", buffer);
        int &p = end[i];
        p = 0;
        for (int j = 0; buffer[j]; ++ j) {
            int c = buffer[j] - 'a';
            if (!go[p][c]) {
                go[p][c] = m ++;
            }
            p = go[p][c];
        }
    }
    build();
    for (int i = 1; i < m; ++ i) {
        children[fail[i]].push_back(i);
    }
    dfs(0);
    memset(bit, 0, sizeof(bit));
    for (int i = 0; i < n; ++ i) {
        status[i] = 1;
        int p = end[i];
        add(position[p], 1);
        add(position[p] + size[p], -1);
    }
    while (q --) {
        scanf("%s", buffer);
        if (*buffer == '?') {
            long long result = 0;
            int p = 0;
            for (char* c = buffer + 1; *c; ++ c) {
                p = go[p][*c - 'a'];
                result += query(position[p]);
            }
            std::cout << result << std::endl;
        } else {
            static int id;
            sscanf(buffer + 1, "%d", &id);
            id --;
            int delta = *buffer == '+' ? 1 : -1;
            if (status[id] != delta) {
                status[id] *= -1;
                int p = end[id];
                add(position[p], delta);
                add(position[p] + size[p], -delta);
            }
        }
    }
    return 0;
}
