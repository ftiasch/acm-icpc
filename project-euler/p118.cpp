#include <cstdio>
#include <set>
#include <vector>

bool is_prime(int n)
{
    if (n <= 1) {
        return false;
    }
    for (int d = 2; d * d <= n; ++ d) {
        if (n % d == 0) {
            return false;
        }
    }
    return true;
}

std::set<std::vector<int>> sets;

void dfs(int mask, int number, std::vector<int> digits)
{
    if (mask) {
        for (int d = 1; d < 10; ++ d) {
            if (mask >> d & 1) {
                int new_mask = mask & ~(1 << d);
                int new_number = number * 10 + d;
                dfs(new_mask, new_number, digits);
                if (is_prime(new_number)) {
                    digits.push_back(new_number);
                    dfs(new_mask, 0, digits);
                    digits.pop_back();
                }
            }
        }
    } else if (!number) {
        std::sort(digits.begin(), digits.end());
        sets.insert(digits);
    }
}

int main()
{
    dfs((1 << 10) - 2, 0, {});
    printf("%d\n", (int)sets.size());
}
