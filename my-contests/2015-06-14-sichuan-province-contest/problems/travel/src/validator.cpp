#include "testlib.h"

int main()
{
    registerValidation();
    int n = inf.readInt(2, 100000);
    inf.readSpace();
    int m = inf.readInt(0, 500000);
    inf.readSpace();
    inf.readInt(1, 1000000000);
    inf.readSpace();
    inf.readInt(1, 1000000000);
    inf.readEoln();
    for (int i = 0; i < m; ++ i) {
        int a = inf.readInt(1, n);
        inf.readSpace();
        int b = inf.readInt(1, n);
        inf.readEoln();
        ensure(a != b);
    }
    inf.readEof();
    return 0;
}
