#include <cstdio>
#include <set>
#include <vector>

const int N = 100000000;

int main()
{
    std::set<int> numbers;
    for (int a = 1; a * a < N; ++ a) {
        int sum = a * a;
        for (int b = a + 1; ; ++ b) {
            sum += b * b;
            if (sum >= N) {
                break;
            }
            std::vector<int> digits;
            int tmp = sum;
            while (tmp) {
                digits.push_back(tmp % 10);
                tmp /= 10;
            }
            std::vector<int> rdigits(digits);
            std::reverse(rdigits.begin(), rdigits.end());
            if (digits == rdigits) {
                numbers.insert(sum);
            }
        }
    }
    long long result = 0;
    for (auto&& x : numbers) {
        result += x;
    }
    printf("%lld\n", result);
}
