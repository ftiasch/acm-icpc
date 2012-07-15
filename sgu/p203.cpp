// SGU 203 -- Hyperhuffman
#include <cstdio>
#include <cstring>
#include <iostream>
using namespace std;

const int N = 500000;

int head[2], tail[2];
long long queue[2][N];

const long long INF = 1000000000000000000LL;

#define VALUE(t) (head[t] == tail[t]? INF: queue[t][head[t]])

long long getMinmimum() {
    int t = VALUE(0) > VALUE(1);
    return queue[t][head[t] ++];
}

int main() {
    int n;
    scanf("%d", &n);
    head[0] = tail[0] = head[1] = tail[1] = 0;
    for (int i = 0; i < n; ++ i) {
        int p;
        scanf("%d", &p);
        queue[0][tail[0] ++] = p;
    }
    long long result = 0;
    for (int k = 0; k < n - 1; ++ k) {
        long long a = getMinmimum();
        long long b = getMinmimum();
        queue[1][tail[1] ++] = a + b;
        result += a + b;
    }
    cout << result << endl;
    return 0;
}
