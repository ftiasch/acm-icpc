#include <algorithm>
#include <cmath>
#include <cstdio>
#include <cstdlib>
#include <iostream>
#include <tuple>
#include <vector>

const double PI = acos(-1.0);

struct Point
{
    Point(double x = 0., double y = 0.) : x(x), y(y) {}

    Point& operator -= (const Point& other)
    {
        x -= other.x;
        y -= other.y;
        return *this;
    }

    double x, y;
};

Point operator - (Point a, const Point& b)
{
    return a -= b;
}

double dot(const Point& a, const Point& b)
{
    return a.x * b.x + a.y * b.y;
}

const int N = 180;

double rad[N];
Point top[N][N];

bool debug_flag = false;

auto check(Point a, const Point& o, Point b)
{
    a -= o;
    b -= o;
    auto rad = 180 * acos(dot(a, b) / sqrt(dot(a, a) * dot(b, b))) / PI;
    auto rad_i = (int)(round(rad));
    for (int d = -1; d <= 1; ++ d) {
        if (std::abs(rad - (rad_i + d)) < 1e-9) {
            return rad_i + d;
        }
    }
    return -1;
}

bool visited[N][N][N];

using T4 = std::tuple<int, int, int, int>;

auto normalize(int a, int b, int c, int d)
{
    T4 result {a, b, c, d};
    result = std::min(result, {b, a, d, c});
    result = std::min(result, {c, d, a, b});
    result = std::min(result, {d, c, b, a});
    return result;
}

int main()
{
    for (auto i = 0; i < N; ++ i) {
        rad[i] = 1.0 * i / N * PI;
    }
    for (auto a = 1; a < N; ++ a) {
        for (auto b = 1; a + b < N; ++ b) {
            auto tanA = tan(rad[a]);
            auto tanB = tan(rad[b]);
            auto x = 1 / (1 + tanA / tanB);
            auto y = x * tanA;
            top[a][b] = Point(x, y);
        }
    }
    Point o { 0., 0.};
    std::vector<T4> results;
    for (auto a = 1; a < N; ++ a) {
        for (auto b = 1; a + b < N; ++ b) {
            auto p = top[a][b];
            p.y *= -1;
            for (auto c = a; a + c < N; ++ c) {
                for (auto d = 1; b + d < N && c + d < N; ++ d) {
                    auto q = top[c][d];
                    auto OPQ = check(o, p, q);
                    if (~OPQ) {
                        auto OQP = check(o, q, p);
                        if (~OQP) {
                            results.push_back(
                                    std::min(normalize(a, b, c, d), normalize(OPQ, OQP, N - a - b - OPQ, N - c - d - OQP)));
                        }
                    }
                    // auto& ref = visited[a + c][N - a - b][b + d];
                    // if (!ref && check(o, p, q) && check(o, q, p)) {
                    //     ref = true;
                    //     result ++;
                    //     std::vector<int> angs { a + c, N - a - b, b + d, N - b - d };
                    //     for (int _ = 0; _ < 2; ++ _) {
                    //         for (int i = 0; i < 4; ++ i) {
                    //             visited[angs[i]][angs[i + 1 & 3]][angs[i + 2 & 3]] = true;
                    //         }
                    //         std::reverse(angs.begin(), angs.end());
                    //     }
                    // }
                }
            }
        }
    }
    std::sort(results.begin(), results.end());
    results.erase(std::unique(results.begin(), results.end()), results.end());
    // for (auto&& result : results) {
    //     printf("%4d %4d %4d %4d\n", std::get<0>(result), std::get<1>(result), std::get<2>(result), std::get<3>(result));
    // }
    std::cout << results.size() << std::endl;
}
