import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
    public void run() {
        try {
            int n = reader.nextInt();
            List <Integer> values = new ArrayList <Integer>();
            int[] a = new int[n];
            for (int i = 0; i < n; ++ i) {
                a[i] = reader.nextInt();
                values.add(a[i]);
            }
            int q = reader.nextInt();
            Query[] queries = new Query[q];
            for (int i = 0; i < q; ++ i) {
                int t = reader.nextInt();
                int x = reader.nextInt();
                int y = reader.nextInt();
                queries[i] = new Query(t, x, y);
                if (t == 1) {
                    values.add(x);
                }
            }
            values = unique(values);
            int m = values.size();
            Map <Integer, Integer> newValues = new HashMap <Integer, Integer>();
            for (int i = 0; i < m; ++ i) {
                newValues.put(values.get(i), i);
            }
            int size = Math.max((int)Math.sqrt(n * Math.log(n)), 1);
            int block = (n + size - 1) / size;
            int[][] count = new int[block + 1][m];
            for (int i = 0; i < n; ++ i) {
                if (i % size == 0) {
                    for (int j = 0; j < m; ++ j) {
                        count[i / size + 1][j] += count[i / size][j];
                    }
                }
                a[i] = newValues.get(a[i]);
                count[i / size + 1][a[i]] ++;
            }
            int[][] stats = new int[block + 1][n + 1];
            for (int i = 1; i <= block; ++ i) {
                for (int j = 0; j < m; ++ j) {
                    add(stats[i], count[i][j], 1);
                }
            }
            for (Query query : queries) {
                int k = query.index;
                int id = k / size;
                if (query.type == 2) {
                    if (query.value == 0) {
                        writer.println(values.get(a[k]));
                    } else {
                        int rank = 0;
                        rank += count[id][a[k]];
                        for (int i = k / size * size; i <= k; ++ i) {
                            if (a[i] == a[k]) {
                                rank ++;
                            }
                        }
                        if (query.value % 2 == 1) {
                            writer.println(rank);
                        } else {
                            int result = 0;
                            for (int i = rank; i <= n; i += ~i & i + 1) {
                                result += stats[id][i];
                            }
                            for (int i = k / size * size; i <= k; ++ i) {
                                count[id][a[i]] ++;
                                if (count[id][a[i]] == rank) {
                                    result ++;
                                }
                            }
                            for (int i = k / size * size; i <= k; ++ i) {
                                count[id][a[i]] --;
                            }
                            writer.println(result);
                        }
                    }
                } else {
                    int newA = newValues.get(query.value);
                    for (int i = id + 1; i <= block; ++ i) {
                        add(stats[i], count[i][a[k]], -1);
                        count[i][a[k]] --;
                        add(stats[i], count[i][a[k]], +1);
                        add(stats[i], count[i][newA], -1);
                        count[i][newA] ++;
                        add(stats[i], count[i][newA], +1);
                    }
                    a[k] = newA;
                }
            }
        } catch (IOException ex) {
        }
        writer.close();
    }

    void add(int[] stat, int k, int v) {
        for (; k >= 0; k -= ~k & k + 1) {
            stat[k] += v;
        }
    }

    List <Integer> unique(List <Integer> array) {
        Set <Integer> set = new HashSet <Integer>();
        set.addAll(array);
        array = new ArrayList <Integer>();
        array.addAll(set);
        return array;
    }

    class Query {
        Query(int t, int x, int y) {
            this.type = t;
            this.value = x - (t - 1);
            this.index = y - 1;
        }

        int type, value, index;
    }

    Main() {
        reader = new InputReader(System.in);
        writer = new PrintWriter(System.out);
    }

    public static void main(String[] args) {
        new Main().run();
    }

    private void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

    private InputReader reader;
    private PrintWriter writer;
}

class InputReader {
    InputReader(InputStream in) {
        reader    = new BufferedReader(new InputStreamReader(in));
        //try {
        //    reader    = new BufferedReader(new FileReader("E.in"));
        //} catch (FileNotFoundException ex) {
        //}
        tokenizer = new StringTokenizer("");
    }

    private String next() throws IOException {
        while (!tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }

    public Integer nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    private BufferedReader  reader;
    private StringTokenizer tokenizer;
}
