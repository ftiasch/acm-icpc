// Problem D -- A Tale from the Dark Side of the Moon
#include <cstdio>
#include <cstring>
#include <string>
#include <iostream>
using namespace std;

string text;

int main() {
    string buffer;
    while (getline(cin, buffer)) {
        text += buffer + "\n";
    }
    int length = -1;
    for (int i = 0; i < (int)text.size(); ++ i) {
        if (text.substr(i, 3) == "EOF") {
            length = i;
            break;
        }
    }
    for (int i = 0; i < length; ++ i) {
        if (text[i] == '\n' || text[i] == ' ') {
            printf("%c", text[i]);
        } else if (islower(text[i])) {
            if (text.substr(i, 2) == "dd") {
                printf("p");
                i ++;
            } else if (text.substr(i, 4) == "pink") {
                printf("floyd");
                i += 3;
            } else if (text.substr(i, 3) == "cei") {
                printf("cei");
                i += 2;
            } else if (text.substr(i, 2) == "ei") {
                printf("ie");
                i ++;
            } else {
                printf("%c", text[i]);
            }
        }
    }
    if (length == 0 || text[length - 1] != '\n') {
        puts("");
    }
    return 0;
}
