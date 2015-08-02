#include <algorithm>
#include <vector>
#include <string>
#include <queue>

class RookGraph {
    static const int MOD = (int)1e9 + 7;

    void update(int &x, int a) {
        x += a;
        if (x >= MOD) {
            x -= MOD;
        }
    }

    int solve(int n, std::vector<std::vector<bool>> const &graph) {
        int m = graph.size();
        std::vector<std::vector<int>> belongs(m);
        std::vector<std::vector<int>> cliques;
        int l = 0;
        for (int j = 0; j < m; ++ j) {
            for (int i = 0; i < j; ++ i) {
                if (graph[i][j]) {
                    std::vector<int> clique;
                    for (int k = 0; k < m; ++ k) {
                        if (graph[i][k] && graph[j][k]) {
                            clique.push_back(k);
                        }
                    }
                    if (std::find(cliques.begin(), cliques.end(), clique) == cliques.end()) {
                        int id = l ++;
                        cliques.push_back(clique);
                        for (int k : clique) {
                            belongs[k].push_back(id);
                            if (belongs[k].size() > 2) {
                                return 0;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < m; ++ i) {
            while (belongs[i].size() < 2) {
                cliques.push_back({i});
                belongs[i].push_back(l ++);
            }
        }
        for (int i = 0; i < m; ++ i) {
            for (int j = 0; j < m; ++ j) {
                bool common = false;
                for (int x = 0; x < 2; ++ x) {
                    for (int y = 0; y < 2; ++ y) {
                        common |= belongs[i][x] == belongs[j][y];
                    }
                }
                if (graph[i][j] != common) {
                    return 0;
                }
            }
        }
        std::vector<std::vector<int>> perm(n + 1, std::vector<int>(n + 1));
        for (int i = 0; i <= n; ++ i) {
            perm[i][0] = 1;
            for (int j = 1; j <= i; ++ j) {
                perm[i][j] = ((long long)perm[i - 1][j - 1] * j % MOD + perm[i - 1][j]) % MOD;
            }
        }
        std::vector<int> color(l, -1);
        std::vector<std::vector<int>> ways(n + 1, std::vector<int>(n + 1));
        ways[n][n] = 1;
        for (int s = 0; s < l; ++ s) {
            if (color[s] == -1) {
                int count[] = {0, 0};
                color[s] = 0;
                std::queue<int> queue;
                queue.push(s);
                while (!queue.empty()) {
                    int u = queue.front();
                    count[color[u]] ++;
                    queue.pop();
                    for (int i : cliques[u]) {
                        for (int v : belongs[i]) {
                            if (u != v) {
                                if (color[v] == -1) {
                                    color[v] = color[u] ^ 1;
                                    queue.push(v);
                                }
                                if (color[u] == color[v]) {
                                    return 0;
                                }
                            }
                        }
                    }
                }
                std::vector<std::vector<int>> new_ways(n + 1, std::vector<int>(n + 1));
                for (int _ = 0; _ < 2; ++ _) {
                    for (int i = count[0]; i <= n; ++ i) {
                        for (int j = count[1]; j <= n; ++ j) {
                            update(new_ways[i - count[0]][j - count[1]], (long long)ways[i][j] * perm[i][count[0]] % MOD * perm[j][count[1]] % MOD);
                        }
                    }
                    if (count[0] == 1 && count[1] == 1) {
                        break;
                    }
                    std::swap(count[0], count[1]);
                }
                ways.swap(new_ways);
            }
        }
        int result = 0;
        for (int i = 0; i <= n; ++ i) {
            for (int j = 0; j <= n; ++ j) {
                update(result, ways[i][j]);
            }
        }
        return result;
    }

public:
    int countPlacements(int n, std::vector<std::string> const &graph_) {
        int m = graph_.size();
        std::vector<std::vector<bool>> graph(m, std::vector<bool>(m));
        for (int i = 0; i < m; ++ i) {
            for (int j = 0; j < m; ++ j) {
                graph[i][j] = graph_[i][j] == '1';
            }
        }
        return solve(n, graph);
    }
};
