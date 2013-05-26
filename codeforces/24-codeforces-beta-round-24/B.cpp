// Codeforces Beta Round #24
// Problem B -- F1 Champions
#include <cstdio>
#include <cstring>
#include <map>
#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

using std::cin;

const int SCORE[10] = {25, 18, 15, 12, 10, 8, 6, 4, 2, 1};

struct Player {
    std::string name;
    int score;
    std::map <int, int> count;

    Player(std::string name) : name(name), score(0) {}

    Player add(int i) {
        if (i < 10) {
            score += SCORE[i];
        }
        count[i] ++;
        return *this;
    }

    int get(int i) const {
        std::map <int, int> :: const_iterator iter = count.find(i);
        if (iter != count.end()) {
            return iter->second;
        }
        return 0;
    }
};

std::map <std::string, Player*> player_map;

void access(std::string name) { 
    if (!player_map.count(name)) {
        player_map[name] = new Player(name);
    }
}

bool by_tradition(const Player &a, const Player &b) {
    if (a.score != b.score) {
        return a.score < b.score;
    }
    for (int i = 0; i < 20; ++ i) {
        if (a.get(i) != b.get(i)) {
            return a.get(i) < b.get(i);
        }
    }
    return false;
}

bool by_modern(const Player &a, const Player &b) {
    if (a.get(0) != b.get(0)) {
        return a.get(0) < b.get(0);
    }
    return by_tradition(a, b);
}

int main() {
    int t;
    cin >> t;
    while (t --) {
        int n;
        cin >> n;
        for (int i = 0; i < n; ++ i) {
            std::string name;
            cin >> name;
            access(name);
            player_map[name]->add(i);
        }
    }
    std::vector <Player> players;
    foreach (iter, player_map) {
        players.push_back(*iter->second);
    }
    Player winner = *std::max_element(players.begin(), players.end(), by_tradition);
    std::cout << winner.name << std::endl;
    winner = *std::max_element(players.begin(), players.end(), by_modern);
    std::cout << winner.name << std::endl;
    return 0;
}
