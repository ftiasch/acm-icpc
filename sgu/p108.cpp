// SGU 108 -- Self-numbers II  
#include <cstdio>
#include <cstring>
#include <vector>
#include <utility>
#include <algorithm>
using namespace std;

const int N = 10000001;
const int M = 5000;

unsigned int visit[(N >> 4) + 1];
int result[M];
vector <pair <int, int> > queries;

int main() {
    memset(visit, 0, sizeof(visit));
    int n;
    scanf("%d", &n);
    for (int i = 1; i <= n; ++ i) {
        int next = i;
        for (int j = i; j > 0; j /= 10) {
            next += j % 10;
        }
        if (next <= n) {
            visit[next >> 4] |= 1U << (next & 15);
        }
    }
    int m;
    scanf("%d", &m);
    queries.resize(m);
    for (int i = 0; i < m; ++ i) {
        scanf("%d", &queries[i].first);
        queries[i].second = i;
    }
    sort(queries.begin(), queries.end());
    int number_count = 0;
    for (int i = 1, j = 0; i <= n; ++ i) {
        if ((visit[i >> 4] >> (i & 15) & 1) == 0) {
            number_count ++;
            while (j < m && queries[j].first == number_count) {
                result[queries[j].second] = i;
                j ++;
            }
        }
    }
    printf("%d\n", number_count);
    for (int i = 0; i < m; ++ i) {
        printf("%d%c", result[i], i == m - 1? '\n': ' ');
    }
    return 0;
}
