# Necklace


frog has $n$ gems arranged in a cycle,
whose *beautifulness* are $a_1, a_2, \dots, a_n$.
She would like to remove some gems to make them into a *beautiful necklace*
without changing their relative order.

Note that a *beautiful necklace* can be divided into $3$ consecutive parts $X, y, Z$, where

1. $X$ consists of gems with non-decreasing *beautifulness*,
2. $y$ is **the only** *perfect gem*. (A *perfect gem* is a gem whose *beautifulness* equals to $10000$)
3. $Z$ consists of gems with non-increasing *beautifulness*.

Find out the maximum total *beautifulness* of the remaining gems.

## Input

The input consists of multiple tests. For each test:

The first line contains $1$ integer $n$
($1 \leq n \leq 10^5$).
The second line contains $n$ integers $a_1, a_2, \dots, a_n$.
($0 \leq a_i \leq 10^4$, $1 \leq \textrm{number of perfect gems} \leq 10$).

## Output

For each test, write $1$ integer which denotes the maximum total remaining *beautifulness*.
