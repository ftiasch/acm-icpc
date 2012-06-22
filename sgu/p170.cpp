// SGU 170 -- Particles
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>
using namespace std;

const int N = 10000;

char s_1[N], s_2[N];

vector <int> scan(const char* s) {
    int n = strlen(s);
    vector <int> result;
    for (int i = 0; i < n; ++ i) {
        if (s[i] == '+') {
            result.push_back(i);
        }
    }
    return result;
}

int main() {
    scanf("%s%s", s_1, s_2);
    vector <int> v_1 = scan(s_1);
    vector <int> v_2 = scan(s_2);
    if (v_1.size() == v_2.size()) {
        int result = 0;
        for (int i = 0; i < (int)v_1.size(); ++ i) {
            result += abs(v_1[i] - v_2[i]);
        }
        printf("%d\n", result);
    } else {
        puts("-1");
    }
    return 0;
}
