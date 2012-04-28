// Round 1A 2012
// Problem B -- Kingdom Rush
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

const int N = 1111;

int n, need[N][2], rating[N];

int main() {
    int test_count;
    scanf("%d", &test_count);
    for (int test = 1; test <= test_count; ++ test) {
        scanf("%d", &n);
        for (int i = 0; i < n; ++ i) {
            scanf("%d%d", &need[i][0], &need[i][1]);
        }
        memset(rating, 0, sizeof(rating));
        int result = 0;
        int star_count = 0;
        while (star_count < (n << 1)) {
            bool found = false;
            for (int i = 0; i < n; ++ i) {
                if (rating[i] < 2 && need[i][1] <= star_count) {
                    result ++;
                    star_count += 2 - rating[i];
                    rating[i] = 2;
                    found = true;
                    break;
                }
            }
            if (found) {
                continue;
            }
            int candidate = -1;
            int maximal = INT_MIN;
            for (int i = 0; i < n; ++ i) {
                if (rating[i] < 1 && need[i][0] <= star_count) {
                    if (need[i][1] > maximal) {
                        candidate = i;
                        maximal = need[i][1];
                    }
                }
            }
            if (candidate == -1) {
                result = -1;
                break;
            }
            result ++;
            rating[candidate] ++;
            star_count ++;
        }
        if (result == -1) {
            printf("Case #%d: Too Bad\n", test);
        } else {
            printf("Case #%d: %d\n", test, result);
        }
    }
    return 0;
}
