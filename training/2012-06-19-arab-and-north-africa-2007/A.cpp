// Problem A -- Judging Olympia
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

int score[6];

int main() {
    while (true) {
        int sum = 0;
        for (int i = 0; i < 6; ++ i) {
            scanf("%d", score + i);
            sum += score[i];
        }
        if (sum == 0) {
            break;
        }
        sum -= *min_element(score, score + 6);
        sum -= *max_element(score, score + 6);
        printf("%d", sum / 4);
        sum %= 4;
        if (sum != 0) {
            printf(".");
            while (sum != 0) {
                sum *= 10;
                printf("%d", sum / 4);
                sum %= 4;
            }
        }
        puts("");
    }
    return 0;
}
