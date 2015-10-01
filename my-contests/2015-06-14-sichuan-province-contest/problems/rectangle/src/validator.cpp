#include "testlib.h"

int main()
{
    registerValidation();
    inf.readInt(1, 50000);
    inf.readSpace();
    inf.readInt(1, 50000);
    inf.readSpace();
    inf.readInt(0, 1000000000);
    inf.readEoln();
    inf.readEof();
    return 0;
}
