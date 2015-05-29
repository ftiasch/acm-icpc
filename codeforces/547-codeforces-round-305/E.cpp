#include <cstdio>
#include <cstring>
#include <utility>
#include <vector>
#include <algorithm>

const int N = (int)2e5;
const int Q = (int)5e5;

int n, m, q, start[N], s[N << 1], step,
    array[N << 1], rank[N << 1], count[N + 1 << 1], tmpA[N << 1], tmpR[2][N << 1],
    result[Q];
std::pair <int, int> range[N];

bool compare(int i, int j) {
    return s[i + step] < s[j + step];
}

int query(int k) {
    int result = 0;
    for (; k < m; k += ~k & k + 1) {
        result += count[k];
    }
    return result;
}

int main()
{
    scanf("%d%d", &n, &q);
    m = 0;
    for (int i = 0; i < n; ++ i) {
        static char buffer[N + 1];
        scanf("%s", buffer);
        s[m ++] = i;
        start[i] = m;
        for (int j = 0; buffer[j]; ++ j) {
            s[m ++] = n + buffer[j] - 'a';
        }
    }
    memset(count, 0, sizeof(*count) * (n + 26));
    for (int i = 0; i < m; ++ i) {
        count[s[i]] ++;
    }
    for (int i = 1; i < n + 26; ++ i) {
        count[i] += count[i - 1];
    }
    for (int i = 0; i < m; ++ i) {
        rank[i] = count[s[i]] - 1;
    }
    for (int l = 1; l < m; l <<= 1) {
        memcpy(tmpR[0], rank, sizeof(*rank) * m);
        memset(count, 0, sizeof(*count) * (m + 1));
        for (int i = 0; i < m; ++ i) {
            count[tmpR[1][i] = i + l < m ? rank[i + l] + 1 : 0] ++;
        }
        for (int i = 1; i <= m; ++ i) {
            count[i] += count[i - 1];
        }
        for (int i = m - 1; i >= 0; -- i) {
            tmpA[-- count[tmpR[1][i]]] = i;
        }
        memset(count, 0, sizeof(*count) * (m + 1));
        for (int i = 0; i < m; ++ i) {
            count[tmpR[0][i]] ++;
        }
        for (int i = 1; i <= m; ++ i) {
            count[i] += count[i - 1];
        }
        for (int i = m - 1; i >= 0; -- i) {
            array[-- count[tmpR[0][tmpA[i]]]] = tmpA[i];
        }
        rank[array[0]] = 0;
        for (int i = 1; i < m; ++ i) {
            int ai  = array[i];
            int aii = array[i - 1];
            rank[ai] = rank[aii] + (tmpR[0][ai] != tmpR[0][aii] || tmpR[1][ai] != tmpR[1][aii]);
        }
        if (rank[array[m - 1]] == m - 1) {
            break;
        }
    }
    for (int i = 0; i < n; ++ i) {
        auto &ref = range[i];
        ref  = {0, m};
        step = 0;
        for (int j = start[i]; s[j] >= n; ++ j) {
            const auto &&ret = std::equal_range(array + ref.first, array + ref.second, start[i], compare);
            ref = {ret.first - array, ret.second - array};
            step ++;
        }
    }
    memset(result, 0, sizeof(*result) * q);
    std::vector <std::pair <int, std::pair <int, int>>> events;
    for (int i = 0; i < q; ++ i) {
        int l, r, k;
        scanf("%d%d%d", &l, &r, &k);
        k --;
        events.push_back({l - 1, {k, i << 1 | 0}});
        if (r < n) {
            events.push_back({r,     {k, i << 1 | 1}});
        }
    }
    std::sort(events.begin(), events.end());
    memset(count, 0, sizeof(*count) * m);
    for (int i = n - 1, j = (int)events.size() - 1; i >= 0; -- i) {
        for (int k = start[i]; s[k] >= n; ++ k) {
            for (int t = rank[k]; ~t; t -= ~t & t + 1) {
                count[t] ++;
            }
        }
        while (j >= 0 && events[j].first == i) {
            const auto &ref = range[events[j].second.first];
            int id          = events[j].second.second;
            result[id >> 1] += ((id & 1) ? -1 : 1) * (query(ref.first) - query(ref.second));
            j --;
        }
    }
    for (int i = 0; i < q; ++ i) {
        printf("%d\n", result[i]);
    }
    return 0;
}
