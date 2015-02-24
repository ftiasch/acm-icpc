#include <algorithm>
#include <cstdio>
#include <vector>

const int MOD = (int)1e8;

int s(int n)
{
	std::vector <int> phi(n + 1);
	std::vector <int> primes;
	for (int d = 2; d <= n; ++ d) {
		if (!phi[d]) {
			phi[d] = d - 1;
			primes.push_back(d);
		}
		for (int i = 0; i < (int)primes.size() && d * primes[i] <= n; ++ i) {
			phi[d * primes[i]] = phi[d] * (primes[i] - 1);
			if (d % primes[i] == 0) {
				phi[d * primes[i]] = phi[d] * primes[i];
				break;
			}
		}
	}
	int result = 0;
	std::vector <int> count(n + 1);
	for (int i = 6; i <= n; ++ i) {
		int ways = 0;
		if (i == 6) {
			ways = 1;
		} else {
			for (int k = phi[i]; k <= n; k += k & -k) {
				ways += count[k];
				if (ways >= MOD) {
					ways -= MOD;
				}
			}
		}
		result += ways;
		if (result >= MOD) {
			result -= MOD;
		}
		for (int k = i - 1; k; k -= k & -k) {
			count[k] += ways;
			if (count[k] >= MOD) {
				count[k] -= MOD;
			}
		}
		for (int k = phi[i]; k; k -= k & -k) {
			count[k] += MOD - ways;
			if (count[k] >= MOD) {
				count[k] -= MOD;
			}
		}
	}
	return result;
}

int main()
{
	printf("%d\n", s(10));
	printf("%d\n", s(100));
	printf("%d\n", s(10000));
	printf("%d\n", s(20000000));
	return 0;
}
