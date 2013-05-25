-- Codeforces Beta Round #16
-- Problem A -- Flag
import Data.List

main :: IO()
main = showBool . check . tail . lines =<< getContents

showBool :: Bool -> IO()
showBool True  = putStrLn "YES"
showBool False = putStrLn "NO"

check :: [String] -> Bool
check flag = (checkRow flag) && (checkColumn flag)

checkRow :: [String] -> Bool
checkRow = all (\x -> (length . group) x == 1)

checkColumn :: [String] -> Bool
checkColumn flag = all id . zipWith (/=) rows $ (tail rows)
    where rows = map head flag
