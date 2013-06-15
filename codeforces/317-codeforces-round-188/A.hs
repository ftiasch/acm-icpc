-- Codeforces Round #188
-- Problem A -- Ants
main :: IO()
main = interact mainFunction

mainFunction :: String -> String
mainFunction input = show $ solve x y m
    where [x, y, m] = map parseInt . words $ input

parseInt :: String -> Integer
parseInt = read

solve :: Integer -> Integer -> Integer -> Integer
solve x y m = if x >= m || y >= m
              then 0
              else if x <= 0 && y <= 0
                   then -1
                   else if x > y
                        then solve y x m
                        else let s = (y - x + y) `div` y
                             in solve (x + s * y) y m + s
