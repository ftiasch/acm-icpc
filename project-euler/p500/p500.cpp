#include <iostream>
#include <set>
#include <utility>
#include <vector>

const int MOD = 500500507;

int main()
{
	std::vector <std::pair <int, int>> divs(2);
	int result = 1;
	for (int n = 2, demand = 500500; demand; ++ n) {
		int p = 2;
		while (p * p <= n && n % p != 0) {
			p ++;
		}
		if (p * p > n) {
			p = n;
		}
		if (divs[n / p].first && divs[n / p].first != p) {
			divs.push_back({-1, 0});
		} else {
			divs.push_back({p, divs[n / p].second + 1});
		}
		if (~divs[n].first && __builtin_popcount(divs[n].second) == 1) {
			demand --;
			result = (long long)result * n % MOD;
		}
	}
	std::cout << result << std::endl;
	return 0;
}
