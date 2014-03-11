import Data.List

modulo = 10 ^ 9

data Residue = Residue Int
type State   = (Int, Int, Int, Int)

instance Show Residue where
    show (Residue r) = show r

(<+>) (Residue a) (Residue b) = Residue ((a + b) `mod` modulo)
(<*>) (Residue a) (Residue b) = Residue ((a * b) `mod` modulo)

(~*~) :: [[Residue]] -> [[Residue]] -> [[Residue]]
(~*~) a b' =
    let b = transpose b'
        in  [[x `dot` y | y <- b] | x <- a]

dot :: [Residue] -> [Residue] -> Residue
dot [] [] = Residue 0
dot (x:xs) (y:ys) = (x <*> y) <+> (dot xs ys)

identity :: [[Residue]] -> [[Residue]]
identity a = [[Residue (if i == j then 1 else 0) | j <- [1..n]] | i <- [1..n]]
              where n = length a

(~^~) :: [[Residue]] -> Int -> [[Residue]]
(~^~) a n = iter (identity a) a n
            where iter r a 0 = r
                  iter r a n = let a' = a ~*~ a
                               in iter (if odd n then r ~*~ a else r) a' (n `div` 2)

binom :: Int -> Int -> Int
binom n k = iter n k
            where iter n 0 = 1
                  iter n k = iter (n - 1) (k - 1) * n `div` k

solve :: Int -> Int -> Residue
solve m' n' = let m       = m' * 2
                  n       = n' - 1
                  states  = [(x, y, m - x - y, c) | x <- [0..m], y <- [0..m], c <- [0..1], x + y <= m]
                  a       = [[transWays x y | y <- states] | x <- states] ~^~ n
                  index x = case elemIndex x states of
                                Just i  -> i
                                Nothing -> error "Invalid states"
                  get x y = a !! (index x) !! (index y)
             in (get (0, 0, m, 0) (0, 0, m, 1)) <+> (get (0, 0, m, 1) (0, 0, m, 1))

transWays :: State -> State -> Residue
transWays (x, y, z, c) (x', y', z', c') = Residue ways
    where ways = if y >= x' && z >= y' && c + (if z' == 0 then 1 else 0) == c'
                 then binom y x' * binom z y' `mod` modulo
                 else 0

main = do
    print $ solve 1 3
    print $ solve 1 4
    print $ solve 1 5
    print $ solve 2 3
    print $ solve 2 100
    --print $ solve 10 (10 ^ 12)
