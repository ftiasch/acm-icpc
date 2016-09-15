#include <cstdio>
#include <cstring>

int count[1 << 18];

int parse(const char* pattern)
{
    int length = strlen(pattern);
    if (length > 18) {
        for (int i = 18; i < length; ++ i) {
            if (pattern[length - 1 - i] - '0' & 1) {
                return -1;
            }
        }
    }
    int mask = 0;
    for (int i = 0; i < 18 && i < length; ++ i) {
        mask |= (pattern[length - 1 - i] - '0' & 1) << i;
    }
    return mask;
}

int main()
{
#ifdef LOCAL_JUDGE
    freopen("A.in", "r", stdin);
#endif
    int q;
    scanf("%d", &q);
    memset(count, 0, sizeof(*count));
    while (q --) {
        char buffer[2], pattern[19];
        scanf("%s%s", buffer, pattern);
        int mask = parse(pattern);
        if (*buffer == '+') {
            if (~mask) {
                count[mask] ++;
            }
        } else if (*buffer == '-') {
            if (~mask) {
                count[mask] --;
            }
        } else if (*buffer == '?') {
            printf("%d\n", count[mask]);
        }
    }
}
