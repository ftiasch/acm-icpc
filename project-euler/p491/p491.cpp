#include <cstdio>
#include <cstring>

const int N = 10;

int power[N + 1];
long long ways[59049][11];

int main()
{
	power[0] = 1;
	for (int i = 1; i <= N; ++ i) {
		power[i] = power[i - 1] * 3;
	}
	memset(ways, 0, sizeof(ways));
	ways[0][0] = 1;
	for (int mask = 0; mask < power[N]; ++ mask) {
		for (int r = 0; r < 11; ++ r) {
			for (int d = 0; d < 10; ++ d) {
				if (!mask && !d) {
					continue;
				}
				if (mask / power[d] % 3 < 2) {
					ways[mask + power[d]][(r * 10 + d) % 11] += ways[mask][r];
				}
			}
		}
	}
	printf("%lld\n", ways[power[N] - 1][0]);
	return 0;
}
