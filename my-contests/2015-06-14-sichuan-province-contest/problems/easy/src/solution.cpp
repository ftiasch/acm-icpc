#include <cstdio>
#include <map>

int main()
{
    std::map <int, int> sqrt;
    for (int i = 0; i * i <= 1000000000; ++ i) {
        sqrt[i * i] = i;
    }
    int n;
    while (scanf("%d", &n) == 1) {
        bool valid = true;
        for (int i = 0; i < n; ++ i) {
            int a;
            scanf("%d", &a);
            valid &= sqrt.count(a);
        }
        puts(valid ? "Yes" : "No");
    }
}
