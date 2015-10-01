#include "testlib.h"

int main(int argc, char* argv[])
{
    registerGen(argc, argv, 1);
    int n = std::atoi(argv[2]);
    int m = std::atoi(argv[3]);
    int k = std::atoi(argv[4]);
    std::vector <int> ori(n), pre(n);
    for (int i = 0; i < n; ++ i) {
        ori[i] = rnd.next(0, 1);
        pre[i] = rnd.next(0, 2);
    }
    std::vector <std::pair <int, int>> edges;
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            if (ori[i] != ori[j] && (pre[i] & pre[j]) == 0) {
                edges.push_back({i, j});
            }
        }
    }
    shuffle(edges.begin(), edges.end());
    m = std::min(m, (int)edges.size());
    printf("%d %d\n", n, m);
    for (int i = 0; i < n; ++ i) {
        printf("%d%c", rnd.next(1, k), " \n"[i == n - 1]);
    }
    for (int i = 0; i < n; ++ i) {
        std::vector <int> candidates;
        for (int mask = 1; mask <= 3; ++ mask) {
            if ((mask & pre[i]) == pre[i]) {
                candidates.push_back(mask);
            }
        }
        shuffle(candidates.begin(), candidates.end());
        printf("%d%c", candidates[0], " \n"[i == n - 1]);
    }
    for (int i = 0; i < m; ++ i) {
        int a = edges[i].first  + 1;
        int b = edges[i].second + 1;
        if (rnd.next(0, 1)) {
            std::swap(a, b);
        }
        printf("%d %d\n", a, b);
    }
    return 0;
}
