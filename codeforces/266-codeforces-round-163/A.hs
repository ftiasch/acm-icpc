-- Codeforces Round #163
-- Problem A -- Stones on the Table
main :: IO()
main = 
    getLine >>
    getLine >>= \s -> 
    print $ length . filter id $ zipWith (==) s (tail s)
