-- Codeforces Round #108
-- Problem C -- Pocket Book
import Data.List

main :: IO()
main = interact work

work :: String -> String
work = show . solve . tail . lines 

solve :: [String] -> Integer
solve strings = (product . map (fromIntegral . length . group . sort) . transpose $ strings) `mod` 1000000007
