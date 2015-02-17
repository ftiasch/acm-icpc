#include <cstdio>
#include <iostream>
#include <set>
#include <utility>
#include <vector>

typedef long long Long;

Long solve(int n)
{
	std::vector <int> min_div(n + 1, -1);
	std::vector <int> primes;
	for (int d = 2; d <= n; ++ d) {
		if (min_div[d] == -1) {
			primes.push_back(d);
			min_div[d] = d;
		}
		for (int i = 0; i < (int)primes.size() && d * primes[i] <= n; ++ i) {
			min_div[d * primes[i]] = primes[i];
			if (d % primes[i] == 0) {
				break;
			}
		}
	}
	std::set <std::pair <int, int>> exists;
	Long result = 0;
	for (int i = n; i >= 2; -- i) {
		int tmp = i;
		int p = min_div[tmp];
		while (tmp % p == 0) {
			tmp /= p;
		}
		if (tmp > 1) {
			int q = min_div[tmp];
			while (tmp % q == 0) {
				tmp /= q;
			}
			if (tmp == 1 && !exists.count({p, q})) {
				result += i;
				exists.insert({p, q});
			}
		}
	}
	return result;
}

int main()
{
	std::cout << solve(100) << std::endl;
	std::cout << solve(10000000) << std::endl;
	return 0;
}
