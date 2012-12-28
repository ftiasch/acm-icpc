// Codeforces Round #158
// Problem B -- Ancient Prophesy
#include <cctype>
#include <cstdio>
#include <cstring>
#include <map>
#include <utility>
#include <vector>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

const int N = 100000;
const int DAY[13] = {-1, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

char s[N + 1];

int parse(int l, int r) {
    for (int i = l; i < r; ++ i) {
        if (!isdigit(s[i])) {
            return -1;
        }
    }
    char backup = s[r];
    s[r] = '\0';
    int ret;
    sscanf(s + l, "%d", &ret);
    s[r] = backup;
    return ret;
}

struct Date {
    int year, month, day;

    Date(int year, int month, int day) : year(year), month(month), day(day) {}
};

bool operator <(const Date &a, const Date &b) {
    if (a.year != b.year) {
        return a.year < b.year;
    }
    if (a.month != b.month) {
        return a.month < b.month;
    }
    return a.day < b.day;
}

int main() {
    scanf("%s", s);
    int n = strlen(s);
    std::map <Date, int> count;
    for (int i = 0; i + 10 <= n; ++ i) {
        if (s[i + 2] == '-' && s[i + 5] == '-') {
            int day = parse(i, i + 2);
            int month = parse(i + 3, i + 5);
            int year = parse(i + 6, i + 10);
            if (2013 <= year && year <= 2015 && 1 <= month && month <= 12 && 1 <= day && day <= DAY[month]) {
                count[Date(year, month, day)] ++;
            }
        }
    }
    std::vector <std::pair <int, Date> > dates;
    foreach (iter, count) {
        dates.push_back(std::make_pair(iter->second, iter->first));
    }
    std::sort(dates.begin(), dates.end());
    Date result = dates.back().second;
    printf("%02d-%02d-%d\n", result.day, result.month, result.year);
    return 0;
}
