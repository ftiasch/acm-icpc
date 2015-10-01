#include "testlib.h"

int main()
{
    registerValidation();
    inf.readToken("[a-z]{1,5000000}");
    inf.readEoln();
    inf.readToken("[a-z]{1,5000000}");
    inf.readEoln();
    inf.readEof();
    return 0;
}
