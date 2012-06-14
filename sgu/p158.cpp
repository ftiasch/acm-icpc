// SGU 158 -- Commuter Train
#include <cstdio>
#include <cstring>
#include <climits>
#include <algorithm>
using namespace std;

const int N = 300;

int length, n, m, passenger[N], door[N];

int main() {
    scanf("%d", &length);
    length *= 2;
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", passenger + i);
        passenger[i] *= 2;
    }
    scanf("%d", &m);
    door[0] = 0;
    for (int i = 1; i < m; ++ i) {
        scanf("%d", door + i);
        door[i] *= 2;
    }
    int maximum = INT_MIN;
    int choice = -1;
    for (int start = 0; start + door[m - 1] <= length; ++ start) {
        int result = 0;
        for (int i = 0, j = 0; i < n; ++ i) {
            while (j + 1 < m && abs(start + door[j] - passenger[i]) >= abs(start + door[j + 1] - passenger[i])) {
                j ++;
            }
            result += abs(start + door[j] - passenger[i]);
        }
        if (result > maximum) {
            choice = start;
            maximum = result;
        }
    }
    printf("%.2f %.2f\n", choice / 2.0, maximum / 2.0);
    return 0;
}
