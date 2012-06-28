// SGU 195  -- New Year Bonus Grant
#include <cstdio>
#include <cstring>
#include <climits>
#include <utility>
#include <vector>
#include <functional>
#include <algorithm>
using namespace std;

const int N = 500001;

int n, parent[N], depth[N];
bool mark[N];
vector <pair <int, int> > order;

int main() {
    scanf("%d", &n);
    depth[1] = 0;
    for (int i = 2; i <= n; ++ i) {
        scanf("%d", parent + i);
        depth[i] = depth[parent[i]] + 1;
        order.push_back(make_pair(depth[i], i));
    }
    sort(order.begin(), order.end(), greater <pair <int, int> >());
    memset(mark, 0, sizeof(mark));
    vector <int> result;
    for (vector <pair <int, int> > :: iterator iter = order.begin(); iter != order.end(); ++ iter) {
        if (!mark[iter->second] && !mark[parent[iter->second]]) {
            mark[iter->second] = mark[parent[iter->second]] = true;
            result.push_back(iter->second);
        }
    }
    printf("%d\n", (int)result.size() * 1000);
    sort(result.begin(), result.end());
    for (int i = 0; i < (int)result.size(); ++ i) {
        printf("%d%c", result[i], i == (int)result.size() - 1? '\n': ' ');
    }
    return 0;
}
