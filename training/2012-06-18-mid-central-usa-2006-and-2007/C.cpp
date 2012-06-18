// Problem C -- Surprising Strings
#include <cstdio>
#include <cstring>
using namespace std;

bool visit[255][255];

int main() {
    char buffer[222];
    while (true) {
        scanf("%s", buffer);
        if (*buffer == '*') {
            break;
        }
        bool check = true;
        int length = strlen(buffer);
        for (int d = 1; d < length; ++ d) {
            memset(visit, 0, sizeof(visit));
            for (int i = 0; i + d < length; ++ i) {
                if (visit[(int)buffer[i]][(int)buffer[i + d]]) {
                    check = false;
                }
                visit[(int)buffer[i]][(int)buffer[i + d]] = true;
            }
        }
        if (check) {
            printf("%s is surprising.\n", buffer);
        } else {
            printf("%s is NOT surprising.\n", buffer);
        }
    }
    return 0;
}
