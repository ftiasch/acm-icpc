#include <algorithm>
#include <cstdio>
#include <iostream>
#include <utility>
#include <vector>

const int N = 3000;

int a[N];

int main()
{
#ifdef LOCAL_JUDGE
    freopen("C.in", "r", stdin);
#endif
    int n;
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
        a[i] -= i;
    }
    std::vector<std::pair<int, int>> stack;
    for (int i = 0; i < n; ++ i) {
        std::pair<int, int> last(a[i], i);
        while (!stack.empty() && stack.back().first > last.first) {
            std::vector<int> values(a + stack.back().second, a + i + 1);
            std::nth_element(values.begin(), values.begin() + values.size() / 2, values.end());
            last = std::make_pair(values[values.size() / 2], stack.back().second);
            stack.pop_back();
        }
        stack.push_back(last);
    }
    long long result = 0;
    stack.push_back(std::make_pair(0, n));
    for (int i = 0; i < (int)(stack.size()) - 1; ++ i) {
        for (int j = stack[i].second; j < stack[i + 1].second; ++ j) {
            result += std::abs(a[j] - stack[i].first);
        }
    }
    std::cout << result << std::endl;
}
