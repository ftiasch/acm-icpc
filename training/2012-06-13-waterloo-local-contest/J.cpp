#include <cstdio>
#include <cstring>
#include <stack>
using namespace std;

int main() {
    char expression[222];
    while (scanf("%s", expression) == 1) {
        if (*expression == '0') {
            break;
        }
        int length = strlen(expression);
        bool check = true;
        for (int mask = 0; mask < (1 << 5) && check; ++ mask) {
            stack <int> value;
            for(int i = length - 1; i >= 0; -- i) {
                char token = expression[i];
                if ('p' <= token && token <= 't') {
                    value.push(mask >> (token - 'p') & 1);
                } else if (token == 'N') {
                    int v = value.top();
                    value.pop();
                    value.push(v ^ 1);
                } else {
                    int u = value.top();
                    value.pop();
                    int v = value.top();
                    value.pop();
                    if (token == 'K') {
                        value.push(u & v);
                    } else if (token == 'A') {
                        value.push(u | v);
                    } else if (token == 'C') {
                        value.push((u ^ 1) | v);
                    } else if (token == 'E') {
                        value.push(u ^ v ^ 1);
                    }
                }
            }
            check &= value.top() == 1;
        }
        puts(check? "tautology": "not");
    }
    return 0;
}
