#include "testlib.h"

int main()
{
    registerValidation();
    int n = inf.readInt(2, 100000);
    inf.readEoln();
    for (int i = 0; i < n; ++ i) {
        inf.readInt(0, 1000000000);
        if (i + 1 < n) {
            inf.readSpace();
        } else {
            inf.readEoln();
        }
    }
    inf.readEof();
    return 0;
}
