#include <cstdio>
#include <iostream>
#include <cstring>
#include <queue>
#include <list>
#include <vector>
#include <string>
#include <algorithm>
using namespace std;

const int N = 5000;
const int M = N * 15 + 1;

int n, trieCount, children[M][26], endState[N], currentEndState;
bool isEnd[M];
string words[N], word;
char buffer[22];

int insert(const string &word) {
    int p = 0;
    for (int i = 0; i < (int)word.size(); ++ i) {
        int token = word[i] - 'a';
        if (children[p][token] == -1) {
            children[p][token] = trieCount ++;
        }
        p = children[p][token];
    }
    isEnd[p] = true;
    return p;
}

//struct State {
//    int i, p, mistake;
//
//    State(int i = 0, int p = 0, int mistake = 0): i(i), p(p), mistake(mistake) {}
//};
//
//typedef queue <State, list <State> > Queue;

bool find(int i, int p, int mistake) {
    if (p == -1) {
        return false;
    }
    if (i == (int)word.length() && isEnd[p] && p != currentEndState) {
        return true;
    }
    if (i < (int)word.length() && find(i + 1, children[p][word[i] - 'a'], mistake)) {
        return true;
    }
    if (mistake < 2) {
        if (i < (int)word.length() && find(i + 1, p, mistake + 1)) {
            return true;
        }
        for (int token = 0; token < 26; ++ token) {
            if (children[p][token] != -1 && find(i, children[p][token], mistake + 1)) {
                return true;
            }
        }
        if (i < (int)word.length()) {
            for (int token = 0; token < 26; ++ token) {
                if (children[p][token] != -1 && find(i + 1, children[p][token], mistake + 1)) {
                    return true;
                }
            }
        }
    }
    return false;
}

int main() {
    cin >> n;
    for (int i = 0; i < n; ++ i) {
        scanf("%s", buffer);
        words[i] = buffer;
    }
    trieCount = 1;
    memset(isEnd, 0, sizeof(isEnd));
    memset(children, -1, sizeof(children));
    for (int i = 0; i < n; ++ i) {
        endState[i] = insert(words[i]);
    }
    vector <string> result;
    int total = 0;
    for (int k = 0; k < n; ++ k) {
        word = words[k];
        currentEndState = endState[k];
        if (find(0, 0, 0)) {
            result.push_back(word);
        }
    }
    printf("%d\n", (int)result.size());
    sort(result.begin(), result.end());
    for (int i = 0; i < (int)result.size(); ++ i) {
        printf("%s\n", result[i].c_str());
    }
    return 0;
}
