#include "testlib.h"

#include <vector>

int main(int argc, char* argv[])
{
    registerGen(argc, argv, 1);
    int n = std::atoi(argv[2]);
    int mm = std::atoi(argv[3]);
    std::vector <int> p(n);
    for (int i = 0; i < n; ++ i) {
        p[i] = i + 1;
    }
    shuffle(p.begin(), p.end());
    int m0 = rnd.next(0, mm);
    int m1 = mm - m0;
    printf("%d %d %d\n", n, m0, m1);
    int noise = rnd.next(mm);
    for (int i = 0; i < mm; ++ i) {
        int a = rnd.next(1, n);
        int b = rnd.next(1, n);
        if (a > b) {
            std::swap(a, b);
        }
        int opt = i < m0 ? INT_MAX : INT_MIN;
        for (int j = a - 1; j < b; ++ j) {
            if (i < m0) {
                opt = std::min(opt, p[j]);
            } else {
                opt = std::max(opt, p[j]);
            }
        }
        int c = i == noise ? rnd.next(1, n) : opt;
        printf("%d %d %d\n", a, b, c);
    }
    return 0;
}
