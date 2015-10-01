#include "testlib.h"

#include <set>
#include <utility>

int main()
{
    registerValidation();
    int n = inf.readInt(0, 1000);
    inf.readEoln();
    std::set <std::pair <int, int>> blocks;
    for (int i = 0; i < n; ++ i) {
        int x = inf.readInt(-1000000000, 1000000000);
        inf.readSpace();
        int y = inf.readInt(-1000000000, 1000000000);
        inf.readEoln();
        ensure(x || y);
        blocks.insert({x, y});
    }
    ensure((int)blocks.size() == n);
    inf.readEof();
    return 0;
}
