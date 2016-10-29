#include <cstdio>
#include <cstring>
#include <map>

const int N = 8;

using LL = long long;

int three[N], count[3][3][3];

int main()
{
    three[0] = 1;
    for (auto i = 1; i < N; ++ i) {
        three[i] = three[i - 1] * 3;
    }
    for (auto a = 0; a < 3; ++ a) {
        for (auto b = 0; b < 3; ++ b) {
            for (auto c = 0; c < 3; ++ c) {
                for (auto d = 0; d < 3; ++ d) {
                    if (a != d && b != d && c != d) {
                        count[a][b][c] ++;
                    }
                }
            }
        }
    }
    std::map<int, LL> ways;
    ways[0] = ways[1] = ways[2] = 1;
    for (auto r = 2; r <= N; ++ r) {
        {
            std::map<int, LL> new_ways;
            for (auto&& iterator : ways) {
                for (auto c = 0; c < 3; ++ c) {
                    new_ways[iterator.first * 3 + c] += iterator.second;
                }
            }
            ways.swap(new_ways);
        }
        for (auto i = 1; i < r; ++ i) {
            std::map<int, LL> new_ways;
            for (auto&& iterator : ways) {
                auto mask = iterator.first;
                auto a = mask / three[i - 1] % 3;
                auto b = mask / three[i] % 3;
                mask -= b * three[i];
                for (auto c = 0; c < 3; ++ c) {
                    new_ways[mask + three[i] * c] += iterator.second * count[a][b][c];
                }
            }
            ways.swap(new_ways);
        }
    }
    auto result = 0LL;
    for (auto&& iterator : ways) {
        result += iterator.second;
    }
    printf("%lld\n", result);
}
