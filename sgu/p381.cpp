// SGU 381 -- Bidirected Graph
#include <cstdio>
#include <cstring>
#include <vector>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

const int N = 100000;
const int M = 500000 << 1;

int edge_count, n, m, first_edge[N], to[M], type[M], next_edge[M], color[N];

void add_edge(int u, int v, int w) {
    to[edge_count] = v;
    type[edge_count] = w;
    next_edge[edge_count] = first_edge[u];
    first_edge[u] = edge_count ++;
}

int main() {
    scanf("%d%d", &n, &m);
    edge_count = 0;
    memset(first_edge, -1, sizeof(first_edge));
    for (int i = 0; i < m; ++ i) {
        int a, b, x, y;
        scanf("%d%d%d%d", &a, &b, &x, &y);
        a --;
        b --;
        bool s = x * y > 0;
        add_edge(a, b, s);
        add_edge(b, a, s);
    }
    memset(color, -1, sizeof(color));
    int answer = 0;
    for (int i = 0; i < n; ++ i) {
        if (color[i] == -1) {
            std::vector <int> queue;
            color[i] = 0;
            queue.push_back(i);
            for (int head = 0; head < (int)queue.size(); ++ head) {
                int u = queue[head];
                for (int iter = first_edge[u]; iter != -1; iter = next_edge[iter]) {
                    int v = to[iter];
                    if (color[v] == -1) {
                        color[v] = color[u] ^ type[iter];
                        queue.push_back(v);
                    }
                    if ((color[u] ^ color[v]) != type[iter]) {
                        puts("NO");
                        return 0;
                    }
                }
            }
            int count = 0;
            foreach (iter, queue) {
                count += color[*iter];
            }
            if (count > (int)queue.size() - count) {
                foreach (iter, queue) {
                    color[*iter] ^= 1;
                }
                count = (int)queue.size() - count;
            }
            answer += count;
        }
    }
    puts("YES");
    printf("%d\n", answer);
    for (int i = 0; i < n; ++ i) {
        if (color[i]) {
            printf("%d\n", i + 1);
        }
    }
    return 0;
}
