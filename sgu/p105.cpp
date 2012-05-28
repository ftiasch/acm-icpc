// SGU 105 -- Div 3
#include <iostream>
using namespace std;

int solve(int n) {
    if (n % 3 < 2) {
        return n / 3 * 2;
    }
    return n / 3 * 2 + 1;
}

int main() {
    int n;
    cin >> n;
    cout << solve(n) << endl;
    return 0;
}
