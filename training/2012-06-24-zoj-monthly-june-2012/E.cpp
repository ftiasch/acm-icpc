// Problem E -- Choir II
#include <cstdio>
#include <cstring>
#include <string>
#include <map>
using namespace std;

const int N = 222;
const int M = N * N * 2;
const int L = 22222;
const int INF = 1000000000;

int n, m, trie_size, id[L], fail[L], queue[L], sum[L], first[L], end[N], weight[N][N], edge_count, first_edge[N], to[M], capacity[M], cost[M], next_edge[M], pre[N], dist[N];
char buffer[L];
bool visit[N];
map <char, int> children[L];
string names[N], sentences[N];

int check_min(int x, int y) {
    if (x == -1) {
        return y;
    }
    return min(x, y);
}

void my_add_edge(int u, int v, int c, int w) {
    to[edge_count] = v;
    capacity[edge_count] = c;
    cost[edge_count] = w;
    next_edge[edge_count] = first_edge[u];
    first_edge[u] = edge_count ++;
}

void add_edge(int a, int b, int c, int d) {
    my_add_edge(a, b, c, d);
    my_add_edge(b, a, 0, -d);
}

int solve(int source, int target) {
    int result = 0;
    while (true) {
        for (int i = 0; i <= target; ++ i) {
            dist[i] = -INF;
        }
        dist[source] = 0;
        memset(visit, 0, sizeof(visit));
        int head = 0;
        int tail = 0;
        queue[tail ++] = source;
        while (head != tail) {
            int u = queue[head];
            visit[u] = false;
            head = (head + 1) % N;
            for (int iter = first_edge[u]; iter != -1; iter = next_edge[iter]) {
                if (capacity[iter] > 0 && dist[u] + cost[iter] > dist[to[iter]]) {
                    dist[to[iter]] = dist[u] + cost[iter];
                    pre[to[iter]] = iter;
                    if (!visit[to[iter]]) {
                        visit[to[iter]] = true;
                        queue[tail] = to[iter];
                        tail = (tail + 1) % N;
                    }
                }
            }
        }
        if (dist[target] == -INF) {
            break;
        }
        for (int i = target; i != source; i = to[pre[i] ^ 1]) {
            result += cost[pre[i]];
            capacity[pre[i]] --;
            capacity[pre[i] ^ 1] ++;
        }
    }
    return result;
}

int main() {
    while (scanf("%d%d", &n, &m) == 2) {
        fgets(buffer, L, stdin);
        trie_size = 0;
        children[0].clear();
        for (int i = 0; i < n + m; ++ i) {
            fgets(buffer, L, stdin);
            int k = 0;
            while (buffer[k] != ':') {
                k ++;
            }
            names[i] = string(buffer, k);
            int p = 0;
            for (int j = 0; j < (int)names[i].size(); ++ j) {
                if (children[p].find(names[i][j]) == children[p].end()) {
                    children[p][names[i][j]] = ++ trie_size;
                    children[trie_size].clear();
                }
                p = children[p][names[i][j]];
            }
            end[i] = p;
            sentences[i] = string(buffer + k + 2);
        }
        int head = 0;
        int tail = 0;
        memset(fail, 0, sizeof(fail));
        for (map <char, int> :: iterator iter = children[0].begin(); iter != children[0].end(); ++ iter) {
            queue[tail ++] = iter->second;
        }
        while (head != tail) {
            int u = queue[head ++];
            for (map <char, int> :: iterator iter = children[u].begin(); iter != children[u].end(); ++ iter) {
                int v = iter->second;
                queue[tail ++] = v;
                int p = u;
                while (p != 0) {
                    p = fail[p];
                    if (children[p].find(iter->first) != children[p].end()) {
                        fail[v] = children[p][iter->first];
                        break;
                    }
                }
            }
        }
        memset(weight, 0, sizeof(weight));
        for (int i = 0; i < n + m; ++ i) {
            memset(sum, 0, sizeof(sum));
            memset(first, -1, sizeof(first));
            int p = 0;
            for (int j = 0; j < (int)sentences[i].size(); ++ j) {
                if (children[p].find(sentences[i][j]) != children[p].end()) {
                    p = children[p][sentences[i][j]];
                } else {
                    while (p != 0) {
                        p = fail[p];
                        if (children[p].find(sentences[i][j]) != children[p].end()) {
                            p = children[p][sentences[i][j]];
                            break;
                        }
                    }
                }
                sum[p] ++;
                first[p] = check_min(first[p], j);
            }
            for (int j = tail - 1; j >= 0; -- j) {
                int u = queue[j];
                sum[fail[u]] += sum[u];
                first[fail[u]] = check_min(first[fail[u]], first[u]);
            }
            for (int j = 0; j < n + m; ++ j) {
                weight[i][j] += sum[end[j]] * (first[end[j]] - (int)names[j].size() + 2);
            }
//for (int j = 0; j < n + m; ++ j) {
//    printf("%4d", sum[end[j]]);
//}
//puts("");
        }
//for (int i = 0; i < n + m; ++ i) {
//    for (int j = 0; j < n + m; ++ j) {
//        printf("%4d", weight[i][j]);
//    }
//    puts("");
//}
        edge_count = 0;
        memset(first_edge, -1, sizeof(first_edge));
        int source = n + m;
        int target = n + m + 1;
        for (int i = 0; i < n; ++ i) {
            add_edge(source, i, 1, 0);
        }
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                add_edge(i, n + j, 1, weight[i][n + j] + weight[n + j][i]);
            }
        }
        for (int j = 0; j < m; ++ j) {
            add_edge(n + j, target, 1, 0);
        }
        printf("%d\n", solve(source, target));
    }
    return 0;
}
