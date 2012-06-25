// SGU 186 -- The Chain
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

int main() {
    int n;
    scanf("%d", &n);
    int length[n];
    for (int i = 0; i < n; ++ i) {
        scanf("%d", length + i);
    }
    sort(length, length + n);
    int rest = n - 1;
    int result = 0;
    for (int i = 0; i < n; ++ i) {
        if (rest <= 0) {
            break;
        }
        result += min(rest, length[i]);
        rest -= length[i] + 1;
    }
    printf("%d\n", result);
    return 0;
}
