#include "testlib.h"

#include <utility>
#include <vector>

int main(int argc, char* argv[])
{
    registerGen(argc, argv, 1);
    int n = std::atoi(argv[2]);
    int k = std::atoi(argv[3]);
    int m = std::atoi(argv[4]);
    int result = rnd.next(1, k - 1);
    std::vector <int> vertices;
    for (int i = 0; i < n; ++ i) {
        vertices.push_back(i);
    }
    shuffle(vertices.begin(), vertices.end());
    std::vector <bool> marked(n);
    for (int i = 0; i < result; ++ i) {
        marked[vertices[i]] = true;
    }
    std::vector <std::pair <int, int>> edges;
    for (int i = 0; i < k; ++ i) {
        for (int j = i + 1; j < n; ++ j) {
            if (marked[i] || marked[j]) {
                edges.push_back({i, j});
            }
        }
    }
    shuffle(edges.begin(), edges.end());
    m = std::min(m, (int)edges.size());
    printf("%d %d\n", n, m);
    for (int i = 0; i < m; ++ i) {
        int a = edges[i].first + 1;
        int b = edges[i].second + 1;
        if (rnd.next(0, 1)) {
            printf("%d %d\n", a, b);
        } else {
            printf("%d %d\n", b, a);
        }
    }
    return 0;
}
