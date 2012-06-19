// SGU 165 -- Basketball
#include <cstdio>
#include <cstring>
#include <queue>
#include <vector>
using namespace std;

int parse(const char *string) {
    int result = 0;
    int length = strlen(string);
    for (int i = 0; i < length; ++ i) {
        if (string[i] != '.') {
            result = result * 10 + string[i] - '0';
        }
    }
    for (int i = 0; i < length; ++ i) {
        if (string[i] == '.') {
            int need = 6 - (length - 1 - i);
            while (need > 0) {
                need --;
                result *= 10;
            }
            return result;
        }
    }
    return result * 1000000;
}

const int N = 6000;

int n, a[N];

int main() {
    scanf("%d", &n);
    queue <int> positive_id, negative_id;
    for (int i = 0; i < n; ++ i) {
        char buffer[22];
        scanf("%s", buffer);
        a[i] = parse(buffer) - 2000000;
        if (a[i] > 0) {
            positive_id.push(i);
        } else {
            negative_id.push(i);
        }
    }
    vector <int> result;
    int sum = 0;
    for (int i = 0; i < n; ++ i) {
        if (!positive_id.empty() && sum + a[positive_id.front()] <= 100000) {
            result.push_back(positive_id.front());
            sum += a[positive_id.front()];
            positive_id.pop();
        } else {
            result.push_back(negative_id.front());
            sum += a[negative_id.front()];
            negative_id.pop();
        }
    }
    puts("yes");
    for (int i = 0; i < (int)result.size(); ++ i) {
        printf("%d%c", result[i] + 1, i == (int)result.size() - 1? '\n': ' ');
    }
    return 0;
}
