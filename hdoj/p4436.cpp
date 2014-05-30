#pragma comment(linker, "/STACK:16777216")
#include <cstdio>
#include <cstring>
#include <vector>

const int C   = 10;
const int MOD = 2012;

struct State;

std::vector <State*> states;

struct State {
    int length, count, sum;
    State *parent;
    State* go[C];

    State(int length) : length(length), count(-1), sum(-1), parent(NULL) {
        memset(go, 0, sizeof(go));
        states.push_back(this);
    }

    State* extend(State* start, int token) {
        State *p = this;
        State *np = new State(length + 1);
        while (p && !p->go[token]) {
            p->go[token] = np;
            p = p->parent;
        }
        if (!p) {
            np->parent = start;
        } else {
            State *q = p->go[token];
            if (p->length + 1 == q->length) {
                np->parent = q;
            } else {
                State *nq = new State(p->length + 1);
                memcpy(nq->go, q->go, sizeof(q->go));
                nq->parent = q->parent;
                np->parent = q->parent = nq;
                while (p && p->go[token] == q) {
                    p->go[token] = nq;
                    p = p->parent;
                }
            }
        }
        return np;
    }

    int get_count() {
        if (count == -1) {
            count = 0;
            for (int i = 0; i < C; ++ i) {
                if (go[i]) {
                    count += go[i]->get_count();
                    if (i) {
                        count ++;
                    }
                }
            }
            count %= MOD;
        }
        return count;
    }

    int get_sum() {
        if (sum == -1) {
            sum = 0;
            for (int i = 0; i < C; ++ i) {
                if (go[i]) {
                    sum += go[i]->get_sum() * 10 + go[i]->get_count() * i;
                    if (i) {
                        sum += i;
                    }
                }
            }
            sum %= MOD;
        }
        return sum;
    }
};

const int N = 100000;

char buffer[N + 1];

int main() {
    int n;
    while (scanf("%d", &n) == 1) {
        State* start = new State(0);
        for (int i = 0; i < n; ++ i) {
            State* p = start;
            scanf("%s", buffer);
            for (int j = strlen(buffer) - 1; j >= 0; -- j) {
                int c = buffer[j] - '0';
                p = p->extend(start, c);
            }
        }
        printf("%d\n", start->get_sum());
        for (int i = 0; i < (int)states.size(); ++ i) {
            delete states[i];
        }
        states.clear();
    }
    return 0;
}
