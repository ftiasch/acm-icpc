// Problem H -- Killing Streak
#include <cstdio>
#include <cstring>
#include <string>
#include <map>
#include <climits>
#include <vector>
#include <algorithm>
using namespace std;

const string TEXTS[8] = {
    "%s has a Killing Spree!", 
    "%s is Dominating!", 
    "%s has a Mega Kill!", 
    "%s is Unstoppable!", 
    "%s is Wicked Sick!", 
    "%s has a Monster Kill!!!", 
    "%s is Godlike!", 
    "%s is Beyond Godlike! Somebody kill him!!"
};

struct Event {
    int t;
    string a, b;

    Event(int t, string a, string b): t(t), a(a), b(b) {}
};

bool operator <(const Event &a, const Event &b) {
    return a.t < b.t;
}

int main() {
    int testCount;
    scanf("%d", &testCount);
    while (testCount > 0) {
        testCount --;
        vector <Event> events;
        while (true) {
            char time[22], nameA[22], buffer[22], nameB[22];
            scanf("%s", time);
            if (*time == '#') {
                break;
            }
            int minute, second;
            sscanf(time, "%d%*c%d", &minute, &second);
            scanf("%s%s%s", nameA, buffer, nameB);
            events.push_back(Event(minute * 60 + second, nameA, nameB));
        }
        sort(events.begin(), events.end());
        map <string, pair <int, vector <int> > > data;
        bool first = true;
        for (vector <Event> :: iterator e = events.begin(); e != events.end(); ++ e) {
            pair <int, vector <int> > &v = data[e->a];
            v.first ++;
            v.second.push_back(e->t);
            if (first) {
                first = false;
                printf("%s just drew First Blood!\n", e->a.c_str());
            }
            if (v.first >= 3) {
                printf(TEXTS[min(v.first, 10) - 3].c_str(), e->a.c_str());
                puts("");
            }
            data[e->b].first = 0;
            int s = v.second.size();
            if (s >= 2 && v.second[s - 1] - v.second[s - 2] <= 14) {
                if (s >= 3 && v.second[s - 2] - v.second[s - 3] <= 14) {
                    printf("%s just got a Triple Kill!\n", e->a.c_str());
                } else {
                    printf("%s just got a Double Kill!\n", e->a.c_str());
                }
            }
        }
        puts("");
    }
    return 0;
}
