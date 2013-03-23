// SGU 397 -- Text Editor
#include <cstdio>
#include <cstring>
#include <stack>

const int N = 1000000;

char operation[N + 1];

int main() {
    scanf("%s", operation);
    int n = strlen(operation);
    std::stack <char> left, right;
    for (int i = 0; i < n; ++ i) {
        if (operation[i] == 'L') {
            if (!left.empty()) {
                right.push(left.top());
                left.pop();
            }
        } else if (operation[i] == 'R') {
            if (!right.empty()) {
                left.push(right.top());
                right.pop();
            }
        } else {
            left.push(operation[i]);
        }
    }
    while (!left.empty()) {
        right.push(left.top());
        left.pop();
    }
    while (!right.empty()) {
        putchar(right.top());
        right.pop();
    }
    return 0;
}
