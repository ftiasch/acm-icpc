#include <iostream>
#include <algorithm>
using namespace std;

typedef long long Long;

Long solveMax(Long K, Long L, Long N) {
    Long result = 0;
    if (L > 0) {
        result = max(result, min(K, N - 1) * 3 + (N - 1 - min(K, N - 1)));
    }
    if (K >= L) {
        result = max(result, min(K - L, N) * 3 + (N - min(K - L, N)));
    }
    return result;
}

Long solveMin(Long K, Long L, Long N) {
    Long result = solveMax(K, L, N);
    if (K > 0) {
        result = min(result, 3 + (N - 1 - min(N - 1, L)));
    }
    if (K <= L) {
        result = min(result, N - min(N, L - K));
    }
    return result;
}

int main() {
    Long K, L, N;
    cin >> K >> L >> N;
    cout << solveMax(K, L, N) << " " << solveMin(K, L, N) << endl;
    return 0;
}
