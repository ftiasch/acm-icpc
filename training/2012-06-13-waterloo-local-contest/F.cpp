#include <cstdio>
#include <cstring>
#include <queue>
#include <algorithm>
using namespace std;

int main() {
    char expression[222];
    while (scanf("%s", expression) == 1 && *expression != '0') {
        int length = strlen(expression);
        int unitary_count = 0;
        queue <int> binaries, variables;
        for (int i = 0; i < length; ++ i) {
            char token = expression[i];
            if ('p' <= token && token <= 't') {
                variables.push(token);
            } else if (token == 'N') {
                unitary_count ++;
            } else {
                binaries.push(token);
            }
        }
        if ((int)variables.size() >= 1) {
            for (int i = 0; i < unitary_count; ++ i) {
                printf("N");
            }
            int result = min((int)binaries.size(), (int)variables.size() - 1);
            for (int i = 0; i < result; ++ i) {
                printf("%c%c", binaries.front(), variables.front());
                binaries.pop();
                variables.pop();
            }
            printf("%c\n", variables.front());
        } else {
            puts("no WFF possible");
        }
    }
    return 0;
}
