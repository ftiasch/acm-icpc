#include <cstdio>
#include <cstring>
#include <algorithm>
#include <set>
#include <utility>
#include <vector>

const int N = 15;

const int DELTA_X[4] = {-1,  0, 0, 1};
const int DELTA_Y[4] = { 0, -1, 1, 0};

int n;

int visited[N + N][N + N];

void add_pair(std::vector <std::pair <int, int>> &pairs, int x, int y)
{
	if (x > y) {
		std::swap(x, y);
	}
	pairs.push_back({x, y});
}

std::set <std::vector <std::pair <int, int>>> patterns;

void dfs(int count, int x, int y)
{
	if (~visited[x][y]) {
		return;
	}
	visited[x][y] = count;
	if (count < n - 1) {
		for (int i = 0; i < 4; ++ i) {
			if (!count && !i) {
				continue;
			}
			dfs(count + 1, x + DELTA_X[i], y + DELTA_Y[i]);
		}
	} else {
		std::vector <std::pair <int, int>> pairs;
		for (int i = 0; i < n + n; ++ i) {
			for (int j = 0; j < n + n; ++ j) {
				if (~visited[i][j]) {
					if (i + 1 < n + n && ~visited[i + 1][j]) {
						add_pair(pairs, visited[i][j], visited[i + 1][j]);
					}
					if (j + 1 < n + n && ~visited[i][j + 1]) {
						add_pair(pairs, visited[i][j], visited[i][j + 1]);
					}
				}
			}
		}
		std::sort(pairs.begin(), pairs.end());
		patterns.insert(pairs);
	}
	visited[x][y] = -1;
}

int main()
{
	n = 15;
	{
		memset(visited, -1, sizeof(visited));
		dfs(0, n, n);
	}
	int result = 0;
	for (int mask = 0; mask < 1 << n; ++ mask) {
		int max_score = 0;
		for (const auto& pattern : patterns) {
			int score = 0;
			for (const auto& pair : pattern) {
				score += (mask >> pair.first & 1) && (mask >> pair.second & 1);
			}
			max_score = std::max(max_score, score);
		}
		result += max_score;
	}
	printf("%d\n", result);
	return 0;
}
