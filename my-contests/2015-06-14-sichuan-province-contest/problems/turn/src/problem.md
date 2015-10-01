# Right turn

frog is trapped in a maze.
The maze is infinitely large and divided into grids.
It also consists of $n$ obstacles,
where the $i$-th obstacle lies in grid $(x_i, y_i)$.

frog is initially in grid $(0, 0)$, heading grid $(1, 0)$.
She moves according to *The Law of Right Turn*:
she keeps moving forward, and turns right encountering a obstacle.

The maze is so large that frog has no chance to escape.
Help her find out the number of turns she will make.

## Input

The input consists of multiple tests. For each test:

The first line contains $1$ integer $n$
($0 \leq n \leq 10^3$).
Each of the following $n$ lines contains $2$ integers $x_i, y_i$.
($|x_i|, |y_i| \leq 10^9, (x_i, y_i) \neq (0, 0)$, all $(x_i, y_i)$ are distinct)

## Output

For each test, write $1$ integer which denotes the number of turns,
or ```-1`'' if she makes infinite turns.
