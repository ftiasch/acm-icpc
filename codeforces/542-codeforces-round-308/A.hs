main :: IO ()
main = print . solve . parse =<< getContents

parse = map parseLine . tail . lines
        where parseLine l = let [x1, y1, x2, y2] = map read . words $ l
                            in  (x1, y1, x2, y2)

solve = sum . map area
        where area (x1, y1, x2, y2) = (x2 - x1 + 1) * (y2 - y1 + 1)
