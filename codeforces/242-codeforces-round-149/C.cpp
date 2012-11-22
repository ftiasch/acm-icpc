// Codeforces Round #149
// Problem C -- King's Path
#include <cstdio>
#include <cstring>
#include <map>
#include <queue>
using namespace std;

const int SIZE = 1000000002;

int n, x_0, y_0, x_1, y_1;
map <long long, int> available;

long long get_id(int x, int y) {
    return (long long)x * SIZE + y;
}

const int DELTA[8][2] = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

int main() {
    scanf("%d%d%d%d%d", &x_0, &y_0, &x_1, &y_1, &n);
    for (int i = 0; i < n; ++ i) {
        int r, a, b;
        scanf("%d%d%d", &r, &a, &b);
        for (int c = a; c <= b; ++ c) {
            available[get_id(r, c)] = -1;
        }
    }
    queue <pair <int, int> > points;
    available[get_id(x_0, y_0)] = 0;
    points.push(make_pair(x_0, y_0));
    while (!points.empty()) {
        int x = points.front().first;
        int y = points.front().second;
        points.pop();
        for (int k = 0; k < 8; ++ k) {
            int new_x = x + DELTA[k][0];
            int new_y = y + DELTA[k][1];
            long long id = get_id(new_x, new_y);
            if (available[id] == -1) {
                available[id] = available[get_id(x, y)] + 1;
                points.push(make_pair(new_x, new_y));
            }
        }
    }
    printf("%d\n", available[get_id(x_1, y_1)]);
    return 0;
}
