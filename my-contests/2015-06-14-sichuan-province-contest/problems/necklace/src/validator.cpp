#include "testlib.h"

const int MAX_VALUE = 10000;

int main()
{
    registerValidation();
    int n = inf.readInt(1, 100000);
    inf.readEoln();
    int m = 0;
    for (int i = 0; i < n; ++ i) {
        int v = inf.readInt(0, MAX_VALUE);
        if (i + 1 < n) {
            inf.readSpace();
        } else {
            inf.readEoln();
        }
        m += (v == MAX_VALUE);
    }
    ensure(1 <= m && m <= 10);
    inf.readEof();
    return 0;
}
