// SGU 166 -- Editor
#include <cctype>
#include <cstdio>
#include <cstring>
#include <list>
#include <algorithm>
using namespace std;

#define foreach(i, v) for (typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

const int N = 30000;

char buffer[N];

int count(list <char> :: iterator begin, list <char> :: iterator end) {
    int result = 0;
    for (list <char> :: iterator iter = begin; iter != end; ++ iter) {
        result ++;
    }
    return result;
}

int main() {
    fgets(buffer, N, stdin);
    list <list <char> > *text = new list <list <char> >(1, list <char>());
    list <list <char> > :: iterator line = text->begin();
    list <char> :: iterator cursor = line->begin();
    int extra_space = 0;
    for (char *c = buffer; *c != '\n'; ++ c) {
        if (islower(*c) || *c == ' ') {
            while (extra_space > 0) {
                extra_space --;
                line->insert(cursor, ' ');
            }
            line->insert(cursor, *c);
        }
        if (*c == 'L') {
            if (extra_space > 0) {
                extra_space --;
            } else if (cursor != line->begin()) {
                cursor --;
            }
        }
        if (*c == 'R') {
            if (cursor != line->end()) {
                cursor ++;
            } else {
                extra_space ++;
            }
        }
        if (*c == 'U') {
            int current_position = cursor == line->end()? (int)line->size() + extra_space: count(line->begin(), cursor);
            extra_space = 0;
            if (line != text->begin()) {
                line --;
                cursor = line->begin();
                while (current_position > 0) {
                    current_position --;
                    if (cursor != line->end()) {
                        cursor ++;
                    } else {
                        extra_space ++;
                    }
                }
            }
        }
        if (*c == 'D') {
            int current_position = cursor == line->end()? (int)line->size() + extra_space: count(line->begin(), cursor);
            extra_space = 0;
            line ++;
            if (line == text->end()) {
                line --;
            } else {
                cursor = line->begin();
                while (current_position > 0) {
                    current_position --;
                    if (cursor != line->end()) {
                        cursor ++;
                    } else {
                        extra_space ++;
                    }
                }
            }
        }
        if (*c == 'N') {
            extra_space = 0;
            list <list <char> > :: iterator previous_line = line;
            line ++;
            text->insert(line, list <char>());
            line --;
            line->insert(line->begin(), cursor, previous_line->end());
            previous_line->erase(cursor, previous_line->end());
            cursor = line->begin();
        }
        if (*c == 'E') {
            extra_space = 0;
            cursor = line->end();
        }
        if (*c == 'H') {
            extra_space = 0;
            cursor = line->begin();
        }
        if (*c == 'X') {
            if (cursor != line->end()) {
                cursor = line->erase(cursor);
            } else {
                list <list <char> > :: iterator next_line = line;
                next_line ++;
                if (next_line != text->end()) {
                    while (extra_space > 0) {
                        extra_space --;
                        line->insert(cursor, ' ');
                    }
                    int current_position = line->size();
                    line->insert(line->end(), next_line->begin(), next_line->end());
                    text->erase(next_line);
                    cursor = line->begin();
                    while (current_position > 0) {
                        current_position --;
                        cursor ++;
                    }
                }
            }
        }
        if (*c == 'B') {
            if (extra_space > 0) {
                extra_space --;
            } else if (cursor != line->begin()) {
                cursor --;
                cursor = line->erase(cursor);
            } else if (line != text->begin()) {
                list <list <char> > :: iterator previous_line = line;
                line --;
                int current_position = line->size();
                line->insert(line->end(), previous_line->begin(), previous_line->end());
                text->erase(previous_line);
                cursor = line->begin();
                while (current_position > 0) {
                    current_position --;
                    cursor ++;
                }
            }
        }
        if (*c == 'Q') {
            break;
        }
    }
    for (list <list <char> > :: iterator line = text->begin(); line != text->end(); ++ line) {
        if (line != text->begin()) {
            puts("");
        }
        for (list <char> :: iterator cursor = line->begin(); cursor != line->end(); ++ cursor) {
            printf("%c", *cursor);
        }
    }
    return 0;
}
