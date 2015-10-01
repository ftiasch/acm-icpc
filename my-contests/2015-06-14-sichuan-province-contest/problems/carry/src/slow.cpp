#include <cstdio>
#include <iostream>

const int N = 100000;

int a[N];

int count(int x, int y)
{
    int result = 0;
    int carry = 0;
    while (x || y) {
        carry += x % 10 + y % 10;
        carry /= 10;
        x /= 10;
        y /= 10;
        result += !!carry;
    }
    return result;
}

int main()
{
    int n;
    while (scanf("%d", &n) == 1) {
        for (int i = 0; i < n; ++ i) {
            scanf("%d", a + i);
        }
        long long result = 0;
        for (int i = 0; i < n; ++ i) {
            for (int j = i + 1; j < n; ++ j) {
                result += count(a[i], a[j]);
            }
        }
        std::cout << result << std::endl;
    }
    return 0;
}
