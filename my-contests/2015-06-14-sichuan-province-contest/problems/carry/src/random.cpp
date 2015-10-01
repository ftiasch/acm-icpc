#include "testlib.h"

int main(int argc, char* argv[])
{
    registerGen(argc, argv, 1);
    int n = std::atoi(argv[2]);
    int m = std::atoi(argv[3]);
    printf("%d\n", n);
    for (int i = 0; i < n; ++ i) {
        printf("%d%c", rnd.next(0, m), " \n"[i == n - 1]);
    }
    return 0;
}
