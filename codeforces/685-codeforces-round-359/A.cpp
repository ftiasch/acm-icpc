#include <algorithm>
#include <cstdio>
#include <string>

std::string show_(int n)
{
    if (!n) {
        return "0";
    }
    std::string result;
    for (;n; n /= 7) {
        result += ('0' + n % 7);
    }
    return result;
}

std::string show(int n, int x)
{
    int l = show_(n - 1).size();
    auto s = show_(x);
    while (s.size() < l) {
        s += '0';
    }
    return s;
}

int main()
{
    int n, m;
    scanf("%d%d", &n, &m);
    if (show(n, 0).size() + show(m, 0).size() > 7) {
        printf("%d\n", 0);
        return 0;
    }
    int result = 0;
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < m; ++ j) {
            auto s = show(n, i) + show(m, j);
            std::sort(s.begin(), s.end());
            result += std::unique(s.begin(), s.end()) == s.end();
        }
    }
    printf("%d\n", result);
}
