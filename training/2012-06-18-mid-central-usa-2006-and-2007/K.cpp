// Problem K -- Guard
#include <cmath>
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>
using namespace std;

typedef long long LL;

const double EPS = 1e-9;

const LL INF = 2000000000LL;

int sgn(double x) {
    return x < -EPS? -1: x > EPS;
}

struct Point {
    int x, y;

    Point(int x = 0, int y = 0): x(x), y(y) {}

    double norm() const {
        return sqrt((double)(x * x + y * y));
    }
};

Point operator -(const Point &a, const Point &b) {
    return Point(a.x - b.x, a.y - b.y);
}

const int N = 11;

int point_count, corridor_count, guard_count, value[N], all_mask[N], minimum[1 << N];
bool available[1 << N];
double coordinate[N][N];
char label[N];
Point point[N];

int find_id(char c) {
    for (int i = 0; i < point_count; ++ i) {
        if (label[i] == c) {
            return i;
        }
    }
    return -1;
}

int solve(int limit) {
    vector <int> choice;
    for (int i = 0; i < corridor_count; ++ i) {
        for (int sub_mask = all_mask[i]; sub_mask > 0; sub_mask = (sub_mask - 1) & all_mask[i]) {
            double lower = -1e9;
            double upper = 1e9;
            for (int j = 0; j < point_count; ++ j) {
                if ((sub_mask >> j & 1) == 1 && value[j] != 0) {
                    lower = max(lower, coordinate[i][j] - limit / 1000.0 / value[j]);
                    upper = min(upper, coordinate[i][j] + limit / 1000.0 / value[j]);
                }
            }
            if (sgn(lower - upper) <= 0) {
                choice.push_back(sub_mask);
            }
        }
    }
    for (int i = 0; i < point_count; ++ i) {
        int mask = 0;
        for (int j = 0; j < corridor_count; ++ j) {
            if (coordinate[j][i] >= 0) {
                for (int k = 0; k < point_count; ++ k) {
                    if (coordinate[j][k] >= 0) {
                        if ((point[k] - point[i]).norm() * value[k] <= limit / 1000.0) {
                            mask |= 1 << k;
                        }
                    }
                }
            }
        }
        choice.push_back(mask);
    }
//for (vector <int> :: iterator iter = choice.begin(); iter != choice.end(); ++ iter) {
//    for (int i = 0; i < point_count; ++ i) {
//        if (*iter >> i & 1) {
//            printf("%c", i + 'A');
//        }
//    }
//    puts("");
//}
    minimum[0] = 0;
    for (int mask = 1; mask < (1 << point_count); ++ mask) {
        minimum[mask] = 10000;
        for (vector <int> :: iterator iter = choice.begin(); iter != choice.end(); ++ iter) {
            if ((*iter & mask) > 0) {
                minimum[mask] = min(minimum[mask], minimum[mask & ~*iter] + 1);
            }
        }
    }
    return minimum[(1 << point_count) - 1];
}

int main() {
    while (scanf("%d", &point_count) == 1 && point_count != 0) {
        scanf("%d%d", &corridor_count, &guard_count);
        for (int i = 0; i < point_count; ++ i) {
            char buffer[22];
            scanf("%s%d%d%d", buffer, &point[i].x, &point[i].y, value + i);
            label[i] = *buffer;
        }
        for (int i = 0; i < corridor_count; ++ i) {
            char buffer[22];
            scanf("%s", buffer);
            int length = strlen(buffer);
            int first = find_id(buffer[0]);
            all_mask[i] = 0;
            for (int j = 0; j < length; ++ j) {
                all_mask[i] |= 1 << find_id(buffer[j]);
            }
            for (int j = 0; j < point_count; ++ j) {
                coordinate[i][j] = -1;
                if (all_mask[i] >> j & 1) {
                    coordinate[i][j] = (point[first] - point[j]).norm();
                }
            }
        }
        LL lower = 0;
        LL upper = INF;
        while (lower < upper) {
            LL mider = (lower + upper) >> 1LL;
            if (solve(mider) <= guard_count) {
                upper = mider;
            } else {
                lower = mider + 1;
            }
        }
        if (upper == INF) {
            puts("too few guards");
        } else {
            printf("%.2f\n", upper / 1000.0);
        }
    }
    return 0;
}
