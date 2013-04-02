// SGU 281 -- Championship
#include <cstdio>
#include <cstring>
#include <string>
#include <map>
#include <vector>
#include <iostream>
#include <algorithm>

const int N = 50000;

int n, a_id[N], b_id[N], count[N];
char buffer[21];
std::string a[N], b[N];

int main() {
    scanf("%d", &n);
    std::vector <std::string> values;
    for (int i = 0; i < n; ++ i) {
        scanf("%s", buffer);
        a[i] = buffer;
        values.push_back(a[i]);
    }
    for (int i = 0; i < n; ++ i) {
        scanf("%s", buffer);
        b[i] = buffer;
    }
    std::sort(values.begin(), values.end());
    for (int i = 0; i < n; ++ i) {
        a_id[i] = std::lower_bound(values.begin(), values.end(), a[i]) - values.begin();
        b_id[i] = std::lower_bound(values.begin(), values.end(), b[i]) - values.begin();
    }
    memset(count, 0, sizeof(count));
    for (int begin = 0; begin < n; ) {
        int end = begin;
        int total = 0;
        do {
            total -= std::abs(count[a_id[end]]);
            count[a_id[end]] ++;
            total += std::abs(count[a_id[end]]);
            total -= std::abs(count[b_id[end]]);
            count[b_id[end]] --;
            total += std::abs(count[b_id[end]]);
            end ++;
        } while (total != 0);
        std::sort(a_id + begin, a_id + end);
        begin = end;
    }
    for (int i = 0; i < n; ++ i) {
        std::cout << values[a_id[i]] << std::endl;
    }
    return 0;
}
