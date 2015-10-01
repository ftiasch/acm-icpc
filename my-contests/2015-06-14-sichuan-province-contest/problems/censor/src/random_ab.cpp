#include "testlib.h"

#include <algorithm>

int main(int argc, char* argv[])
{
    registerGen(argc, argv, 1);
    int n = std::atoi(argv[2]);
    int m = std::atoi(argv[3]);
    for (int i = 0; i < n; ++ i) {
        putchar('a');
    }
    puts("");
    for (int i = 0; i < m; ++ i) {
        putchar("ba"[std::min(rnd.next(0, m / n), 1)]);
    }
    puts("");
    return 0;
}
