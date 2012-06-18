// Problem D -- Frugal Search
#include <cctype>
#include <cstdio>
#include <cstring>
#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

int main() {
    while (true) {
        vector <string> words;
        while (true) {
            string word;
            cin >> word;
            if (word[0] == '#' || word[0] == '*') {
                break;
            }
            words.push_back(word);
        }
        if ((int)words.size() == 0) {
            break;
        }
        sort(words.begin(), words.end());
        while (true) {
            string query;
            cin >> query;
            if (query[0] == '*') {
                break;
            }
            bool found = false;
            int mark[26];
            for (int i = 0; i < (int)words.size() && !found; ++ i) {
                for (int lower = 0; lower < (int)query.size() && !found; ++ lower) {
                    int upper = lower;
                    while (upper < (int)query.size() && query[upper] != '|') {
                        upper ++;
                    }
                    memset(mark, -2, sizeof(mark));
                    for (int j = lower; j < upper; ++ j) {
                        if (isalpha(query[j])) {
                            mark[query[j] - 'a'] = 0;
                        } else {
                            if (query[j] == '+') {
                                mark[query[j + 1] - 'a'] = 1;
                            } else {
                                mark[query[j + 1] - 'a'] = -1;
                            }
                            j ++;
                        }
                    }
                    bool check = true;
                    int unsign_count = 0;
                    for (int j = 0; j < (int)words[i].size(); ++ j) {
                        int token = words[i][j] - 'a';
                        if (mark[token] == -2) {
                            continue;
                        }
                        if (mark[token] == 0) {
                            unsign_count ++;
                        } else if (mark[token] == 1) {
                            mark[token] = -2;
                        } else if (mark[token] == -1) {
                            check = false;
                        }
                    }
                    for (int j = 0; j < 26; ++ j) {
                        if (mark[j] == 1) {
                            check = false;
                        }
                    }
                    if (check && unsign_count > 0) {
                        found = true;
                        cout << words[i] << "\n";
                        break;
                    }
                    lower = upper;
                }
            }
            if (!found) { 
                cout << "NONE\n";
            }
        }
        cout << "$\n";
    }
    cout << flush;
    return 0;
}
