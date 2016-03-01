#include <cstdio>
#include <cstring>
#include <iostream>
#include <vector>

const int N = 2500;
const int K = 6;

int n, m, k, begin[N], end[N], count[N * (K + 1) + 1], prev[N * N + 2], next[N * N + 2], lengths[K + 1];
char board[N][N + 1];
std::vector<int> events[N], row[N];

void append(int& p, int q)
{
    next[p] = q;
    prev[q] = p;
    p = q;
}

int get_x(int loc)
{
    return loc == n * m + 1 ? -1 : loc / m;
}

int get_length(int loc)
{
    return get_x(next[loc]) - get_x(loc);
}

int main()
{
    scanf("%d%d%d", &n, &m, &k);
    for (int i = 0; i < n; ++ i) {
        scanf("%s", board[i]);
        begin[i] = end[i] = 0;
        for (int j = 0; j < m; ++ j) {
            if (board[i][j] == '1') {
                row[i].push_back(j);
            }
        }
    }
    long long result = 0;
    for (int left = 0; left < m; ++ left) {
        memset(count, 0, sizeof(*count) * (n * (k + 1) + 1));
        int sum = 0;
        count[sum] ++;
        for (int i = 0; i < n; ++ i) {
            while (begin[i] < end[i] && row[i][begin[i]] < left) {
                begin[i] ++;
            }
            while (end[i] < (int)row[i].size() && end[i] - begin[i] <= k) {
                int j = row[i][end[i] ++];
                events[j].push_back(i * m + j);
            }
            sum += end[i] - begin[i];
            count[sum] ++;
        }
        int segments = 0;
        for (int i = k; i <= n * (k + 1); ++ i) {
            segments += k ? count[i] * count[i - k] : count[i] * (count[i] - 1) / 2;
        }
        int head = n * m + 1;
        for (int i = 0; i < n; ++ i) {
            for (int j = begin[i]; j < end[i]; ++ j) {
                append(head, i * m + row[i][j]);
            }
        }
        append(head, n * m);
        for (int right = m; right > left; -- right)
        {
            if (right < m) {
                for (int i : events[right]) {
                    memset(lengths, 0, sizeof(*lengths) * (k + 1));
                    for (int j = 0, p = i; j <= k && p < n * m; ++ j, p = next[p]) {
                        lengths[j] = get_length(p);
                    }
                    for (int j = 0, p = prev[i]; j <= k; ++ j) {
                        int l = get_length(p);
                        segments += l * lengths[k - j];
                        if (j < k) {
                            segments -= l * lengths[k - 1 - j];
                        }
                        if (p >= n * m) {
                            break;
                        }
                        p = prev[p];
                    }
                    next[prev[i]] = next[i];
                    prev[next[i]] = prev[i];
                }
            }
            result += segments;
        }
    }
    std::cout << result << std::endl;
    return 0;
}
