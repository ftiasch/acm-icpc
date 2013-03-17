// SGU 305 -- Exhibition
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

const int N = 300;

int n, m, a[N], b[N];
std::vector <int> choices[N];

std::vector <int> graph[N];
bool visit[N];
int match[N * N];

bool find(int u) {
	if (visit[u]) {
		return false;
	}
    visit[u] = true;
    foreach (iter, graph[u]) {
        int v = *iter;
        if (match[v] == -1 || find(match[v])) {
            match[v] = u;
            return true;
        }
    }
    return false;
}

int answer[N];

int main() {
	scanf("%d%d", &n, &m);
	for (int i = 0; i < n; ++ i) {
		scanf("%d%d", a + i, b + i);
	}
	std::vector <int> values;
	for (int i = 0; i < n; ++ i) {
        if (a[i] == 0) {
            if (1 <= b[i] && b[i] <= m) {
                choices[i].push_back(b[i]);
            }
        } else {
            long long x = b[i];
            if (a[i] > 0) {
                if (x < 1) {
                    x = 1 + (a[i] - (1 - x) % a[i]) % a[i];
                }
            } else {
                if (x > m) {
                    x = m - (-a[i] - (x - m) % -a[i]) % -a[i];
                }
            }
            for (int j = 0; j < n; ++ j) {
                if (1 <= x && x <= m) {
                    choices[i].push_back(x);
                }
                x += a[i];
            }
        }
        foreach (iter, choices[i]) {
            values.push_back(*iter);
        }
	}
	std::sort(values.begin(), values.end());
	values.erase(std::unique(values.begin(), values.end()), values.end());
	for (int i = 0; i < n; ++ i) {
        foreach (iter, choices[i]) {
            graph[i].push_back(std::lower_bound(values.begin(), values.end(), *iter) - values.begin());
        }
	}
	memset(match, -1, sizeof(match));
	for (int i = 0; i < n; ++ i) {
		if (find(i)) {
			memset(visit, 0, sizeof(visit));
		}
	}
    memset(answer, -1, sizeof(answer));
    memset(visit, 0, sizeof(visit));
    for (int i = 0; i < (int)values.size(); ++ i) {
        if (match[i] != -1) {
            if (values[i] <= n) {
                visit[values[i] - 1] = true;
            }
            answer[match[i]] = values[i];
        }
    }
    for (int i = 0; i < n; ++ i) {
        if (answer[i] == -1) {
            answer[i] = 0;
            while (visit[answer[i]]) {
                answer[i] ++;
            }
            visit[answer[i] ++] = true;
        }
        printf("%d%c", answer[i], i == n - 1 ? '\n' : ' ');
    }
	return 0;
}
