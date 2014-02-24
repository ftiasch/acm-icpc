#include <algorithm>
#include <cstdio>
#include <utility>
#include <vector>

int s(int n)
{
    std::vector <std::pair <int, int> > points;
    for (int i = 0, two = 1 % n, three = 1 % n; i <= 2 * n; ++ i) {
        points.push_back(std::make_pair(two, three));
        (two *= 2) %= n;
        (three *= 3) %= n;
    }
    std::sort(points.begin(), points.end());
    points.erase(std::unique(points.begin(), points.end()), points.end());
    std::vector <int> maximum(points.size() + 1, n);
    maximum[0] = 0;
    int result = 0;
    for (auto point : points) {
        int y = point.second;
        int length = std::upper_bound(maximum.begin(), maximum.end(), y) - maximum.begin();
        result = std::max(result, length);
        maximum[length] = y;
    }
    return result;
}

int main()
{
    printf("%d\n", s(22));
    printf("%d\n", s(123));
    printf("%d\n", s(10000));
    int result = 0;
    for (int k = 1; k <= 30; ++ k) {
        result += s(k * k * k * k * k);
    }
    printf("%d\n", result);
}
