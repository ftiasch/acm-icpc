#include <algorithm>
#include <iostream>
#include <cstdio>
#include <vector>

typedef long long Long;

const int N = 100000;

int n, a[N];

Long square(Long x) {
    return x * x;
}

Long distance(int i, int j) {
    return square(i - j) + square(a[i] - a[j]);
}

bool by_a(int i, int j) {
    return a[i] < a[j];
}

Long solve(int l, int r) {
    Long min_distance = 1000000000000000000LL;
    if (r - l > 1) {
        int m = l + r >> 1;
        min_distance = std::min(solve(l, m), solve(m, r));
        std::vector <int> indices;
        for (int i = l; i < r; ++ i) {
            if (square(i - m) < min_distance) {
                indices.push_back(i);
            }
        }
        std::sort(indices.begin(), indices.end(), by_a);
        for (int i = 0; i < (int)indices.size(); ++ i) {
            for (int j = i + 1; j < (int)indices.size() && square(a[indices[i]] - a[indices[j]]) < min_distance; ++ j) {
                min_distance = std::min(min_distance, distance(indices[i], indices[j]));
            }
        }
    }
    return min_distance;
}

int main() {
    scanf("%d%*d", &n);
    a[0] = 0;
    for (int i = 1; i < n; ++ i) {
        scanf("%d", a + i);
        a[i] += a[i - 1];
    }
    std::cout << solve(0, n) << std::endl;
    return 0;
}
