// SGU 191 -- Exhibition
#include <cstdio>
#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
using namespace std;

vector <int> toVector(const string &s) {
    vector <int> v;
    for (int i = (int)s.length() - 1; i >= 0; -- i) {
        v.push_back(s[i] - 'A');
    }
    return v;
}

int main() {
    string a, b;
    cin >> a >> b;
    vector <int> init = toVector(a);
    vector <int> final = toVector(b);
    while (final.size() >= init.size() && init.size() > 0) {
        if (final.back() == init.back()) {
            int type = init.back();
            init.push_back(type ^ 1);
        } else {
            init.pop_back();
        }
        final.pop_back();
    }
    puts(final.empty() && init.empty()? "YES": "NO");
    return 0;
}
