#include "testlib.h"

#include <algorithm>

int main()
{
    registerValidation();
    int n = inf.readInt(2, 500);
    inf.readSpace();
    int m = inf.readInt(1, n * (n - 1) / 2);
    inf.readEoln();
    for (int i = 0; i < m; ++ i) {
        int a = inf.readInt(1, n);
        inf.readSpace();
        int b = inf.readInt(1, n);
        inf.readEoln();
        ensure(std::min(a, b) <= 30);
    }
    inf.readEof();
    return 0;
}
