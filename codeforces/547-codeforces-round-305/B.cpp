#include <cstdio>
#include <climits>
#include <vector>
#include <algorithm>

const int N = 2e5;

#define REVERSE(v) std::reverse((v), (v) + n)

int n, a[N], left[N], right[N], result[N];

void scan(int* left)
{
    std::vector <int> stack;
    stack.push_back(-1);
    for (int i = 0; i < n; ++ i) {
        while (stack.back() != -1 && a[stack.back()] >= a[i]) {
            stack.pop_back();
        }
        left[i] = i - stack.back();
        stack.push_back(i);
    }
}

int main()
{
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    scan(left);
    REVERSE(a);
    scan(right);
    REVERSE(a);
    REVERSE(right);
    std::fill(result, result + n, INT_MIN);
    for (int i = 0; i < n; ++ i) {
        int l = left[i] + right[i] - 2;
        result[l] = std::max(result[l], a[i]);
    }
    for (int i = n - 2; i >= 0; -- i) {
        result[i] = std::max(result[i], result[i + 1]);
    }
    for (int i = 0; i < n; ++ i) {
        printf("%d%c", result[i], " \n"[i == n - 1]);
    }
    return 0;
}
