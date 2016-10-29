#include <iostream>

const int N = 10;

bool is_prime(long long n)
{
    for (long long d = 2; d * d <= n; ++ d) {
        if (n % d == 0) {
            return false;
        }
    }
    return true;
}

long long dfs(int digit, int index, int quota, long long number)
{
    if (index == N) {
        return is_prime(number) ? number : 0;
    }
    long long result = 0;
    for (int d = !index; d < 10; ++ d) {
        if (index + 1 + (quota - (d == digit)) <= N) {
            result += dfs(digit, index + 1, quota - (d == digit), number * 10 + d);
        }
    }
    return result;
}

int main()
{
    long long result = 0;
    for (int d = 0; d < 10; ++ d) {
        int k = N;
        while (true) {
            auto ret = dfs(d, 0, k, 0);
            if (ret > 0) {
                result += ret;
                break;
            }
            k --;
        }
    }
    std::cout << result << std::endl;
}
