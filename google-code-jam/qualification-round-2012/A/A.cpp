#include <cctype>
#include <cstdio>
#include <cstring>
using namespace std;

char mapping[26] = {24, 7, 4, 18, 14, 2, 21, 23, 3, 20, 8, 6, 11, 1, 10, 17, 25, 19, 13, 22, 9, 15, 5, 12, 0, 16};

int main() {
    int test_count;
    scanf("%d", &test_count);
    char buffer[111];
    fgets(buffer, 111, stdin);
    for (int test = 1; test <= test_count; ++ test) {
        char string[111];
        fgets(string, 111, stdin);
        int length = strlen(string);
        for (int i = 0; i < length; ++ i) {
            if (isalpha(string[i])) {
                string[i] = mapping[string[i] - 'a'] + 'a';
            }
        }
        printf("Case #%d: %s", test, string);
    }
    return 0;
}
