// Yandex Algorithm Open 2011 Qualification 1
// Problem A -- Plug-in
#include <cstdio>
#include <cstring>

const int N = 200000 + 1;

char string[N];

int main() {
    scanf("%s", string);
    int size = 0;
    for (int i = 0; string[i]; ++ i) {
        if (size && string[size - 1] == string[i]) {
            size --;
        } else {
            string[size ++] = string[i];
        }
    }
    string[size] = '\0';
    puts(string);
    return 0;
}
