// SGU 142 -- Keyword
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>
using namespace std;

const int N = 500001;

int n;
char text[N];
unsigned hash[N];

unsigned get_hash(int i, int j) {
    return hash[i] - (hash[j] << (j - i));
}

int main() {
    scanf("%d%s", &n, text);
    hash[n] = 0;
    for (int i = n - 1; i >= 0; -- i) {
        hash[i] = (text[i] - 'a') + (hash[i + 1] << 1);
    }
    int length = 1;
    while (true) {
        vector <unsigned> values(n - length + 1);
        for (int i = 0; i + length <= n; ++ i) {
            values[i] = get_hash(i, i + length);
        }
        sort(values.begin(), values.end());
        values.erase(unique(values.begin(), values.end()), values.end());
        if ((int)values.size() < (1 << length)) {
            values.push_back((unsigned)-1);
            printf("%d\n", length);
            unsigned result = 0;
            while (values[result] == result) {
                result ++;
            }
            for (int i = 0; i < length; ++ i) {
                printf("%c", (result >> i & 1) == 0? 'a': 'b');
            }
            puts("");
            break;
        }
        length ++;
    }
    return 0;
}
