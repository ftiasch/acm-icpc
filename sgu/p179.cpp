// SGU 179 -- Brackets light
#include <cstdio>
#include <cstring>
using namespace std;

const int N = 11111;

char text[N], bracketCount[N];

int main() {
    scanf("%s", text);
    int n = strlen(text);
    bracketCount[0] = 0;
    for (int i = 0; i < n; ++ i) {
        bracketCount[i + 1] = bracketCount[i];
        if (text[i] == '(') {
            bracketCount[i + 1] ++;
        } else {
            bracketCount[i + 1] --;
        }
    }
    for (int i = n - 1; i >= 0; -- i) {
        if (text[i] == '(' && bracketCount[i] > 0) {
            for (int j = 0; j < i; ++ j) {
                printf("%c", text[j]);
            }
            putchar(')');
            int space = n - 1 - i;
            int leftBracket = (space - (bracketCount[i] - 1)) >> 1;
            int rightBracket = space - leftBracket;
            for (int j = 0; j < leftBracket; ++ j) {
                putchar('(');
            }
            for (int j = 0; j < rightBracket; ++ j) {
                putchar(')');
            }
            puts("");
            return 0;
        }
    }
    puts("No solution");
    return 0;
}
