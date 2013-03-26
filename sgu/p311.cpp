// SGU 311 -- Ice-cream Tycoon
#include <cctype>
#include <cstdio>
#include <cstring>
#include <utility>
#include <algorithm>

typedef long long LL;

const int N = 1000001;

LL count[N], total_count[N], total_cost[N];

LL read_int() {
    char ch = getchar();
    while (!isdigit(ch)) {
        ch = getchar();
    }
    LL ret = 0;
    while (isdigit(ch)) {
        ret = ret * 10 + ch - '0';
        ch = getchar();
    }
    return ret;
}

void update(int k, LL v) {
    count[k] += v;
    for (int i = k; i < N; i += i & -i) {
        total_count[i] += v;
        total_cost[i] += k * v;
    }
}

LL get_sum(int k) {
    LL ret = 0;
    for (; k >= 1; k -= k & -k) {
        ret += total_cost[k];
    }
    return ret;
}

std::pair <int, LL> find(LL t) { // <
    int k = 0;
    while (1 << k + 1 < N) {
        k ++;
    }
    int now = 0;
    for (; k >= 0; -- k) {
        if (now + (1 << k) < N && total_count[now + (1 << k)] < t) {
            now += 1 << k;
            t -= total_count[now];
        }
    }
    return std::make_pair(now, t);
}

int main() {
    char buffer[7];
    int x;
    while (scanf("%s%d", buffer, &x) == 2) {
        LL c = read_int();
        if (*buffer == 'A') {
            update(c, x);
        } else {
            std::pair <int, LL> ret = find(x);
            LL s = get_sum(ret.first);
            if (ret.first + 1 < N && s + (ret.first + 1) * ret.second <= c) {
                puts("HAPPY");
                while (x) {
                    int id = find(1).first + 1;
                    int d = std::min(count[id], (LL)x);
                    update(id, -d);
                    x -= d;
                }
            } else {
                puts("UNHAPPY");
            }
        }
    }
    return 0;
}
