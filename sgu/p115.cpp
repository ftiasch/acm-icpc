#include <cstdio>
#include <cstring>
using namespace std;

int result[101][101];

const int MONTH_DAY[12] = {31, 30, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

int get_day(int month) {
    if (month == 2) {
        return 28;
    }
    return MONTH_DAY[month - 1];
}

int main() {
    memset(result, -1, sizeof(result));
    int current_day = 0;
    for (int month = 1; month <= 12; ++ month) {
        for (int day = 1; day <= get_day(month); ++ day) {
            result[month][day] = current_day;
            current_day = (current_day + 1) % 7;
        }
    }
    int d, m;
    scanf("%d%d", &d, &m);
    if (result[m][d] == -1) {
        puts("Impossible");
    } else {
        printf("%d\n", result[m][d] + 1);
    }
    return 0;
}
