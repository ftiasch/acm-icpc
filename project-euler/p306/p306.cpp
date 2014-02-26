#include <cstdio>
#include <vector>
#include <unordered_set>

int get_sg(const std::vector <int> &sg, int n)
{
    if (n < (int)sg.size()) {
        return sg[n];
    }
    return sg[69 + (n - 69) % 34];
}

int solve(int n)
{
    std::vector <int> sg(34 * 3 + 1, 0);
    for (int n = 1; n <= 34 * 3; ++ n) {
        std::unordered_set <int> set;
        for (int i = 0; i <= n - 2; ++ i) {
            set.insert(sg[i] ^ sg[n - 2 - i]);
        }
        while (set.count(sg[n])) {
            sg[n] ++;
        }
    }
    int result = 0;
    for (int i = 1; i <= n; ++ i) {
        result += get_sg(sg, i) != 0;
    }
    return result;
}

int main()
{
    printf("%d\n", solve(5));
    printf("%d\n", solve(50));
    printf("%d\n", solve(1000000));
}
