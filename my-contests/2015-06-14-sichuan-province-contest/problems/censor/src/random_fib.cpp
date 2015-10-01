#include "testlib.h"

#include <algorithm>
#include <string>

std::string cut(const std::string &source, int n)
{
    int p = rnd.next(0, (int)source.length() - n);
    return source.substr(p, n);
}

int main(int argc, char* argv[])
{
    registerGen(argc, argv, 1);
    int n = std::atoi(argv[2]);
    int m = std::atoi(argv[3]);
    std::string fib = [&]() {
        std::string p = "a";
        std::string q = "b";
        while ((int)q.size() < n + m) {
            p += q;
            std::swap(p, q);
        }
        return q;
    }();
    puts(cut(fib, n).c_str());
    puts(cut(fib, m).c_str());
    return 0;
}
