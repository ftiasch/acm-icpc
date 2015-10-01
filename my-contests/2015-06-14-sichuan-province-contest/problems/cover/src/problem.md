# Vertex Cover


frog has a graph with $n$ vertices $v(1), v(2), \dots, v(n)$
and $m$ edges $(v(a_1), v(b_1)), (v(a_2), v(b_2)), \dots, (v(a_m), v(b_m))$.

She would like to color some vertices
so that each edge has at least one colored vertex.

Find the minimum number of colored vertices.

## Input

The input consists of multiple tests. For each test:

The first line contains $2$ integers $n, m$
($2 \leq n \leq 500, 1 \leq m \leq \frac{n(n - 1)}{2}$).
Each of the following $m$ lines contains $2$ integers $a_i, b_i$
($1 \leq a_i, b_i \leq n, a_i \neq b_i, \min\{a_i, b_i\} \leq 30$)

## Output

For each test, write $1$ integer which denotes the minimum number of colored vertices.
