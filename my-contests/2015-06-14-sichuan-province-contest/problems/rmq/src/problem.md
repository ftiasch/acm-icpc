# Range Query

frog has a permutation $p(1), p(2), \dots, p(n)$ of $\{1, 2, \dots, n\}$.
She also has $m_1 + m_2$ records $(a_i, b_i, c_i)$ of the permutation.

- For $1 \leq i \leq m_1$, $(a_i, b_i, c_i)$ means $\min\{p(a_i), p(a_i + 1), \dots, p(b_i)\} = c_i$;
- For $m_1 < i \leq m_1 + m_2$, $(a_i, b_i, c_i)$ means $\max\{p(a_i), p(a_i + 1), \dots, p(b_i)\} = c_i$.

Find a permutation which is consistent with above records,
or report the records are self-contradictory.
If there are more than one valid permutations, find the lexicographically least one.

Permutation $p(1), p(2), \dots, p(n)$ is lexicographically smaller than $q(1), q(2), \dots, q(n)$ if and only if
there exists $1 \leq i \leq n$ which $p(i) < q(i)$ and
for all $1 \leq j < i$, $p(j) = q(j)$.

## Input

The input consists of multiple tests. For each test:

The first line contains $3$ integers $n, m_1, m_2$
($1 \leq n \leq 50, 0 \leq m_1 + m_2 \leq 50$).
Each of the following $(m_1 + m_2)$ lines contains $3$ integers $a_i, b_i, c_i$
($1 \leq a_i \leq b_i \leq n, 1 \leq c_i \leq n$).

## Output

For each test, write $n$ integers $p(1), p(2), \dots, p(n)$ which denote the lexicographically least permutation,
or ```-1`'' if records are self-contradictory.
