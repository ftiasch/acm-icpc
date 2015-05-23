import Debug.Trace

type Input  = (Int, [Int], [Int])
type Output = Maybe (Int, Int)

main :: IO ()
main = putStrLn . format . solve . parse =<< getContents

parse :: String -> Input
parse content = (read n, readLine a, readLine b)
    where [n, a, b] = lines content
          readLine  = map read . tail . words

data Queue a = Queue [a] [a]

empty :: Queue a -> Bool
empty (Queue [] _) = True
empty otherwise    = False

balance :: [a] -> [a] -> Queue a
balance [] b = Queue (reverse b) []
balance a  b = Queue a b

push :: Queue a -> a -> Queue a
push (Queue a b) k = balance a (k : b)

pop :: Queue a -> (a, Queue a)
pop (Queue (a : as) b) = (a, balance as b)

fromList :: [a] -> Queue a
fromList a = Queue a []

solve :: Input -> Output
solve (n, a, b) = simulate 0 (fromList a) (fromList b)
    where simulate step a b
              | step > 1000 = Nothing
              | empty a     = Just (step, 2)
              | empty b     = Just (step, 1)
              | otherwise   =
                  let (x, as)   = pop a
                      (y, bs)   = pop b
                      simulate' = simulate (step + 1)
                  in if x > y
                     then simulate' (as `push` y `push` x) bs
                     else simulate' as (bs `push` x `push` y)

format :: Output -> String
format Nothing       = "-1"
format (Just (a, b)) = unwords . map show $ [a, b]
