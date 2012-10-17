-- Codeforces Round #111
-- Problem B -- Unlucky Ticket
import Data.List

main = interact work

work :: String -> String
work input = if check first second || check second first then "YES" else "NO"
    where n = read . head . lines $ input :: Int
          ticket = last . lines $ input
          first = take n ticket
          second = drop n ticket
          
check :: String -> String -> Bool
check a b = and $ zipWith (<) (sort a) (sort b)
