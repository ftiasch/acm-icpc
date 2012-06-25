// SGU 184 -- Patties
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

int main() {
    int a, b, c, x, y, z;
    scanf("%d%d%d%d%d%d", &a, &b, &c, &x, &y, &z);
    printf("%d\n", min(min(a / x, b / y), c / z));
    return 0;
}
