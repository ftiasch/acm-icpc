{-# LANGUAGE FunctionalDependencies, FlexibleInstances #-}

import Control.Applicative
import Control.Arrow
import Control.Monad
import Data.Char
import Data.List

type Input  = ([[Int]], [Int])
type Output = [Int]

parse :: [String] -> Input
parse = evalState $ do
        n <- read   <$> readLine
        g <- parseG <$> replicateM n readLine
        d <- parseD <$> readLine
        return (g, d)
        where readLine = state (head &&& tail)
              parseG   = map (map (\c -> ord c - 48))
              parseD   = map read . words

solve :: Input -> Output
solve (g, d) = sort (foo d)
               where foo a = case elemIndex 0 a of
                                 Nothing -> []
                                 Just i  -> i : foo (zipWith (-) a (g !! i))

pprint :: Output -> IO ()
pprint a = putStr (unlines ls)
           where ls = [show (length a), unwords (map (show . (+1)) a)]

main :: IO ()
main = pprint . solve . parse . lines =<< getContents

-- {{{ A minimal State Monad
class (Monad m) => MonadState s m | m -> s where
    get :: m s
    put :: s -> m ()

-- modify :: (MonadState s m) => (s -> s) -> m ()
-- modify f = do
--  s <- get
--  put (f s)
--
-- gets :: (MonadState s m) => (s -> a) -> m a
-- gets f = do
--  s <- get
--  return (f s)

newtype State s a = State { runState :: s -> (a, s) }

instance Functor (State s) where
    fmap f m = State $ \s -> let
        (a, s') = runState m s
        in (f a, s')

instance Applicative (State s) where
    pure = return
    (<*>) = ap

instance Monad (State s) where
    return a = State $ \s -> (a, s)
    m >>= k  = State $ \s -> let
        (a, s') = runState m s
        in runState (k a) s'

instance MonadState s (State s) where
    get   = State $ \s -> (s, s)
    put s = State $ const ((), s)

evalState :: State s a -> s -> a
evalState m s = fst (runState m s)

-- execState :: State s a -> s -> s
-- execState m s = snd (runState m s)
--
-- mapState :: ((a, s) -> (b, s)) -> State s a -> State s b
-- mapState f m = State $ f . runState m
--
-- withState :: (s -> s) -> State s a -> State s a
-- withState f m = State $ runState m . f

state :: (s -> (a, s)) -> State s a
state = State
-- }}}
