#include <iostream>
#include <string>

int main()
{
    std::string a;
    std::cin >> a;
    int n = a.size();
    int i = 0;
    while (i < n && a[i] == 'a') {
        i ++;
    }
    if (i == n) {
        // aaa..a
        a[n - 1] = 'z';
    } else {
        int j = i;
        while (j < n && a[j] != 'a') {
            a[j] --;
            j ++;
        }
    }
    std::cout << a << std::endl;
}
