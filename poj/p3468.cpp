#include <cstring>
#include <string>
#include <iostream>
#include <algorithm>

const int N = 100000 + 1;

int n, m;

int delta[N << 1];
long long sum[N << 1];

int get_id(int l, int r) {
    return l + r | l != r;
}

void update(int l, int r, int a, int b, int c) {
    if (b < l || r < a) {
        return;
    }
    int id = get_id(l, r);
    sum[id] += (long long)c * (std::min(r, b) - std::max(l, a) + 1);
    if (a <= l && r <= b) {
        delta[id] += c;
    } else {
        int m = l + r >> 1;
        update(l, m, a, b, c);
        update(m + 1, r, a, b, c);
    }
}

long long query(int l, int r, int a, int b) {
    if (b < l || r < a) {
        return 0LL;
    }
    int id = get_id(l, r);
    if (a <= l && r <= b) {
        return sum[id];
    }
    int m = l + r >> 1;
    long long ret = (long long)delta[id] * (std::min(r, b) - std::max(l, a) + 1);
    ret += query(l, m, a, b);
    return ret += query(m + 1, r, a, b);
}

int main() {
    std::ios::sync_with_stdio(false);
    std::cin >> n >> m;
    memset(delta, 0, sizeof(delta));
    memset(sum, 0, sizeof(sum));
    for (int i = 1, a; i <= n; ++ i) {
        std::cin >> a;
        update(1, n, i, i, a);
    }
    while (m --) {
        std::string type;
        int a, b, c;
        std::cin >> type >> a >> b;
        if (type[0] == 'Q') {
            std::cout << query(1, n, a, b) << std::endl;
        } else {
            std::cin >> c;
            update(1, n, a, b, c);
        }
    }
    return 0;
}
