// SGU 455 -- Sequence analysis
#include <cstdio>
#include <cstring>
#include <iostream>

typedef long long LL;

const int LIMIT = 2000000;

LL a, b, c;

LL next(LL x) {
    return (a * x + x % b) % c;
}

int main() {
    std::cin >> a >> b >> c;
    int total = 1;
    LL x = next(1);
    LL y = next(next(1));
    while (x != y && total <= LIMIT) {
        x = next(x);
        y = next(next(y));
        total ++;
    }
    if (total > LIMIT) {
        puts("-1");
        return 0;
    }
    x = y = 1;
    for (int i = 0; i < total; ++ i) {
        y = next(y);
    }
    int id = 0;
    while (x != y && id <= LIMIT) {
        id ++;
        x = next(x);
        y = next(y);
    }
    if (id > LIMIT) {
        puts("-1");
        return 0;
    }
    LL value = x;
    id ++;
    x = next(x);
    while (x != value && id <= LIMIT) {
        id ++;
        x = next(x);
    }
    if (id > LIMIT) {
        puts("-1");
        return 0;
    }
    printf("%d\n", id);
    return 0;
}
