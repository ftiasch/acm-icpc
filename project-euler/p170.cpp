#include <cassert>
#include <cstdio>
#include <iostream>

using LL = long long;

LL  inputs[10], result;

auto dfs(int input_mask, int input_count, LL buffer, int buffer_length, LL output, int output_length)
{
    if (input_count + !!buffer_length + (10 - __builtin_popcount(input_mask)) < 3) {
        return;
    }
    if (buffer_length) {
        inputs[input_count] = buffer;
        auto new_output = output;
        auto new_output_length = output_length;
        auto valid = true;
        if (input_count) {
            auto mask = 0;
            for (auto tmp = output; tmp; tmp /= 10) {
                mask |= 1 << tmp % 10;
            }
            auto power = 1LL;
            for (auto product = inputs[0] * buffer; product && valid; product /= 10) {
                auto d = product % 10;
                if (mask >> d & 1) {
                    valid = false;
                } else {
                    mask |= 1 << d;
                    power *= 10;
                    new_output_length ++;
                }
            }
            new_output = output * power + (inputs[0] * buffer);
        }
        if (valid) {
            dfs(input_mask, input_count + 1, 0LL, 0, new_output, new_output_length);
        }
    }
    if (input_mask == 1023) {
        if (!buffer_length && output_length == 10) {
            // if (result < output) {
            //     std::cout << output << std::endl;
            //     for (int i = 0; i < input_count; ++ i) {
            //         printf("%lld,", inputs[i]);
            //     }
            //     puts("");
            // }
            result = std::max(result, output);
        }
    } else {
        for (auto d = (int)(!buffer_length); d < 10; ++ d) {
            if (~input_mask >> d & 1) {
                dfs(input_mask | 1 << d, input_count, buffer * 10 + d, buffer_length + 1, output, output_length);
            }
        }
    }
}

int main()
{
    dfs(0, 0, 0LL, 0, 0LL, 0);
    std::cout << result << std::endl;
}
