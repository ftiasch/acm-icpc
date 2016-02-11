#include <algorithm>
#include <cstdio>
#include <climits>
#include <iostream>
#include <utility>
#include <vector>

#define ALL(v) (v).begin(), (v).end()
#define X first
#define Y second

typedef long long Long;
typedef std::pair<int, int> Point;

const Long MAX = 4e16;

Long square(Long x)
{
    return x * x;
}

bool check(int n, std::vector<std::pair<int, int>>& points, Long threshold)
{
    for (int _ = 0; _ < 2; ++ _) {
        std::vector<int> prefix_y_min(n + 1), prefix_y_max(n + 1);
        std::vector<Long> prefix_square_max(n + 1);
        prefix_y_min[0] = INT_MAX;
        prefix_y_max[0] = INT_MIN;
        prefix_square_max[0] = -MAX;
        for (int i = 0; i < n; ++ i) {
            prefix_y_min[i + 1] = std::min(prefix_y_min[i], points[i].Y);
            prefix_y_max[i + 1] = std::max(prefix_y_max[i], points[i].Y);
            prefix_square_max[i + 1] = std::max(prefix_square_max[i], square(points[i].Y));
        }
        int suffix_y_min = INT_MAX;
        int suffix_y_max = INT_MIN;
        Long suffix_square_max = -MAX;
        for (int i_0 = 0, i_1 = 0, i_2 = n - 1, i_3 = n - 1, j = n - 1; j >= 0 && points[j].X >= 0; -- j) {
            Long x_j = points[j].X;
            while (std::abs(points[i_0].X) > points[j].X) {
                i_0 ++;
            }
            while (i_1 < j && square(x_j) + prefix_square_max[i_1 + 1] <= threshold) {
                i_1 ++;
            }
            while (i_2 && square(points[j].X - points[i_2 - 1].X) <= threshold) {
                i_2 --;
            }
            while (~i_3 && square((long long)std::max(suffix_y_max, prefix_y_max[i_3]) - std::min(suffix_y_min, prefix_y_min[i_3])) > threshold) {
                i_3 --;
            }
            if (!~i_3) {
                break;
            }
            if (square(x_j) + suffix_square_max <= threshold && std::max(i_0, i_2) <= std::min(i_1, i_3)) {
                return true;
            }
            suffix_y_min = std::min(suffix_y_min, points[j].Y);
            suffix_y_max = std::max(suffix_y_max, points[j].Y);
            suffix_square_max = std::max(suffix_square_max, square(points[j].Y));
        }
        std::reverse(ALL(points));
        for (auto& p : points) {
            p.X *= -1;
        }
    }
    return false;
}

int main()
{
    int n;
    scanf("%d", &n);
    std::vector<std::pair<int, int>> points(n);
    int y_min = INT_MAX;
    int y_max = INT_MIN;
    for (int i = 0; i < n; ++ i) {
        int x, y;
        scanf("%d%d", &x, &y);
        y_min = std::min(y_min, y);
        y_max = std::max(y_max, y);
        points[i] = {x, y};
    }
    std::sort(ALL(points));
    Long low = 0;
    Long high = square(y_max - y_min);
    while (low < high) {
        Long middle = low + high >> 1;
        if (check(n, points, middle)) {
            high = middle;
        } else {
            low = middle + 1;
        }
    }
    std::cout << high << std::endl;
    return 0;
}
