-- Codeforces Beta Round #29 
-- Problem B -- Traffic Lights
main :: IO()
main = interact work

work :: String -> String
work input = show (solve l d v g r)
    where [l, d, v, g, r] = map (\i -> read i :: Int) . words $ input

solve :: Int -> Int -> Int -> Int -> Int -> Double
solve l d v g r = total + penalty
    where total   = fromIntegral l / fromIntegral v
          penalty = if left < v * g
                    then 0
                    else fromIntegral (g + r) - fromIntegral left / fromIntegral v
          left    = d `mod` (v * (g + r))
