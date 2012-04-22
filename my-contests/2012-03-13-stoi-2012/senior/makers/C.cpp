#include <cstdio>
#include <cstring>
using namespace std;

const int N = 5555;

char string[N];

int char_to_int(char c) {
    if ('a' <= c && c <= 'c') {
        return c - 'a' + 1;
    }
    return 0;
}

int main() {
    scanf("%s", string);
    int length = strlen(string);
    bool different = false;
    for (int i = 1; i < length; ++ i) {
        different |= string[0] != string[i];
    }
    if (!different) {
        printf("%d\n", length);
    } else {
        int xor_sum = 0;
        for (int i = 0; i < length; ++ i) {
            xor_sum ^= char_to_int(string[i]);
        }
        if (xor_sum == 0) {
            printf("%d\n", 2);
        } else {
            printf("%d\n", 1);
        }
    }
    return 0;
}
