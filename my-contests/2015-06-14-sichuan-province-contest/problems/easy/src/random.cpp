#include "testlib.h"

#include <vector>

int main(int argc, char* argv[])
{
    registerGen(argc, argv, 1);
    int n = std::atoi(argv[2]);
    int m = std::atoi(argv[3]);
    std::vector <int> a;
    for (int i = 0; i * i <= m; ++ i) {
        a.push_back(i * i);
    }
    printf("%d\n", n);
    int t = rnd.next(0, 1);
    for (int i = 0; i < n; ++ i) {
        int x = t ? a[rnd.next(0, (int)a.size() - 1)] : rnd.next(0, m);
        printf("%d%c", x, " \n"[i == n - 1]);
    }
    return 0;
}
