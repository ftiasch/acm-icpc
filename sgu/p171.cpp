// SGU 171 -- Sarov zones
#include <cstdio>
#include <cstring>
#include <utility>
#include <functional>
#include <algorithm>
using namespace std;

const int M = 100;
const int N = 16000;

int n, m, zone_level[M], zone_capacity[M], student_level[N], student_weight[N], zone_order[M], student_order[N], assignment[N];

bool compare_zone(int i, int j) {
    return zone_level[i] < zone_level[j];
}

bool compare_student(int i, int j) {
    return student_weight[i] > student_weight[j];
}

int main() {
    scanf("%d", &m);
    n = 0;
    for (int i = 0; i < m; ++ i) {
        zone_order[i] = i;
        scanf("%d", zone_capacity + i);
        n += zone_capacity[i];
    }
    for (int i = 0; i < m; ++ i) {
        scanf("%d", zone_level + i);
    }
    for (int i = 0; i < n; ++ i) {
        student_order[i] = i;
        scanf("%d", student_level + i);
    }
    for (int i = 0; i < n; ++ i) {
        scanf("%d", student_weight + i);
    }
    sort(zone_order, zone_order + m, compare_zone);
    sort(student_order, student_order + n, compare_student);
    for (int i = 0; i < n; ++ i) {
        int j = m - 1;
        while (j >= 0 && (zone_capacity[zone_order[j]] == 0 || zone_level[zone_order[j]] >= student_level[student_order[i]])) {
            j --;
        }
        if (j == -1) {
            j = m - 1;
            while (zone_capacity[zone_order[j]] == 0) {
                j --;
            }
        }
        zone_capacity[zone_order[j]] --;
        assignment[student_order[i]] = zone_order[j];
    }
    for (int i = 0; i < n; ++ i) {
        printf("%d%c", assignment[i] + 1, i == n - 1? '\n': ' ');
    }
    return 0;
}
