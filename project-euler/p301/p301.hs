-- Project Euler 301 Nim
solve n = iter 1 2 n
    where iter a b n
            | n == 0    = a
            | otherwise = iter b (a + b) (n - 1)

main = print $ solve 30
