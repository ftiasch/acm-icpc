#include <cstdio>
#include <cstring>
#include <vector>
#include <map>
using namespace std;

const int MOD = 10003;

int a, n;

int main() {
    scanf("%d%d", &a, &n);
    vector <int> sequence(1, a);
    map <int, int> hash;
    hash[a] = 0;
    for (int i = 1; i <= n; ++ i) {
        a = (a * a + a + 1) % MOD;
        sequence.push_back(a);
        if (hash.count(a)) {
            int start = hash[a];
            int cycle = i - hash[a];
            printf("%d\n", sequence[start + (n - start) % cycle]);
            return 0;
        } else {
            hash[a] = i;
        }
    }
    printf("%d\n", a);
    return 0;
}
