#include <algorithm>
#include <bitset>
#include <cstdio>
#include <climits>
#include <functional>
#include <numeric>
#include <utility>
#include <vector>

const int N = 2000;

typedef std::bitset<N> Bitset;
typedef std::vector<std::pair<int, int>> Formula;
typedef std::vector<std::vector<int>> Graph;

std::vector<int> contract(const Graph& graph)
{
    int n = graph.size();
    int dfs_count = 0;
    int scc_count = 0;
    std::vector<int> dfn(n, -1), low(n), scc(n), stack;
    std::function<void(int)> dfs = [&](int u) {
        if (!~dfn[u]) {
            int tmp = low[u] = dfn[u] = dfs_count ++;
            stack.push_back(u);
            for (int v = 0; v < n; ++ v) {
                if (graph[u][v]) {
                    dfs(v);
                    tmp = std::min(tmp, low[v]);
                }
            }
            low[u] = tmp;
            if (dfn[u] == low[u]) {
                int v = -1;
                while (v != u) {
                    v = stack.back();
                    stack.pop_back();
                    low[v] = INT_MAX;
                    scc[v] = scc_count;
                }
                scc_count ++;
            }
        }
    };
    for (int i = 0; i < n << 1; ++ i) {
        dfs(i);
    }
    return scc;
}

std::vector<Bitset> closure(const Graph& graph, const std::vector<int>& order)
{
    int n = graph.size();
    std::vector<Bitset> bitsets(n);
    for (int u : order) {
        bitsets[u].set(u);
        for (int v = 0; v < n; ++ v) {
            if (graph[u][v] && !bitsets[u].test(v)) {
                bitsets[u] |= bitsets[v];
            }
        }
    }
    return bitsets;
}

std::vector<bool> solve(int n, const Formula& f1, const Formula& f2)
{
    Graph graph(n << 1, std::vector<int>(n << 1));
    for (auto&& e : f1) {
        graph[e.first ^ 1][e.second] = true;
        graph[e.second ^ 1][e.first] = true;
    }
    auto scc = contract(graph);
    for (int i = 0; i < n; ++ i) {
        if (scc[i << 1] == scc[i << 1 | 1]) {
            return {};
        }
    }
    int m = *std::max_element(scc.begin(), scc.end()) + 1;
    Graph ngraph(m, std::vector<int>(m));
    for (int i = 0; i < n << 1; ++ i) {
        for (int j = 0; j < n << 1; ++ j) {
            if (graph[i][j]) {
                ngraph[scc[i]][scc[j]] = true;
            }
        }
    }
    std::vector<int> order(m);
    std::iota(order.begin(), order.end(), 0);
    auto decendents = closure(ngraph, order);
    for (int i = 0; i < m; ++ i) {
        for (int j = i; j < m; ++ j) {
            std::swap(ngraph[i][j], ngraph[j][i]);
        }
    }
    std::reverse(order.begin(), order.end());
    auto ancestors = closure(ngraph, order);
    for (auto&& e : f2) {
        auto ancestor_union = ancestors[scc[e.first]] | ancestors[scc[e.second]];
        auto decendent_union = decendents[scc[e.first ^ 1]] | decendents[scc[e.second ^ 1]];
        if (!(ancestor_union & decendent_union).any()) {
            graph[e.first][e.first ^ 1] = true;
            graph[e.second][e.second ^ 1] = true;
            auto scc = contract(graph);
            std::vector<bool> solution(n);
            for (int i = 0; i < n; ++ i) {
                solution[i] = scc[i << 1] < scc[i << 1 | 1];
            }
            return solution;
        }
    }
    return {};
}

int main()
{
    int n, m1, m2;
    scanf("%d%d%d", &n, &m1, &m2);
    auto relabel = [n](int x) {
        if (x > 0) {
            return x - 1 << 1;
        }
        return - x - 1 << 1 | 1;
    };
    Formula formula1(m1), formula2(m2);
    for (int i = 0; i < m1; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        formula1[i] = {relabel(a), relabel(b)};
    }
    for (int i = 0; i < m2; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        formula2[i] = {relabel(a), relabel(b)};
    }
    auto result = solve(n, formula1, formula2);
    if (result.empty()) {
        result = solve(n, formula2, formula1);
    }
    if (result.empty()) {
        puts("SIMILAR");
    } else {
        for (int i = 0; i < n; ++ i) {
            printf("%d%c", (int)result[i], " \n"[i == n - 1]);
        }
    }
}
