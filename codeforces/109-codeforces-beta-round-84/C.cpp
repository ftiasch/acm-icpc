// Codeforces Beta Round #84
// Problem C -- Lucky Tree
#include <cstdio>
#include <cstring>
#include <iostream>
#include <algorithm>

const int N = 100000;

int n, parent[N], size[N];

int find(int u) {
	if (parent[u] == -1) {
		return u;
	}
	return parent[u] = find(parent[u]);
}

bool is_lucky(int n) {
	for (; n > 0; n /= 10) {
		if (n % 10 != 4 && n % 10 != 7) {
			return false;
		}
	}
	return true;
}

int main() {
//freopen("C.in", "r", stdin);
	scanf("%d", &n);
	memset(parent, -1, sizeof(parent));
	for (int i = 0; i < n - 1; ++ i) {
		int a, b, c;
		scanf("%d%d%d", &a, &b, &c);
		a --, b --;
		if (!is_lucky(c)) {
			parent[find(a)] = find(b);
		}
	}
	memset(size, 0, sizeof(size));
	for (int i = 0; i < n; ++ i) {
		size[find(i)] ++;
	}
	long long answer = 0;
	for (int i = 0; i < n; ++ i) {
		int count = n - size[find(i)];
		answer += (long long)count * (count - 1);
	} 
	std::cout << answer << std::endl;
	return 0;
}