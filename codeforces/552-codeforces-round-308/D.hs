import Data.List (group, sort)

data Point = Point Int Int
             deriving (Show, Eq)
type Input = [Point]

parse :: String -> Input
parse = map (parsePoint . map read . words) . lines
        where parsePoint [x, y] = Point x y
              parsePoint _      = undefined

instance Ord Point where
    (Point x y) <= (Point x' y') = x < x' || x == x' && y <= y'

normalize :: Int -> Int -> Point
normalize 0 0 = Point 0 0
normalize x y
    | invalid   = normalize (-x) (-y)
    | otherwise = let g = gcd x y in Point (x `div` g) (y `div` g)
    where invalid = y < 0 || y == 0 && x < 0

pairs :: Int -> Int
pairs n = n * (n - 1) `div` 2

colinears :: [Point] -> Int
colinears = sum . map (pairs . length) . group . sort

solve' :: Point -> [Point] -> Int
solve' (Point x0 y0) ps' = pairs (n - 1) - colinears ps
                           where n  = length ps
                                 ps = map (\(Point x y) -> normalize (x - x0) (y - y0)) ps'

solve :: Input -> Integer
solve ps = sum [fromIntegral (solve' p ps) | p <- ps] `div` 3

main :: IO ()
main = do
       _ <- getLine
       print . solve . parse =<< getContents
