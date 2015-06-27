#include <cstdio>
#include <climits>
#include <set>

const int N = 200000 + 1;

typedef std::pair <int, int> Data;

int x[N], y[N], t[N];

int main()
{
    int n, q;
    scanf("%d%d", &n, &q);
    x[q] = y[q] = 0;
    std::set <Data> set;
    set.insert({0, q});
    set.insert({n + 1, q});
    for (int i = 0; i < q; ++ i) {
        char buffer[2];
        scanf("%d%d%s", x + i, y + i, buffer);
        t[i] = *buffer == 'U';
        std::set <Data>::iterator iterator;
        if (t[i]) {
            iterator = set.lower_bound({x[i], INT_MIN});
        } else {
            iterator = set.upper_bound({x[i], INT_MAX});
            iterator --;
        }
        if (x[i] == iterator->first) {
            puts("0");
        } else {
            int j = iterator->second;
            set.insert({x[i], i});
            if (t[i]) {
                printf("%d\n", y[i] - y[j]);
                y[i] = y[j];
            } else {
                printf("%d\n", x[i] - x[j]);
                x[i] = x[j];
            }
        }
    }
    return 0;
}
