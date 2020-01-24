#include <tuple>

int main()
{
    auto count = 124;
    auto mod = 1;
    while (count) {
        mod += 2;
        auto a = 1;
        auto b = 1;
        auto c = 1;
        bool valid = true;
        do {
            std::swap(a, b);
            std::swap(b, c);
            c += a;
            c += b;
            while (c >= mod) {
                c -= mod;
            }
            valid &= !!c;
        } while ((a != 1 || b != 1 || c != 1) && valid);
        if (valid) {
            count --;
        }
    }
    printf("%d\n", mod);
}
