// SGU 178 -- Golden chain
#include <cstdio>
#include <iostream>
using namespace std;

int main() {
    long long n;
    cin >> n;
    if (n == 1) {
        puts("0");
    } else {
        int result = 1;
        while (true) {
            long long total = result;
            int left = result + 1;
            while (left > 0) {
                left --;
                long long next = total + 1;
                total += next;
            }
            if (total >= n) {
                printf("%d\n", result);
                return 0;
            }
            result ++;
        }
    }
    return 0;
}
