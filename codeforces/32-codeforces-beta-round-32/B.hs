-- Codeforces Beta Round #32
-- Problem B -- Borze
main :: IO()
main = putStrLn . parse =<< getLine

parse :: String -> String
parse "" = ""
parse "." = "0"
parse (a:b:s) = if a == '.'
                then '0' : parse (b:s)
                else (if b == '.'
                     then '1'
                     else '2') : parse s
