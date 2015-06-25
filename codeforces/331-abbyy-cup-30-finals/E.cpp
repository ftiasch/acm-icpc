#include <algorithm>
#include <cstdio>
#include <cstring>
#include <vector>

#define ALL(v) (v).begin(), (v).end()

typedef std::vector <int> Seq;

const int N   = 50;
const int MOD = (int)1e9 + 7;

Seq *graph[N][N], *rgraph[N][N];

Seq find(Seq* graph[N][N], Seq seq, int l)
{
    for (int i = 1; i < (int)seq.size() && (int)seq.size() <= l; ++ i) {
        Seq* g = graph[seq[i - 1]][seq[i]];
        if (g == NULL) {
            return Seq();
        }
        for (int v : *g) {
            seq.push_back(v);
        }
    }
    return seq;
}

Seq reverse(Seq seq)
{
    std::reverse(ALL(seq));
    return seq;
}

Seq concat(Seq a, const Seq &b)
{
    a.insert(a.end(), b.begin(), b.end());
    return a;
}

void update(int &x, int a)
{
    x += a;
    if (x >= MOD) {
        x -= MOD;
    }
}

int ways[2 * N + 1][3][N];

int main()
{
    int n, m;
    scanf("%d%d", &n, &m);
    for (int _ = 0; _ < m; ++ _) {
        int x, y, k;
        scanf("%d%d%d", &x, &y, &k);
        x --, y --;
        Seq seq;
        while (k --) {
            int v;
            scanf("%d", &v);
            seq.push_back(-- v);
        }
        graph[x][y]  = new Seq(seq);
        rgraph[y][x] = new Seq(reverse(seq));
    }
    std::vector <Seq> seqs[3];
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            if (graph[i][j] != NULL) {
                Seq seq = *graph[i][j];
                if (!seq.empty() && seq.back() == i) {
                    const auto &path = reverse(find(rgraph, reverse(seq), 2 * n));
                    if (1 <= (int)path.size() && path.size() <= 2 * n) {
                        seqs[0].push_back(concat(path, Seq(1, j)));
                    }
                }
                for (int k = 1; k < (int)seq.size(); ++ k) {
                    if (seq[k - 1] == i && seq[k] == j) {
                        const auto &bpath = reverse(find(rgraph, reverse(Seq(seq.begin(), seq.begin() + k)), 2 * n));
                        const auto &fpath = find(graph, Seq(seq.begin() + k, seq.end()), 2 * n);
                        if (!bpath.empty() && !fpath.empty() && bpath.size() + fpath.size() <= 2 * n) {
                            seqs[1].push_back(concat(bpath, fpath));
                        }
                    }
                }
                if (!seq.empty() && seq.front() == j) {
                    const auto &path = find(graph, seq, 2 * n);
                    if (1 <= (int)path.size() && path.size() <= 2 * n) {
                        seqs[2].push_back(concat(Seq(1, i), path));
                    }
                }
            }
        }
    }
    for (int i = 0; i < 3; ++ i) {
        std::sort(ALL(seqs[i]));
        seqs[i].erase(std::unique(ALL(seqs[i])), seqs[i].end());
    }
    memset(ways, 0, sizeof(ways));
    for (int v = 0; v < n; ++ v) {
        ways[0][0][v] = ways[0][1][v] = 1;
    }
    for (int i = 0; i <= 2 * n; ++ i) {
        for (int s = 0; s < 3; ++ s) {
            for (const Seq &seq : seqs[s]) {
                int u = seq.front();
                int v = seq.back();
                int j = i + seq.size() - 1;
                if (j <= 2 * n) {
                    if (s != 1) {
                        update(ways[j][s][v], ways[i][s][u]);
                    }
                    if (s != 2) {
                        update(ways[j][s + 1][v], ways[i][0][u]);
                    }
                }
            }
        }
        if (i + 1 <= 2 * n) {
            for (int j = 0; j < n; ++ j) {
                for (int k = 0; k < n; ++ k) {
                    Seq *seq = graph[j][k];
                    if (seq != NULL && seq->empty()) {
                        update(ways[i + 1][0][k], ways[i][2][j]);
                        update(ways[i + 1][1][k], ways[i][2][j]);
                    }
                }
            }
        }
        if (i) {
            int result = 0;
            for (int v = 0; v < n; ++ v) {
                update(result, ways[i][2][v]);
            }
            printf("%d\n", result);
        }
    }
    return 0;
}
