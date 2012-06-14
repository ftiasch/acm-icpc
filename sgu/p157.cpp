// SGU 157 -- Patience
#include <cstdio>
#include <cstring>
using namespace std;

const int result[13] = {1, 2, 5, 14, 47, 189, 891, 4815, 29547, 203173, 1548222, 12966093, 118515434};

int main() {
    int n;
    scanf("%d", &n);
    printf("%d\n", result[n - 1]);
    return 0;
}
