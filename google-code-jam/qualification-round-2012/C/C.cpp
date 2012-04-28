#include <cstdio>
#include <cstring>
#include <iostream>
#include <algorithm>
using namespace std;

bool visit[2222222];

int main() {
    int test_count;
    scanf("%d", &test_count);
    for (int test = 1; test <= test_count; ++ test) {
        int a, b;
        scanf("%d%d", &a, &b);
        int times = 1;
        while (times * 10 <= b) {
            times *= 10;
        }
        memset(visit, 0, sizeof(visit));
        long long result = 0;
        for (int i = a; i <= b; ++ i) {
            if (!visit[i]) {
                int n = 0;
                int j = i;
                do {
                    int r = j % 10;
                    j = (j - r) / 10 + r * times;
                    if (a <= j && j <= b && !visit[j]) {
                        n ++;
                        visit[j] = true;
                    }
                } while (j != i);
                result += n * (n - 1) / 2;
            }
        }
        cout << "Case #" << test << ": " << result << endl;
    }
    return 0;
}
