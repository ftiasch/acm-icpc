main :: IO ()
main = print . solve . read =<< getContents

digits :: Integer -> Integer
digits = fromIntegral . length . show

solve :: Integer -> Integer
solve n = n * l - sum [10 ^ i - 1 | i <- [1..l - 1]]
          where l = digits n
