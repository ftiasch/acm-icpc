// Codeforces Beta Round #30 
// Problem E -- Tricky and Clever Password
#include <cstdio>
#include <cstring>
#include <climits>
#include <vector>
#include <algorithm>
using namespace std;

const int N = 100001;

char text[N];
int n, fail[N], match[N], palindrome[N];

int main() {
    scanf("%s", text);
    n = strlen(text);
    fail[n - 1] = n;
    for (int i = n - 1; i > 0; -- i) {
        fail[i - 1] = n;
        int p = i;
        while (p < n) {
            p = fail[p];
            if (text[i - 1] == text[p - 1]) {
                fail[i - 1] = p - 1;
                break;
            }
        }
    }
    for (int i = 0; i <= n; ++ i) {
        match[i] = INT_MAX;
    }
    for (int i = 0, p = n; i < n; ++ i) {
        if (p > 0 && text[p - 1] == text[i]) {
            p --;
        } else {
            while (p < n) {
                p = fail[p];
                if (text[p - 1] == text[i]) {
                    p --;
                    break;
                }
            }
        }
        match[p] = min(match[p], i);
    }
    for (int i = 0; i < n; ++ i) {
        match[fail[i]] = min(match[fail[i]], match[i]);
    }
    match[n] = -1;
    palindrome[0] = 1;
    for (int i = 1, j = 0; i < n; ++ i) {
        if (j + palindrome[j] <= i) {
            palindrome[i] = 0;
        } else {
            palindrome[i] = min(palindrome[(j << 1) - i], j + palindrome[j] - i);
        }
        while (i - palindrome[i] >= 0 && i + palindrome[i] < n && text[i - palindrome[i]] == text[i + palindrome[i]]) {
            palindrome[i] ++;
        }
        if (i + palindrome[i] > j + palindrome[j]) {
            j = i;
        }
    }
    int result = 0;
    vector <pair <int, int> > ways;
    for (int i = 0; i < n; ++ i) {
        int begin = i - palindrome[i] + 1;
        int end = i + palindrome[i] - 1;
        int lower = end + 1;
        int upper = n;
        while (lower < upper) {
            int mider = (lower + upper) >> 1;
            if (match[mider] < begin) {
                upper = mider;
            } else {
                lower = mider + 1;
            }
        }
        int length = n - upper;
        int tmp = (length << 1) + (palindrome[i] << 1) - 1;
        if (tmp > result) {
            result = tmp;
            ways.clear();
            if (length > 0) {
                ways.push_back(make_pair(match[upper] - length + 1, length));
            }
            ways.push_back(make_pair(begin, end - begin + 1));
            if (length > 0) {
                ways.push_back(make_pair(upper, length));
            }
        }
    }
    printf("%d\n", (int)ways.size());
    for (vector <pair <int, int> > :: iterator iter = ways.begin(); iter != ways.end(); ++ iter) {
        printf("%d %d\n", iter->first + 1, iter->second);
    }
    return 0;
}
