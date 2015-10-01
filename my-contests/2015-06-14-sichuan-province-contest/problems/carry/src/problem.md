# Carries

frog has $n$ integers $a_1, a_2, \dots, a_n$,
and she wants to add them pairwise.

Unfortunately, frog is somehow afraid of carries (进位).
She defines *hardness* $h(x, y)$ for adding $x$ and $y$
the number of carries involved in the calculation.
For example, $h(1, 9) = 1, h(1, 99) = 2$.

Find the total hardness adding $n$ integers pairwise.
In another word, find $$\sum_{1 \leq i < j \leq n} h(a_i, a_j)$$.

## Input

The input consists of multiple tests. For each test:

The first line contains $1$ integer $n$
($2 \leq n \leq 10^5$).
The second line contains $n$ integers $a_1, a_2, \dots, a_n$.
($0 \leq a_i \leq 10^9$).

## Output

For each test, write $1$ integer which denotes the total hardness.
