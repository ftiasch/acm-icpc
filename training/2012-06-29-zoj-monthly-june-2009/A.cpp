// Problem A -- Beautiful Meadow
#include <cstdio>
#include <cstring>
#include <map>
#include <algorithm>
using namespace std;

struct Mask {
    int mask;

    Mask(int mask = 0): mask(mask) {}

    int operator[](int k) const {
        return mask >> (k << 1) & 3;
    }

    Mask set(int k, int v) const {
        return mask & ~(3 << (k << 1)) | (v << (k << 1));
    }

    Mask shift() const {
        return mask << 2;
    }

    int match(int k) const {
        int delta = (*this)[k] == 2? 1: -1;
        int count = 0;
        for (int i = k; ; i += delta) {
            if ((*this)[i] == 2) {
                count ++;
            }
            if ((*this)[i] == 3) {
                count --;
            }
            if (count == 0) {
                return i;
            }
        }
        return -1;
    }

    bool empty() const {
        return mask == 0;
    }

    void show(int m) {
        for (int i = 0; i <= m; ++ i) {
            printf("%c", "01()"[(*this)[i]]);
        }
        puts("");
    }
};

bool operator <(const Mask &a, const Mask &b) {
    return a.mask < b.mask;
}

const int N = 10;

typedef map <Mask, int> Map;

int n, m, weight[N][N];

void update(Map *states, Mask state, int value) {
    if (states->find(state) == states->end()) {
        states->insert(make_pair(state, value));
    }
    (*states)[state] = max((*states)[state], value);
}

int main() {
    int testCount;
    scanf("%d", &testCount);
    Map *states = new Map();
    Map *newStates = new Map();
    while (testCount > 0) {
        testCount --;
        scanf("%d%d", &n, &m);
        memset(weight, 0, sizeof(weight));
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                scanf("%d", &weight[i][j]);
            }
        }
        int result = 0;
        newStates->clear();
        newStates->insert(make_pair(Mask(0), 0));
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                result = max(result, weight[i][j]);
                //printf("%d, %d\n", i, j);
                swap(states, newStates);
                newStates->clear();
                for (Map :: iterator iter = states->begin(); iter != states->end(); ++ iter) {
                    Mask state = j == 0? (iter->first).shift(): iter->first;
                    //state.show(m);
                    int value = iter->second + weight[i][j];
                    if (weight[i][j] == 0) {
                        if (state[j] == 0 && state[j + 1] == 0) {
                            update(newStates, state, value);
                        }
                    } else {
                        if (state[j] == 0 && state[j + 1] == 0) {
                            update(newStates, state, value - weight[i][j]);
                            if (weight[i][j + 1] > 0 && weight[i + 1][j] > 0) {
                                update(newStates, state.set(j, 2).set(j + 1, 3), value);
                            }
                            if (weight[i + 1][j] > 0) {
                                update(newStates, state.set(j, 1), value);
                            }
                            if (weight[i][j + 1] > 0) {
                                update(newStates, state.set(j + 1, 1), value);
                            }
                        } else if (state[j] == 0 || state[j + 1] == 0) {
                            int type = state[j] | state[j + 1];
                            if (type == 1) {
                                if (state.set(j, 0).set(j + 1, 0).empty()) {
                                    result = max(result, value);
                                }
                            } else {
                                if (state[j] > 0) {
                                    update(newStates, state.set(state.match(j), 1).set(j, 0).set(j + 1, 0), value);
                                }
                                if (state[j + 1] > 0) {
                                    update(newStates, state.set(state.match(j + 1), 1).set(j, 0).set(j + 1, 0), value);
                                }
                            }
                            if (weight[i + 1][j] > 0) {
                                update(newStates, state.set(j, type).set(j + 1, 0), value);
                            }
                            if (weight[i][j + 1] > 0) {
                                update(newStates, state.set(j, 0).set(j + 1, type), value);
                            }
                        } else {
                            if (state[j] == 1 && state[j + 1] == 1) {
                                if (state.set(j, 0).set(j + 1, 0).empty()) {
                                    result = max(result, value);
                                }
                            } else if (state[j] == 1 || state[j + 1] == 1) {
                                if (state[j] != 1) {
                                    update(newStates, state.set(state.match(j), 1).set(j, 0).set(j + 1, 0), value);
                                } else {
                                    update(newStates, state.set(state.match(j + 1), 1).set(j, 0).set(j + 1, 0), value);
                                }
                            } else {
                                if (state[j] == 2) {
                                    if (state[j + 1] == 2) {
                                        update(newStates, state.set(state.match(j + 1), 2).set(j, 0).set(j + 1, 0), value);
                                    }
                                } else {
                                    if (state[j + 1] == 2) {
                                        update(newStates, state.set(j, 0).set(j + 1, 0), value);
                                    } else {
                                        update(newStates, state.set(state.match(j), 3).set(j, 0).set(j + 1, 0), value);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        printf("%d\n", result);
    }
    return 0;
}
