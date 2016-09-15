#include <cstdio>
#include <functional>
#include <vector>

using Rect = std::vector<int>;

std::vector<Rect> answer;

int query(int x1, int x2, int y1, int y2)
{
    if (x1 > x2 || y1 > y2) {
        return 0;
    }
    printf("? %d %d %d %d\n", x1, y1, x2, y2);
    fflush(stdout);
    int result = 0;
#ifdef LOCAL_JUDGE
    for (auto&& rect : answer) {
        result += x1 <= rect.at(0) && rect.at(2) <= x2 && y1 <= rect.at(1) && rect.at(3) <= y2;
    }
#else
    scanf("%d", &result);
#endif
    return result;
}

int bsearch(int low, int high, std::function<bool(int)> check)
{
    while (low < high) {
        int middle = low + high >> 1;
        if (check(middle)) {
            high = middle;
        } else {
            low = middle + 1;
        }
    }
    return high;
}

Rect find_one(int x1, int x2, int y1, int y2)
{
    x2 = bsearch(x1, x2, [&](int x2) { return query(x1, x2, y1, y2) >= 1; });
    x1 = bsearch(x1, x2, [&](int x1) { return query(x1 + 1, x2, y1, y2) < 1; });
    y2 = bsearch(y1, y2, [&](int y2) { return query(x1, x2, y1, y2) >= 1; });
    y1 = bsearch(y1, y2, [&](int y1) { return query(x1, x2, y1 + 1, y2) < 1; });
    return std::vector<int>({x1, y1, x2, y2});
}

int main()
{
    int n;
#ifdef LOCAL_JUDGE
    n = 5;
    answer.push_back({2, 2, 2, 2});
    answer.push_back({3, 4, 3, 5});
#else
    scanf("%d", &n);
#endif
    int x2 = bsearch(1, n, [&](int x2) { return query(1, x2, 1, n) >= 1; });
    std::vector<Rect> rects;
    if (query(x2 + 1, n, 1, n) == 1) {
        rects.push_back(find_one(1, x2, 1, n));
        rects.push_back(find_one(x2 + 1, n, 1, n));
    } else {
        int y2 = bsearch(1, n, [&](int y2) { return query(1, n, 1, y2) >= 1; });
        rects.push_back(find_one(1, n, 1, y2));
        rects.push_back(find_one(1, n, y2 + 1, n));
    }
    printf("!");
    for (auto&& rect : rects) {
        for (int i = 0; i < 4; ++ i) {
            printf(" %d", rect.at(i));
        }
    }
    puts("");
}
