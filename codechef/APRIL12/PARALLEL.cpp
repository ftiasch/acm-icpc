// Codechef April Challenge 2012
// Parallel Computing
#include <cstdio>
#include <cstring>
#include <vector>
using namespace std;

struct Node {
    int a, b, c;

    Node(int a, int b, int c): a(a), b(b), c(c) {}
};

vector <vector <Node> > result;

void solve(int base, int n) {
    if ((base << 1) <= n) {
        result.push_back(vector <Node>());
        for (int i = base; i + base <= n; i += (base << 1)) {
            result.back().push_back(Node(i, i + base, i + base));
        }
        solve(base << 1, n);
        result.push_back(vector <Node>());
        for (int i = base + base; i + base <= n; i += (base << 1)) {
            result.back().push_back(Node(i, i + base, i + base));
        }
    }
}

int main() {
    int n;
    scanf("%d", &n);
    solve(1, n);
    printf("%d\n", (int)result.size());
    for (vector <vector <Node> > :: iterator v = result.begin(); \
            v != result.end(); ++ v) {
        printf("%d", (int)v->size());
        for (vector <Node> :: iterator iter = v->begin(); \
                iter != v->end(); ++ iter) {
            printf(" %d+%d=%d", iter->a, iter->b, iter->c);
        }
        puts("");
    }
    return 0;
}
