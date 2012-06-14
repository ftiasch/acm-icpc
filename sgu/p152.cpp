// SGU 152 -- Making round
#include <cstdio>
#include <cstring>
#include <list>
using namespace std;

const int N = 10001;
const int M = 101;

int n, a[N];
char choice[N][M];
bool can[N][M];

int main() {
    scanf("%d", &n);
    long long sum = 0;
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
        sum += a[i];
    }
    memset(can, 0, sizeof(can));
    can[0][0] = true;
    for (int i = 0; i < n; ++ i) {
        int lower = 0;
        while (lower + 1 <= 100 && sum * (lower + 1) <= 100 * a[i]) {
            lower ++;
        }
        int upper = 100;
        while (upper - 1 >= 0 && sum * (upper - 1) >= 100 * a[i]) {
            upper --;
        }
        for (int j = 0; j <= 100; ++ j) {
            if (can[i][j]) {
                if (j + lower <= 100) {
                    can[i + 1][j + lower] = true;
                    choice[i + 1][j + lower] = lower;
                }
                if (j + upper <= 100) {
                    can[i + 1][j + upper] = true;
                    choice[i + 1][j + upper] = upper;
                }
            }
        }
    }
    if (can[n][100]) {
        list <int> result;
        for (int i = n, j = 100; i > 0; -- i) {
            result.push_front(choice[i][j]);
            j -= choice[i][j];
        }
        for (list <int> :: iterator iter = result.begin(); iter != result.end(); ++ iter) {
            printf("%d ", *iter);
        }
        puts("");
    } else {
        puts("No solution");
    }
    return 0;
}
