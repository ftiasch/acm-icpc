#include <cstdio>
#include <bitset>
#include <vector>

const auto N = 2000000000;

std::bitset<N + 1> result, gao;

int main()
{
    result.flip();
    for (auto&& k : std::vector<int>{1, 2, 3, 7}) {
        gao.reset();
        for (auto b = 1; k * b * b <= N; ++ b) {
            auto sum = 1 + k * b * b;
            for (auto a = 3; sum <= N; a += 2) {
                gao.set(sum);
                sum += a;
            }
        }
        result &= gao;
    }
    printf("%ld\n", result.count());
}
