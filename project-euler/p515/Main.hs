import Data.Array
import Debug.Trace (trace)

data Fp = F Int Int
          deriving (Show, Eq)

instance Num Fp where
    (F p a) + (F p' b)
        | p == p'   = F p ((a + b) `mod` p)
        | otherwise = error ""
    (F p a) * (F p' b)
        | p == p'   = F p ((a * b) `mod` p)
        | otherwise = error ""
    negate (F p a) = F p ((p - a) `mod` p)
    abs         = undefined
    signum      = undefined
    fromInteger = undefined

inverse :: Fp -> Fp
inverse (F p 0) = error "0 has no inverse"
inverse (F p 1) = F p 1
inverse (F p a) = negate (F p (p `div` a)) * inverse (F p (p `mod` a))

fdiv :: Fp -> Fp -> Fp
a `fdiv` b = a * (inverse b)

binom :: Int -> Int -> Int -> Fp
binom p n k = (prod [n - k + 1..n]) `fdiv` (prod [1..k])
              where prod = foldl (*) (F p 1) . map (F p)

toInt :: Fp -> Int
toInt (F _ a) = a

isPrime :: Int -> Bool
isPrime n = all (\i -> n `mod` i /= 0) . takeWhile (\i -> i * i <= n) $ [2..n]

d :: Int -> Int -> Int -> Int
d p n k = toInt $ (binom p (n + k - 1) (k - 1) - F p 1) `fdiv` negate (F p (k - 1))

dd :: Int -> Int -> Int -> Int
dd a b k = sum . map (\p -> d p (p - 1) k) . filter isPrime $ [a..a + b - 1]

main = do print $ dd 101 1 10
          print $ dd (10^3) (10^2) (10^2)
          print $ dd (10^6) (10^3) (10^3)
          print $ dd (10^9) (10^5) (10^5)
