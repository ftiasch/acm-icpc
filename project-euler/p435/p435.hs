import Control.Lens
import Linear

m :: Integer
m = product [1..15]

newtype Residue = Residue Integer
                  deriving (Eq)

instance Show Residue where
    show (Residue a) = show a

instance Num Residue where
    (+) (Residue a) (Residue b) = Residue ((a + b) `mod` m)
    (*) (Residue a) (Residue b) = Residue ((a * b) `mod` m)
    fromInteger = Residue

type Matrix = M33 Residue

power :: Matrix -> Integer -> Matrix
power a n | n == 0 = eye3
          | n > 0  = let t = power a (n `div` 2)
                         t2 = t !*! t
                     in if even n then t2
                                  else t2 !*! a

f :: Integer -> Residue -> Residue
f n x = let x0 = V3 0 x 0
            a = V3 (V3 0 (x * x) 0) (V3 1 x 1) (V3 0 0 1)
            xn = x0 *! (power a n)
        in xn ^. _z

main :: IO ()
main = do
    print $ f 7 11
    print $ sum . map (\x -> f (10 ^ 15) (Residue x)) $ [0..100]
