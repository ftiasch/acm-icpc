// Codeforces Beta Round #16
// Problem D -- Logging
#include <cstdio>
#include <cstring>

const int N = 100;

int n, times[N];
char buffer[111];

int main() {
    scanf("%d", &n);
    gets(buffer);
    for (int i = 0; i < n; ++ i) {
        gets(buffer);
        buffer[3] = ' ';
        int h, m;
        sscanf(buffer + 1, "%d%d", &h, &m);
        if (h == 12) {
            h = 0;
        }
        if (buffer[7] == 'p') {
            h += 12;
        }
        times[i] = h * 60 + m;
    }
    int answer = 1;
    int last = -1;
    int count = 0;
    for (int i = 0; i < n; ++ i) {
        if (last < times[i] || (last == times[i] && count < 10)) {
            if (last == times[i]) {
                count ++;
            } else {
                count = 1;
            }
        } else {
            answer ++;
            count = 1;
        }
        last = times[i];
    }
    printf("%d\n", answer);
    return 0;
}
