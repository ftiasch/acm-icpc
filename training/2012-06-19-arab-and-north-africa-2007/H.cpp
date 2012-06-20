// Problem H -- The Writers' Club
#include <cstdio>
#include <cstring>
#include <string>
#include <vector>
#include <algorithm>
using namespace std;

const int BUFFER_LENGTH = 2000000;

char buffer[BUFFER_LENGTH];

const int N = 111;
const int M = 111111;

int user_count, writer_count, writer_map[M], writers_id[N], mark[M];
string writers_name[N];
vector <string> readers_name[N];
vector <int> readers_id[N];
vector <string> users_name;
bool graph[N][N];

int main() {
    int test_count = 0;
    while (true) {
        scanf("%d%d", &user_count, &writer_count);
        if (user_count == 0 && writer_count == 0) {
            break;
        }
        fgets(buffer, BUFFER_LENGTH, stdin);
        users_name.clear();
        for (int i = 0; i < writer_count; ++ i) {
            readers_name[i].clear();
            fgets(buffer, BUFFER_LENGTH, stdin);
            int length = strlen(buffer) - 1;
            bool first = true;
            for (int lower = 0; lower < length; ++ lower) {
                if (buffer[lower] != ' ') {
                    int upper = lower;
                    while (upper < length && buffer[upper] != ' ') {
                        upper ++;
                    }
                    string name(buffer + lower, upper - lower);
                    users_name.push_back(name);
//printf("-- %s\n", name.c_str());
                    if (first) {
                        first = false;
                        writers_name[i] = name;
                    } 
                    readers_name[i].push_back(name);
                    lower = upper;
                }
            }
        }
        sort(users_name.begin(), users_name.end());
        users_name.erase(unique(users_name.begin(), users_name.end()), users_name.end());
        for (int i = 0; i < user_count; ++ i) {
            writer_map[i] = -1;
        }
        for (int i = 0; i < writer_count; ++ i) {
            writers_id[i] = lower_bound(users_name.begin(), users_name.end(), writers_name[i]) - users_name.begin();
//printf(">> %d\n", writers_id[i]);
            writer_map[writers_id[i]] = i;
        }
        memset(graph, 0, sizeof(graph));
        for (int i = 0; i < writer_count; ++ i) {
            readers_id[i].clear();
            for (vector <string> :: iterator iter = readers_name[i].begin(); iter != readers_name[i].end(); ++ iter) {
                readers_id[i].push_back(lower_bound(users_name.begin(), users_name.end(), *iter) - users_name.begin());
            }
            for (vector <int> :: iterator iter = readers_id[i].begin(); iter != readers_id[i].end(); ++ iter) {
                if (writer_map[*iter] != -1) {
                    graph[i][writer_map[*iter]] = true;
                }
            }
        }
        for (int k = 0; k < writer_count; ++ k) {
            for (int i = 0; i < writer_count; ++ i) {
                for (int j = 0; j < writer_count; ++ j) {
                    graph[i][j] |= graph[i][k] && graph[k][j];
                }
            }
        }
//for (int i = 0; i < user_count; ++ i) {
//    printf("%d: %s\n", i, users_name[i].c_str());
//}
//for (int i = 0; i < writer_count; ++ i) {
//    for (vector <int> :: iterator iter = readers_id[i].begin(); iter != readers_id[i].end(); ++ iter) {
//        printf("%d ", *iter);
//    }
//    puts("");
//}
        for (int i = 0; i < user_count; ++ i) {
            mark[i] = -1;
        }
        printf("--- CASE %d\n", ++ test_count);
        for (int k = 0; k < user_count; ++ k) {
            if (writer_map[k] != -1) {
                int i = writer_map[k];
                for (int j = 0; j < writer_count; ++ j) {
                    if (graph[i][j]) {
                        for (vector <int> :: iterator iter = readers_id[j].begin(); iter != readers_id[j].end(); ++ iter) {
                            mark[*iter] = i;
                        }
                    }
                }
                for (vector <int> :: iterator iter = readers_id[i].begin(); iter != readers_id[i].end(); ++ iter) {
                    mark[*iter] = -1;
                }
                int size = 0;
                for (int j = 0; j < user_count; ++ j) {
                    if (mark[j] == i) {
                        size ++;
                    }
                }
//printf("%s", writers_name[i].c_str());
                if (size > 0) {
                    printf("%s", writers_name[i].c_str());
                    for (int j = 0; j < user_count; ++ j) {
                        if (mark[j] == i ) {
                            printf(" %s", users_name[j].c_str());
                        }
                    }
                    puts("");
                }
            }
        }
    }
    return 0;
}
