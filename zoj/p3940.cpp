#include <algorithm>
#include <cstdio>
#include <map>
#include <vector>
#include <utility>

const int MOD = (int)1e9 + 7;

void update(int &x, int a)
{
    x += a;
    if (x >= MOD) {
        x -= MOD;
    }
}

int main()
{
    int T;
    scanf("%d", &T);
    while (T --) {
        std::map<int, int> count;
        int n, m;
        scanf("%d%d", &n, &m);
        count[m + 1] = 1;
        for (int _ = 0; _ < n; ++ _) {
            int a;
            scanf("%d", &a);
            int new_count = 0;
            while (count.rbegin()->first >= a) {
                auto last = *count.rbegin();
                count.erase(last.first);
                update(count[last.first % a], last.second);
                update(new_count, (long long)(last.first / a) * last.second % MOD);
            }
            if (new_count) {
                update(count[a], new_count);
            }
        }
        int q;
        scanf("%d", &q);
        std::vector<std::pair<int, int>> queries;
        for (int i = 0; i < q; ++ i) {
            int y;
            scanf("%d", &y);
            queries.emplace_back(y, i + 1);
        }
        std::sort(queries.begin(), queries.end());
        int new_count = 0;
        auto iterator = count.rbegin();
        int result = 0;
        for (int i = q - 1; i >= 0; -- i) {
            while (iterator != count.rend() && queries[i].first < iterator->first) {
                update(new_count, iterator->second);
                iterator ++;
            }
            update(result, (long long)new_count * (queries[i].second) % MOD);
        }
        printf("%d\n", result);
    }
}
