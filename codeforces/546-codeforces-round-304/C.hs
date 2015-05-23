import Debug.Trace

type Input  = (Int, [Int], [Int])
type Output = Maybe (Int, Int)

main :: IO ()
main = putStrLn . format . solve . parse =<< getContents

parse :: String -> Input
parse content =
    let [n, a, b] = lines content
        readLine  = map read . tail . words
    in  (read n, readLine a, readLine b)

data Queue = Queue ([Int], [Int])

empty :: Queue -> Bool
empty (Queue ([], _)) = True
empty otherwise       = False

balance :: ([Int], [Int]) -> Queue
balance ([], c) =
    let n      = length c
        (a, b) = splitAt (n `div` 2) c
    in Queue (reverse b, a)
balance queue = Queue queue

push :: Queue -> Int -> Queue
push (Queue (a, b)) k = balance (a, k : b)

pop :: Queue -> (Int, Queue)
pop (Queue (a : as, b)) = (a, balance (as, b))

fromList :: [Int] -> Queue
fromList a = Queue (a, [])

solve :: Input -> Output
solve (n, a, b) =
    let bound = 1000
        simulate step a b
            | step > bound = Nothing
            | empty a      = Just (step, 2)
            | empty b      = Just (step, 1)
            | otherwise    =
                let (x, as) = pop a
                    (y, bs) = pop b
                in if x > y
                   then simulate (step + 1) (as `push` y `push` x) bs
                   else simulate (step + 1) as (bs `push` x `push` y)
    in simulate 0 (fromList a) (fromList b)

format :: Output -> String
format Nothing       = "-1"
format (Just (a, b)) = unwords . map show $ [a, b]
