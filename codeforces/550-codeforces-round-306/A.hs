import Data.List (isPrefixOf)

check :: String -> Bool
check s = recur ["AB", "BA"] s || recur ["BA", "AB"] s
          where recur [] _  = True
                recur _  [] = False
                recur (p : ps) s
                    | p `isPrefixOf` s = recur      ps  (drop (length p) s)
                    | otherwise        = recur (p : ps) (tail s)

format :: Bool -> String
format False = "NO"
format True  = "YES"

main :: IO ()
main = putStrLn . format . check =<< getLine
