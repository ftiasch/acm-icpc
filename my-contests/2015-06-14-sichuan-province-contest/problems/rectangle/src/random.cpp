#include "testlib.h"

int main(int argc, char* argv[])
{
    registerGen(argc, argv, 1);
    int N = std::atoi(argv[2]);
    int n = rnd.next(1, N);
    int m = rnd.next(1, N);
    int k = rnd.next(0, n + m << 2);
    printf("%d %d %d\n", n, m, k);
    return 0;
}
