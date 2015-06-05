import Data.List (delete)

type Graph = [(Int, Int)]

vertices :: Graph -> Int
vertices = maximum . map (\(a, b) -> max a b)

merge :: Graph -> Graph -> Graph
merge g g' = g ++ (map (\(a, b) -> (a + n, b + n)) g')
             where n = vertices g

-- deg(v_1) = deg(v_2) = ... = deg(v_{d + 1}) = d - 1
almostClique :: Int -> Graph
almostClique d = do i <- [1..d + 1]
                    j <- delete (i + (d + 1) `div` 2) [i + 1..d + 1]
                    return (i, j)

-- deg(v_1) = d - 1, deg(v_2) = deg(v_3) = ... = deg(v_{d + 2}) = d
almostRegular :: Int -> Graph
almostRegular d = (almostClique d) ++ [(i, d + 2) | i <- [2..d + 1]]

solve :: Int -> Maybe Graph
solve d
    | even d    = Nothing
    | d == 1    = Just $ [(1, 2)]
    | otherwise = Just $ (1, 1 + n) : merge g g
    where g = almostRegular d
          n = vertices g

format :: Maybe Graph -> String
format Nothing  = "NO"
format (Just g) = unlines $ "YES" : map pp ((vertices g, length g) : g)
                  where pp (a, b) = unwords . map show $ [a, b]

main :: IO ()
main = putStrLn . format . solve . read =<< getContents
