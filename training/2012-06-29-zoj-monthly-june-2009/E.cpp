// Problem E -- Count the Derivations
#include <cstdio>
#include <cstring>
#include <utility>
#include <vector>
using namespace std;

const int N = 2222;
const int MOD = 1000000007;

int match[N], stack[N], stackSize, choose[N][N];
char text[N];

pair <int, int> parse(int begin, int end) {
    if (begin + 2 == end) {
        return make_pair(0, 1);
    }
    pair <int, int> result(0, 0);
    vector <pair <int, int> > children;
    for (int i = begin + 1; i < end - 1; i = match[i] + 1) {
        children.push_back(parse(i, match[i] + 1));
        result.first += children.back().first;
    }
    int left = result.first;
    long long tmp = 1;
    for (int i = 0; i < (int)children.size(); ++ i) {
        tmp = ((tmp * children[i].second) % MOD * choose[left][children[i].first]) % MOD;
        left -= children[i].first;
    }
    result.first ++;
    result.second = tmp;
    return result;
}

int main() {
    memset(choose, 0, sizeof(choose));
    for (int i = 0; i < N; ++ i) {
        choose[i][0] = 1;
        for (int j = 1; j <= i; ++ j) {
            choose[i][j] = (choose[i - 1][j] + choose[i - 1][j - 1]) % MOD;
        }
    }
    int testCount;
    scanf("%d", &testCount);
    while (testCount > 0) {
        testCount --;
        scanf("%s", text);
        stackSize = 0;
        int length = strlen(text);
        for (int i = length - 1; i >= 0; -- i) {
            if (text[i] == ')') {
                stack[stackSize ++] = i;
            } else {
                match[i] = stack[-- stackSize];
            }
        }
        printf("%d\n", parse(0, length).second);
    }
    return 0;
}
