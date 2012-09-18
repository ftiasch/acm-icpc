// Codeforces Round #138
// Problem B -- Two Strings
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>
using namespace std;

const int N = 222222;

char a[N], b[N];
int prefix[N], suffix[N];
vector <int> indices[26];

int main() {
    scanf("%s%s", a + 1, b + 1);
    int n = strlen(a + 1);
    int m = strlen(b + 1);
    for (int i = 1; i <= m; ++ i) {
        indices[b[i] - 'a'].push_back(i);
    }
    prefix[0] = 0;
    for (int i = 1, j = 0; i <= n; ++ i) {
        if (j + 1 <= m && a[i] == b[j + 1]) {
            j ++;
        }
        prefix[i] = j;
    }
    suffix[n + 1] = m + 1;
    for (int i = n, j = m + 1; i >= 1; -- i) {
        if (j - 1 >= 1 && a[i] == b[j - 1]) {
            j --;
        }
        suffix[i] = j;
    }
    bool valid = true;
    for (int i = 1; i <= n; ++ i) {
        int low = max(suffix[i + 1] - 1, 1);
        int high = min(prefix[i - 1] + 1, m);
        int token = a[i] - 'a';
        vector <int> :: iterator iter = lower_bound(indices[token].begin(), indices[token].end(), low);
        valid &= iter != indices[token].end() && *iter <= high;
    }
    puts(valid? "Yes": "No");
    return 0;
}
