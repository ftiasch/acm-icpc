#include <cstdio>
#include <cstring>

const int N = 5000000;

char p[N + 1], t[N + 1];

int main()
{
    while (scanf("%s%s", p, t) == 2) {
        int n = strlen(p);
        while (true) {
            char* it = strstr(t, p);
            if (it == NULL) {
                break;
            }
            for (it += n; *it; ++ it) {
                *(it - n) = *it;
            }
            *(it - n) = '\0';
        }
        puts(t);
    }
    return 0;
}
