#include "testlib.h"

const int MAX_VALUE = 10000;

int main(int argc, char* argv[])
{
    registerGen(argc, argv, 1);
    int n = std::atoi(argv[2]);
    int m = std::atoi(argv[3]);
    int k = std::atoi(argv[4]);
    std::vector <int> a;
    for (int i = 0; i < n; ++ i) {
        a.push_back(rnd.next(0, m - 1));
    }
    for (int i = 0; i < k; ++ i) {
        a[rnd.next(0, n - 1)] = MAX_VALUE;
    }
    printf("%d\n", n);
    for (int i = 0; i < n; ++ i) {
        printf("%d%c", a[i], " \n"[i == n - 1]);
    }
    return 0;
}
