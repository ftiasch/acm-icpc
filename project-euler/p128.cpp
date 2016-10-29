#include <cassert>
#include <cstdlib>
#include <cstdio>
#include <map>
#include <vector>

struct Point
{
    Point(int x = 0, int y = 0) : x(x), y(y) {}

    Point& operator += (const Point& other)
    {
        x += other.x;
        y += other.y;
        return *this;
    }

    int x, y;
};

const Point DELTA[] = {{-1, -1}, {-1, 0}, {0, 1}, {1, 1}, {1, 0}, {0, -1}};

Point operator + (Point a, const Point &b)
{
    return a += b;
}

bool operator < (const Point &a, const Point& b)
{
    return a.x < b.x || a.x == b.x && a.y < b.y;
}

// 1, 2, +6, +12
// start[d] = -4 + 6 * sum_{k = 2}^d k = -4 + 3 * (2 + d)(d - 1) = -4 + 3 * (d + d^2 - 2) = 3 * d^2 + 3 * d - 10

bool is_prime(long long n)
{
    if (n < 2) {
        return false;
    }
    for (long long d = 2; d * d <= n; ++ d) {
        if (n % d == 0) {
            return false;
        }
    }
    return true;
}

const int N = 2000;

int main()
{
    int count = 1; // P(1) = 3
    long long p = 1;
    long long q = 2;
    int d = 1;
    while (true) {
        auto r = q + 6 * d;
        auto qq = r - 1;
        d ++;
        auto rr = r + 6 * d - 1;
        if (is_prime(qq - q) && is_prime(r + 1 - q) && is_prime(rr - q)) {
            count ++;
            if (count == N) {
                printf("%lld\n", q);
                return 0;
            }
        }
        if (qq > 7 && is_prime(qq - q) && is_prime(qq - p) && is_prime(rr - 1 - qq)) {
            count ++;
            if (count == N) {
                printf("%lld\n", qq);
                return 0;
            }
        }
        p = q;
        q = r;
    }
    // std::map<Point, int> id;
    // Point p(0, 0);
    // id[p] = 1;
    // std::vector<Point> layer {p};
    // int count = 1;
    // int n = 1;
    // while (true) {
    //     auto pp = p;
    //     p += Point(1, 0);
    //     std::vector<Point> new_layer;
    //     for (int k = 0; k < 6; ++ k) {
    //         for (int i = 0; i < n; ++ i) {
    //             new_layer.push_back(p);
    //             id[p] = ++ count;
    //             p += DELTA[k];
    //         }
    //     }
    //     for (auto&& q : layer) {
    //         auto&& x = id.at(q);
    //         int c = 0;
    //         for (int k = 0; k < 6; ++ k) {
    //             if (is_prime(std::abs(x - id.at(q + DELTA[k])))) {
    //                 c ++;
    //             }
    //         }
    //         if (c == 3) {
    //             printf("! %d\n", x);
    //             assert(x == id.at(pp) || x == id.at(pp + DELTA[2]));
    //         }
    //     }
    //     layer.swap(new_layer);
    //     n ++;
    // }
}
