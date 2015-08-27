package WalkOverATree

func min(a, b int) int {
    if (a < b) {
        return a
    }
    return b
}

func max(a, b int) int {
    if (a > b) {
        return a
    }
    return b
}

func height(children [][]int, u int) (Height int) {
    Height = 0
    for _, v := range children[u] {
        Height = max(Height, 1 + height(children, v))
    }
    return
}

func MaxNodesVisited(parent []int, l int) int {
    n := len(parent) + 1
    children := make([][]int, n)
    for i := range children {
        children[i] = make([]int, 0)
    }
    for i := 1; i < n; i ++ {
        p := parent[i - 1]
        children[p] = append(children[p], i)
    }
    h := height(children, 0)
    if l <= h {
        return l + 1
    }
    return min(h + 1 + (l - h) / 2, n)
}
