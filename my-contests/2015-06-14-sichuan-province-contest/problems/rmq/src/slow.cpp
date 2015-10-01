#include <cstdio>
#include <vector>

int main()
{
    int n, m[2];
    while (scanf("%d%d%d", &n, m, m + 1) == 3) {
        int mm = m[0] + m[1];
        std::vector <int> a(mm), b(mm), c(mm);
        for (int i = 0; i < mm; ++ i) {
            scanf("%d%d%d", &a[i], &b[i], &c[i]);
        }
        std::vector <int> p(n);
        for (int i = 0; i < n; ++ i) {
            p[i] = i + 1;
        }
        bool found = false;
        do {
            bool valid = true;
            for (int i = 0; i < mm && valid; ++ i) {
                int opt = i < m[0] ? INT_MAX : INT_MIN;
                for (int j = a[i] - 1; j < b[i]; ++ j) {
                    if (i < m[0]) {
                        opt = std::min(opt, p[j]);
                    } else {
                        opt = std::max(opt, p[j]);
                    }
                }
                valid &= opt == c[i];
            }
            if (valid) {
                for (int i = 0; i < n; ++ i) {
                    printf("%d%c", p[i], " \n"[i == n - 1]);
                }
                found = true;
            }
        } while (!found && std::next_permutation(p.begin(), p.end()));
        if (!found) {
            puts("-1");
        }
    }
    return 0;
}
