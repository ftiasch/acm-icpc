-- Codeforces Round #143
-- Problem A -- Team
main = interact work

work :: String -> String
work input = show . length . filter (\l -> sum l >= 2) . map parse . tail . lines $ input

parse :: String -> [Int]
parse line = map (\i -> read i :: Int) . words $ line
