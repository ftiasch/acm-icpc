#include <vector>
#include <string>
#include <climits>
using namespace std;

class PolynomialGCD {
private:
    static const int MOD = (int)1e9 + 7;

    bool is_prime(int n) {
        for (int d = 2; d * d <= n; ++ d) {
            if (n % d == 0) {
                return false;
            }
        }
        return true;
    }

public:
    int gcd(string const &s) {
        int n = s.size();
        int result = 1;
        for (int p = 2; p <= n; ++ p) {
            if (!is_prime(p)) {
                continue;
            }
            int pp = p;
            while (pp * p <= n) {
                pp *= p;
            }
            std::vector <int> minimum(pp, INT_MAX);
            std::vector <int> count(pp);
            for (int i = 0; i < n; ++ i) {
                int d = s[i] - '0';
                count[i % pp] += d;
                minimum[i % pp] = std::min(minimum[i % pp], d);
            }
            for (int i = n; i < std::min(pp * p, n + pp); ++ i) {
                minimum[i % pp] = 0;
            }
            while (pp > 1) {
                pp /= p;
                std::vector <int> new_count(pp);
                std::vector <int> new_minimum(pp, INT_MAX);
                for (int i = 0; i < pp * p; ++ i) {
                    new_count[i % pp] += count[i];
                    new_minimum[i % pp] = std::min(new_minimum[i % pp], minimum[i] + count[i]);
                }
                count.swap(new_count);
                minimum.swap(new_minimum);
            }
            int e = minimum[0];
            while (e --) {
                result = (long long)result * p % MOD;
            }
        }
        return result;
    }
};
