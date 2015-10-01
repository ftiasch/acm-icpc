#include "testlib.h"

int main()
{
    registerValidation();
    int n = inf.readInt(1, 50);
    inf.readSpace();
    int m[2];
    m[0] = inf.readInt(0, 50);
    inf.readSpace();
    m[1] = inf.readInt(0, 50);
    inf.readEoln();
    ensure(m[0] + m[1] <= 50);
    for (int i = 0; i < 2; ++ i) {
        for (int j = 1; j <= m[i]; ++ j) {
            int l = inf.readInt(1, n);
            inf.readSpace();
            int r = inf.readInt(1, n);
            inf.readSpace();
            inf.readInt(1, n);
            inf.readEoln();
            ensure(l <= r);
        }
    }
    inf.readEof();
    return 0;
}
