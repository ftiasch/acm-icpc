# Party

$n$ frogs are invited to a tea party.
Frogs are conveniently numbered by $1, 2, \dots, n$.

The tea party has black and green tea in service.
Each frog has its own preference.
He or she may drink only black/green tea or accept both.

There are $m$ pairs of frogs who dislike each other.
They fight when they are serving the same type of tea.

Luckily, frogs can be divided into $2$ groups
such that no two frogs in the same group dislike each other.

Frogs like gems.
If the $i$-th frog can be paid $w_i$ gems instead of serving tea,
it will not fight with others anymore.

The party manager has to dicide how to serve tea/pay gems to avoid fights,
minimizing the total gems paid.

## Input

The input consists of multiple tests. For each test:

The first line contains $2$ integers $n, m$
($1 \leq n \leq 10^3, 0 \leq m \leq 10^4$).
The second line contains $n$ integers $w_1, w_2, \dots, w_n$.
($1 \leq w_i \leq 10^6$).

The third line contains $n$ integers $p_1, p_2, \dots, p_n$.
($1 \leq p_i \leq 3$).
$p_i = 1$ means the $i$-th frog drinks only black tea.
$p_i = 2$ means it drinks only green one,
while $p_i = 3$ means it accepts both.

Each of the following $m$ lines contains $2$ integers $a_i, b_i$,
which denotes frog $a_i$ and $b_i$ dislike each other.
($1 \leq a_i, b_i \leq n$)

## Output

For each test, write $1$ integer which denotes the minimum total gems paid.
