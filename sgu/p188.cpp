// SGU 188 -- Factory guard
#include <cstdio>
#include <cstring>
using namespace std;

const int N = 20;

int n, limit, position[N], speed[N], result[N];

int main() {
    scanf("%d%d", &n, &limit);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", position + i);
    }
    for (int i = 0; i < n; ++ i) {
        scanf("%d", speed + i);
    }
    memset(result, 0, sizeof(result));
    for (int i = 0; i < n; ++ i) {
        if (speed[i] > 0) {
            for (int j = 0; j < n; ++ j) {
                if (speed[j] < 0) {
                    int distance = position[i] < position[j]? position[j] - position[i]:
                        1000 - position[i] + position[j];
                    int totalSpeed = speed[i] - speed[j];
                    if (totalSpeed * limit >= distance) {
                        int delta = (limit * totalSpeed - distance) / 1000 + 1;
                        result[i] += delta;
                        result[j] += delta;
                    }
                }
            }
        }
    }
    for (int i = 0; i < n; ++ i) {
        printf("%d%c", result[i], i == n - 1? '\n': ' ');
    }
    return 0;
}
