// 1855. Trade Guilds of Erathia
#include <cstdio>
#include <cstring>
#include <iostream>

typedef long long LL;

struct Data {
    LL sum[3];

    Data() {
        memset(sum, 0, sizeof(sum));
    }
};

Data merge(const Data &a, const Data &b) {
    Data c;
    for (int i = 0; i < 3; ++ i) {
        c.sum[i] = a.sum[i] + b.sum[i];
    }
    return c;
}

const int N = 100000;

LL delta[N << 1], power_sum[3][N];
Data data[N << 1];

int get_id(int l, int r) {
    return l + r | (l != r);
}

void change_node(int l, int r, int d) {
    int id = get_id(l, r);
    delta[id] += d;
    for (int i = 0; i < 3; ++ i) {
        data[id].sum[i] += (LL)d * (power_sum[i][r] - power_sum[i][l - 1]);
    }
}

void push_down(int l, int r) {
    LL &d = delta[get_id(l, r)];
    if (d != 0) {
        int m = l + r >> 1;
        change_node(l, m, d);
        change_node(m + 1, r, d);
        d = 0;
    }
}

void update(int l, int r) {
    int m = l + r >> 1;
    data[get_id(l, r)] = merge(data[get_id(l, m)], data[get_id(m + 1, r)]);
}

void change(int l, int r, int a, int b, int d) {
    if (b < l || r < a) {
        return;
    }
    if (a <= l && r <= b) {
        change_node(l, r, d);
    } else {
        push_down(l, r);
        int m = l + r >> 1;
        change(l, m, a, b, d);
        change(m + 1, r, a, b, d);
        update(l, r);
    }
}

Data establish(int l, int r, int a, int b) {
    if (b < l || r < a) {
        return Data();
    }
    if (a <= l && r <= b) {
        return data[get_id(l, r)];
    }
    push_down(l, r);
    int m = l + r >> 1;
    return merge(establish(l, m, a, b), 
                 establish(m + 1, r, a, b));
}

int main() {
    memset(delta, 0, sizeof(delta));
    int n, m;
    scanf("%d%d", &n, &m);
    memset(power_sum, 0, sizeof(power_sum));
    for (int i = 1; i < n; ++ i) {
        power_sum[0][i] = 1;
        for (int t = 1; t < 3; ++ t) {
            power_sum[t][i] = power_sum[t - 1][i] * i;
        }
    }
    for (int t = 0; t < 3; ++ t) {
        for (int i = 1; i < n; ++ i) {
            power_sum[t][i] += power_sum[t][i - 1];
        }
    }
    while (m --) {
        char buffer[10];
        scanf("%s", buffer);
        int a, b;
        scanf("%d%d", &a, &b);
        b --;
        if (*buffer == 'c') {
            int d;
            scanf("%d", &d);
            change(1, n - 1, a, b, d);
        } else {
            Data ret = establish(1, n - 1, a, b);
            // sum_{a <= i <= b}{(i - a + 1) * (b - i + 1) * x_i}
            int m = b - a + 1;
            double answer = (double)(ret.sum[0] * (b + 1) * (1 - a) + ret.sum[1] * (a + b) - ret.sum[2]) / ((LL)m * (m + 1) / 2);
            printf("%.10f\n", answer);
        }
    }
    return 0;
}
