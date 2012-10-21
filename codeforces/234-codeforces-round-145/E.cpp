// Codeforces Round #145
// Problem E -- Champions' League
#include <cstdio>
#include <iostream>
#include <vector>
#include <functional>
#include <algorithm>
using namespace std;

int n, x, a, b, c;

int next() {
    return x = (x * a + b) % c;
}

const int N = 64;

pair <int, string> teams[N];
vector <string> brackets[4];

int main() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    cin >> n >> x >> a >> b >> c;
    for (int i = 0; i < n; ++ i) {
        string name;
        int rating;
        cin >> name >> rating;
        teams[i] = make_pair(rating, name);
    }
    sort(teams, teams + n, greater <pair <int, string> >());
    for (int i = 0; i < n; ++ i) {
        brackets[i / (n >> 2)].push_back(teams[i].second);
    }
    for (int k = 0; k < n >> 2; ++ k) {
        printf("Group %c:\n", 'A' + k);
        for (int i = 0; i < 4; ++ i) {
            int j = next() % (int)brackets[i].size();
            printf("%s\n", brackets[i][j].c_str());
            brackets[i].erase(brackets[i].begin() + j);
        }
    }
    return 0;
}
