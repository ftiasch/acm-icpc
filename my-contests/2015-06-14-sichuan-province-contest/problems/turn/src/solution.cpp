#include <cstdio>
#include <cstring>
#include <climits>
#include <set>
#include <utility>
#include <algorithm>

const int N = 1000;
const int DELTA_X[4] = {-1, 0, 1,  0};
const int DELTA_Y[4] = { 0, 1, 0, -1};

int n, x[N], y[N];

int signum(int x) {
    return x < 0 ? -1 : x > 0;
}

int abs(int x) {
    return x < 0 ? -x : x;
}

int main()
{
    while (scanf("%d", &n) == 1) {
        for (int i = 0; i < n; ++ i) {
            scanf("%d%d", x + i, y + i);
        }
        int result = 0;
        std::set <int> state;
        int a = 0;
        int b = 0;
        int d = 2;
        while (true) {
            std::pair <int, int> best(INT_MAX, -1);
            for (int i = 0; i < n; ++ i) {
                if (signum(x[i] - a) == DELTA_X[d] && signum(y[i] - b) == DELTA_Y[d]) {
                    best = std::min(best, std::make_pair(abs(x[i] - a) + abs(y[i] - b), i));
                }
            }
            if (best.first == INT_MAX) {
                break;
            }
            int i = best.second;
            int s = i << 2 | d;
            if (state.count(s)) {
                result = -1;
                break;
            }
            state.insert(s);
            result ++;
            a = x[i] - DELTA_X[d];
            b = y[i] - DELTA_Y[d];
            d = d + 1 & 3;
        }
        printf("%d\n", result);
    }
    return 0;
}
