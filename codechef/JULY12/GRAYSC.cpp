// July Challenge 2012
// The Gray-Similar Code
#include <cstdio>
#include <cstring>
#include <map>
#include <iostream>
#include <algorithm>
using namespace std;

typedef unsigned long long UnsignedLong;

const int N = 200;

int n;
UnsignedLong a[N];
map <UnsignedLong, int> minimumIndex;

int main() {
    scanf("%d", &n);
    if (n > N) {
        puts("Yes");
    } else {
        for (int i = 0; i < n; ++ i) {
            cin >> a[i];
        }
        for (int i = 0; i < n; ++ i) {
            for (int j = i + 1; j < n; ++ j) {
                minimumIndex[a[i] ^ a[j]] = i;
            }
        }
        bool found = false;
        for (int i = 0; i < n && !found; ++ i) {
            for (int j = i + 1; j < n && !found; ++ j) {
                int &ret = minimumIndex[a[i] ^ a[j]];
                if (j < ret) {
                    found = true;
                }
            }
        }
        puts(found? "Yes": "No");
    }
    return 0;
}
