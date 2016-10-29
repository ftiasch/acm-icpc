#include <atomic>
#include <cstdio>
#include <thread>
#include <vector>

bool check(int n)
{
    while (n) {
        if (~(n % 10) & 1) {
            return false;
        }
        n /= 10;
    }
    return true;
}

int rev(int n)
{
    int result = 0;
    while (n) {
        result *= 10;
        result += n % 10;
        n /= 10;
    }
    return result;
}

int main()
{
    const int N = 1000000000;
    std::vector<std::thread> threads;
    std::atomic_int result(0);
    for (int t = 0; t < 4; ++ t) {
        threads.emplace_back([t, &result]()
        {
            int local_result = 0;
            for (int i = t; i < N; i += 4) {
                local_result += i % 10 && check(i + rev(i));
            }
            result += local_result;
        });
    }
    for (auto& t : threads) {
        t.join();
    }
    printf("%d\n", result.load());
}
