// Problem F -- Incidental Points
#include <cmath>
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>
using namespace std;

const int N = 1111;
const double EPS = 1e-9;
const double INF = 1e9;

int sgn(double x) {
    return x < -EPS? -1: x > EPS;
}

struct Point {
    int x, y;

    Point(int x = 0, int y = 0): x(x), y(y) {}
};

Point operator -(const Point &a, const Point &b) {
    return Point(a.x - b.x, a.y - b.y);
}

bool operator ==(const Point &a, const Point &b) {
    return a.x == b.x && a.y == b.y;
}

bool operator !=(const Point &a, const Point &b) {
    return !(a == b);
}

int n;
Point points[N];
char buffer[222];

int main() {
    int test_count = 0;
    while (true) {
        n = 0;
        while (true) {
            fgets(buffer, 222, stdin);
            if (buffer[0] == '-' && buffer[1] == '-') {
                break;
            }
            int x, y;
            sscanf(buffer, "%d%d", &x, &y);
            points[n ++] = Point(x, y);
        }
        if (n == 0) {
            break;
        }
        int result = 0;
        for (int i = 0; i < n; ++ i) {
            vector <double> slopes;
            for (int j = 0; j < n; ++ j) {
                if (points[i] != points[j]) {
                    Point p = points[j] - points[i];
                    if (p.x == 0) {
                        slopes.push_back(INF);
                    } else {
                        slopes.push_back((double)p.y / p.x);
                    }
                }
            }
            sort(slopes.begin(), slopes.end());
            int new_result = 0;
            for (int j = 0; j < (int)slopes.size(); ++ j) {
                int k = j;
                while (k < (int)slopes.size() && sgn(slopes[j] - slopes[k]) == 0) {
                    k ++;
                }
                new_result = max(new_result, k - j);
                j = k - 1;
            }
            for (int j = 0; j < n; ++ j) {
                if (points[i] == points[j]) {
                    new_result ++;
                }
            }
            result = max(result, new_result);
        }
        printf("%d. %d\n", ++ test_count, result);
    }
    return 0;
}
