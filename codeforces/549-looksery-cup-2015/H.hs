type Input  = (Double, Double, Double, Double)
type Output = Double

parse :: String -> Input
parse contents = (a, b, c, d) where [a, b, c, d] = map read . words $ contents

type Range = (Double, Double)

multiply :: Range -> Range -> Range
multiply (a, b) (c, d) = (minimum products, maximum products)
                         where products = do x <- [a, b]
                                             y <- [c, d]
                                             return (x * y)

isOverlap :: Range -> Range -> Bool
isOverlap (a, b) (c, d) = max a c <= min b d

check :: Input -> Double -> Bool
check (a, b, c, d) r = isOverlap (f a d) (f b c)
                       where f x y = multiply (g x) (g y)
                             g x   = (x - r, x + r)

solve :: Input -> Output
solve param@(a, b, c, d) = fst (iterate bsearch (0, bound) !! 233)
                           where bound = maximum . map abs $ [a, b, c, d]
                                 bsearch (l, r) = let mid = (l + r) / 2 in
                                                  if check param mid
                                                  then (l, mid)
                                                  else (mid, r)

main :: IO ()
main = print . solve . parse =<< getContents
