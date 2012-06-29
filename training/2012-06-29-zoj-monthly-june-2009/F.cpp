// Problem F -- DFA 
#include <cstdio>
#include <cstring>
using namespace std;

char buffer[111];

int main() {
    int testCount;
    scanf("%d", &testCount);
    while (testCount > 0) {
        testCount --;
        int n;
        scanf("%d", &n);
        for (int i = 0; i < n; ++ i) {
            scanf("%s", buffer);
        }
        scanf("%s", buffer);
        n = strlen(buffer);
        printf("%d\n", n + 2);
        for (int i = 0; i < n; ++ i) {
            int children[3];
            for (int j = 0; j < 3; ++ j) {
                children[j] = n + 1;
            }
            children[buffer[i] - 'a'] = i + 1;
            printf("%d %d %d %d 1\n", i, children[0], children[1], children[2]);
        }
        printf("%d %d %d %d 0\n", n, n + 1, n + 1, n + 1);
        printf("%d %d %d %d 1\n", n + 1, n + 1, n + 1, n + 1);
        puts("");
    }
    return 0;
}
