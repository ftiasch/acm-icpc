#include "testlib.h"

#include <set>
#include <utility>

int main(int argc, char* argv[])
{
    registerGen(argc, argv, 1);
    int n = std::atoi(argv[2]);
    int m = std::atoi(argv[3]);
    int k = std::atoi(argv[4]);
    int a = rnd.next(1, k);
    int b = rnd.next(1, k);
    m = std::min((long long)m, (long long)n * (n - 1) / 2);
    printf("%d %d %d %d\n", n, m, a, b);
    std::set <std::pair <int, int>> edges;
    if (m) {
        edges.insert({0, n - 1});
    }
    for (int i = 1; i < n - 1; ++ i) {
        if (rnd.next(0, 1)) {
            edges.insert({0, i});
        } else {
            edges.insert({i, n - 1});
        }
    }
    while ((int)edges.size() < m) {
        int a = rnd.next(0, n - 1);
        int b = rnd.next(0, n - 1);
        if (a > b) {
            std::swap(a, b);
        }
        if (a != b) {
            edges.insert({a, b});
        }
    }
    std::vector <std::pair <int, int>> pp;
    for (const auto &it : edges) {
        pp.push_back(it);
    }
    shuffle(pp.begin(), pp.end());
    for (const auto &it : pp) {
        int a = it.first + 1;
        int b = it.second + 1;
        if (rnd.next(0, 1)) {
            std::swap(a, b);
        }
        printf("%d %d\n", a, b);
    }
    return 0;
}
