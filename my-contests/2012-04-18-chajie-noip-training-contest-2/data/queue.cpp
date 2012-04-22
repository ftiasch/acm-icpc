#include <cstdio>
#include <cstring>
#include <deque>
#include <algorithm>
using namespace std;

struct FancyArray {
    deque <int> array;
    deque <deque <int> :: iterator> stack;

    void push_front(int k) {
        array.push_front(k);
        while ((int)stack.size() > 0 && k < *stack.front()) {
            stack.pop_front();
        }
        stack.push_front(array.begin());
    }

    void push_back(int k) {
        array.push_back(k);
        if ((int)stack.size() == 0 || *stack.back() > k) {
            stack.push_back(array.end() - 1);
        }
    }

    int pop_back() {
        deque <int> :: iterator it = array.end() - 1;
        if ((int)stack.size() > 0 && stack.back() == it) {
            stack.pop_back();
        }
        int result = *it;
        array.pop_back();
        return result;
    }
    
    int get_kth(int k) {
        if (k > (int)stack.size()) {
            return 1;
        }
        return *stack[stack.size() - k];
    }
};

FancyArray array;

int main() {
    int m;
    scanf("%d", &m);
    while (m > 0) {
        m --;
        int type;
        scanf("%d", &type);
        if (type == 1) {
            int element;
            scanf("%d", &element);
            array.push_back(-element);
        } else if (type == 2) {
            int element;
            scanf("%d", &element);
            array.push_front(-element);
        } else if (type == 3) {
            array.pop_back();
        } else {
            int rank;
            scanf("%d", &rank);
            printf("%d\n", -array.get_kth(rank));
        }
    }
    return 0;
}
