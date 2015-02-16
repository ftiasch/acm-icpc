#include <cassert>
#include <cstdio>
#include <iostream>
#include <vector>

const int N = 10000000;

int min_div[N + 1];

void ext_gcd(int a, int b, int &x, int &y)
{
	if (b == 0) {
		x = a, y = 0;
	} else {
		ext_gcd(b, a % b, y, x);
		y *= -1;
		x *= -1;
		y += a / b * x;
	}
}

int solve(int n)
{
	if (n == 1) {
		return 0;
	}
	std::vector <int> divs;
	for (int t = n; t > 1; t /= min_div[t]) {
		divs.push_back(min_div[t]);
	}
	int k = divs.size();
	int result = 1;
	for (int mask = 0; mask < 1 << k; ++ mask) {
		int a = 1;
		int b = 1;
		for (int i = 0; i < k; ++ i) {
			(mask >> i & 1 ? a : b) *= divs[i];
		}
		int x, y;
		ext_gcd(a, b, x, y);
		int t = x / b;
		x -= t * b;
		//y += t * a;
		while (x <= 0) {
			x += b;
			//y += a;
		}
		while (x - b > 0) {
			x -= b;
			//y -= a;
		}
		if (a * x < n) {
			result = std::max(result, a * x);
		}
	}
	return result;
}

int pusu(int n)
{
	int x = n - 1;
	while ((long long)x * (x - 1) % n != 0) {
		x --;
	}
	return x;
}

int main()
{
	std::vector <int> primes;
	memset(min_div, -1, sizeof(min_div));
	for (int p = 2; p <= N; ++ p) {
		if (min_div[p] == -1) {
			min_div[p] = p;
			primes.push_back(p);
		}
		for (int i = 0; i < (int)primes.size() && p * primes[i] <= N; ++ i) {
			min_div[p * primes[i]] = primes[i];
			if (p % primes[i] == 0) {
				min_div[p * primes[i]] = min_div[p] * primes[i];
				break;
			}
		}
	}
	long long result = 0;
	for (int n = 1; n <= N; ++ n) {
		result += solve(n);
	}
	std::cout << result << std::endl;
	return 0;
}
