#include <algorithm>
#include <cmath>
#include <cassert>
#include <cstdio>
#include <tuple>
#include <utility>
#include <vector>

#define ALL(v) (v).begin(), (v).end()

const int N = 120000;

void generate(std::vector<std::pair<int, int>>& triangles, int u1, int v1, int u2, int v2)
{
    auto u = u1 + u2;
    auto v = v1 + v2;
    if (u * v > N) {
        return;
    }
    for (auto d = 1LL; d * u * v <= N; ++ d) {
        auto y = d * u * v;
        for (auto _ = 0; _ < 2; ++ _) {
            auto t1 = d * v * v;
            auto t2 = d * u * u;
            (_ ? t2 : t1) *= 3;
            if (t1 > t2) {
                auto z = t1 + t2;
                if (z % 4) {
                    continue;
                }
                z /= 4;
                auto x = t1 - y - 2 * z;
                if (x % 2 || x <= 0) {
                    continue;
                }
                x /= 2;
                if (x > y) {
                    std::swap(x, y);
                }
                assert(x * x + y * y + x * y == z * z);
                if (x + y <= N) {
                    triangles.emplace_back(x, y);
                }
            }
        }
    }
    generate(triangles, u1, v1, u, v);
    generate(triangles, u, v, u2, v2);
}

int main()
{
    std::vector<std::pair<int, int>> triangles;
    generate(triangles, 0, 1, 1, 1);
    std::vector<std::vector<int>> another(N + 1);
    for (auto&& triangle : triangles) {
        another.at(triangle.second).push_back(triangle.first);
    }
    for (auto& v : another) {
        std::sort(ALL(v));
    }
    std::vector<bool> exist(N + 1);
    for (auto&& triangle : triangles) {
        int b, c;
        std::tie(b, c) = triangle;
        std::vector<int> common;
        std::set_intersection(ALL(another.at(b)), ALL(another.at(c)), std::back_inserter(common));
        for (auto&& a : common) {
            if (a + b + c <= N) {
                exist.at(a + b + c) = true;
            }
        }
    }
    auto result = 0LL;
    for (int n = 1; n <= N; ++ n) {
        if (exist.at(n)) {
            result += n;
        }
    }
    printf("%lld\n", result);
}
