#include <vector>
#include <string>
#include <map>
#include <iostream>
#include <algorithm>
using namespace std;

int trie_count;
map <string, int> children[111111];

int main() {
    ios::sync_with_stdio(false);
    while (true) {
        int n;
        cin >> n;
        if (n == 0) {
            break;
        }
        trie_count = 0;
        children[0].clear();
        while (n > 0) {
            n --;
            int m;
            cin >> m;
            vector <string> v(m);
            for (int i = 0; i < m; ++ i) {
                cin >> v[i];
            }
            reverse(v.begin(), v.end());
            int p = 0;
            for (int i = 0; i < m; ++ i) {
                if (children[p].find(v[i]) == children[p].end()) {
                    children[p][v[i]] = trie_count;
                    children[trie_count ++].clear();
                }
                p = children[p][v[i]];
            }
        }
        cout << trie_count << endl;
    }
    return 0;
}
