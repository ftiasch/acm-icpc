// Ural SU and Orel STU contest
// Problem J -- Airplanes
#include <cstdio>
#include <cstring>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

typedef long long Long;

struct Point {
    Long x, y, z;

    Point(Long x = 0, Long y = 0, Long z = 0): x(x), y(y), z(z) {
//cout << x << " " << y << " " << z << endl;
    }

    bool zero() const {
        return x == 0 && y == 0 && z == 0;
    }
};

Point det(const Point &a, const Point &b) {
    return Point(a.y * b.z - b.y * a.z, 
                 a.z * b.x - b.z * a.x,
                 a.x * b.y - b.x * a.y);
}

Long dot(const Point &a, const Point &b) {
    return a.x * b.x + a.y * b.y + a.z * b.z;
}

istream &operator >>(istream &in, Point &p) {
    return in >> p.x >> p.y >> p.z;
}

const int N = 200;

int n;
Point points[N], axisX, axisY, axisZ;

int main() {
    cin >> n;
    for (int i = 0; i < n; ++ i) {
        cin >> points[i];
    }
    int result = 1;
    for (int i = 0; i < n; ++ i) {
        axisX = points[i];
        for (int j = 0; j < n; ++ j) {
            axisZ = det(axisX, points[j]);
            if (!axisZ.zero()) {
                axisY = det(axisZ, axisX);
                int newResult = 1;
                for (int k = 0; k < n; ++ k) {
                    Long ret = dot(points[k], axisZ);
                    if (ret == 0 && dot(det(points[i], points[k]), axisZ) > 0) {
                        newResult ++;
                    }
                    if (ret > 0) {
                        newResult ++;
                    }
                }
                result = max(result, newResult);
            }
        }
    }
    printf("%d\n", result);
    return 0;
}
