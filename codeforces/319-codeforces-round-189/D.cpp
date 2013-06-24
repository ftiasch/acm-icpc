// Codeforces Round #189
// Problem D -- Have You Ever Heard About the Word?
#include <cstdio>
#include <cstring>
#include <algorithm>

typedef unsigned long long ULL;

const ULL MAGIC = 173;

const int N = 50000;

int n;
char text[N + 2];

ULL power[N + 1], hash[N + 1];

void prepare() {
    hash[0] = 0;
    for (int i = 1; i <= n; ++ i) {
        hash[i] = hash[i - 1] * MAGIC + text[i];
    }
}

ULL get_hash(int l, int r) {
    return hash[r] - hash[l - 1] * power[r - l + 1];
}

int get_lcp(int i, int j) {
    int low = 0;
    int high = n - std::max(i, j) + 1;
    while (low < high) {
        int middle = low + high + 1 >> 1;
        if (get_hash(i, i + middle - 1) == get_hash(j, j + middle - 1)) {
            low = middle;
        } else {
            high = middle - 1;
        }
    }
    return low;
}

int get_lcs(int i, int j) {
    int low = 0;
    int high = std::min(i, j);
    while (low < high) {
        int middle = low + high + 1 >> 1;
        if (get_hash(i - middle + 1, i) == get_hash(j - middle + 1, j)) {
            low = middle;
        } else {
            high = middle - 1;
        }
    }
    return low;
}

bool check(int length) {
    for (int i = 1; i + length <= n; i += length) {
        if (get_lcp(i, i + length) + get_lcs(i, i + length) >= length) {
            return true;
        }
    }
    return false;
}

void resolve(int length) {
    int total = 0;
    ULL now_hash = 0;
    for (int i = 1; i <= n; ) {
        if (i + length - 1 <= n && get_hash(i, i + length - 1) == now_hash) {
            i += length;
        } else {
            text[++ total] = text[i];
            now_hash = now_hash * MAGIC + text[total];
            if (total > length) {
                now_hash -= power[length] * text[total - length];
            }
            i ++;
        }
    }
    n = total;
}

int main() {
    power[0] = 1;
    for (int i = 1; i <= N; ++ i) {
        power[i] = power[i - 1] * MAGIC;
    }
    scanf("%s", text + 1);
    n = strlen(text + 1);
    prepare();
    for (int length = 1; length << 1 <= n; ++ length) {
        if (check(length)) {
            resolve(length);
            prepare();
        }
    }
    text[n + 1] = '\0';
    puts(text + 1);
    return 0;
}
