// SGU 375 -- Amplifiers
#include <cstdio>
#include <cstring>
#include <vector>
using namespace std;

int main() {
    int n;
    scanf("%d", &n);
    if (n % 2 == 0) {
        puts("No solution");
    } else {
        vector <int> ways;
        while (n != 1) {
            if ((n + 1) % 4 == 2) {
                ways.push_back(1);
                n = (n + 1) / 2;
            } else {
                ways.push_back(2);
                n = (n - 1) / 2;
            }
        }
        printf("%d\n", (int)ways.size());
        for (int i = (int)ways.size() - 1; i >= 0; -- i) {
            printf("%d%c", ways[i], i == 0? '\n': ' ');
        }
    }
    return 0;
}
