// SGU 161 -- Intuitionistic Logic
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

class Node {
    static int[] constants;
    static int[][][] operations;

    int type;
    Node left, right;
    
    Node(int type, Node left, Node right) {
        this.type = type;
        this.left = left;
        this.right = right;
    }

    final static String OPERATORS = "~&|>=";

    static int getPriority(char operator) {
        return OPERATORS.indexOf(operator);
    }

    static Node construct(String expression) {
//System.out.println(expression);
        if (expression.length() == 1) {
            char token = expression.charAt(0);
            if ('0' <= token && token <= '1') {
                return new Node(5 + token - '0', null, null);
            }
            return new Node(7 + token - 'A', null, null);
        }
        int bracketCount = 0;
        ArrayList <Integer> operators = new ArrayList <Integer>();
        ArrayList <Integer> equalsSign = new ArrayList <Integer>();
        int maximumPriority = -1;
        for (int i = 0; i < expression.length(); ++ i) {
            char token = expression.charAt(i);
            if (token == '(') {
                bracketCount ++;
            }
            if (token == ')') {
                bracketCount --;
            }
            if (bracketCount == 0) {
                if (token == '=') {
                    equalsSign.add(i);
                }
                if (token == '~' || token == '&' || token == '|' || token == '>' || token == '=') {
                    operators.add(i);
                    maximumPriority = Math.max(maximumPriority, getPriority(token));
                }
            }
        }
        if (maximumPriority == -1) {
            return construct(expression.substring(1, expression.length() - 1));
        }
        if (maximumPriority == 0) {
            return new Node(0, null, construct(expression.substring(1)));
        }
        int index = -1;
        if (maximumPriority == 1 || maximumPriority == 2) {
            for (int i = operators.size() - 1; i >= 0; -- i) {
                if (getPriority(expression.charAt(operators.get(i))) == maximumPriority) {
                    index = operators.get(i);
                    break;
                }
            }
        }
        if (maximumPriority == 3) {
            for (int i = 0; i < operators.size(); ++ i) {
                if (expression.charAt(operators.get(i)) == '>') {
                    index = operators.get(i);
                    break;
                }
            }
        }
        if (maximumPriority == 4 && equalsSign.size() == 1) {
            index = equalsSign.get(0);
        }
        if (index != -1) {
            return new Node(maximumPriority, construct(expression.substring(0, index)), construct(expression.substring(index + 1)));
        }
        return new Node(1, construct(expression.substring(0, equalsSign.get(1))), construct(expression.substring(equalsSign.get(0) + 1)));
    }

    int evaluate(int[] values) {
        if (0 <= type && type < 5) {
            if (type == 0) {
                return operations[0][0][right.evaluate(values)];
            }
            return operations[type][left.evaluate(values)][right.evaluate(values)];
        } 
        if (5 <= type && type < 7) {
            return constants[type - 5];
        }
        return values[type - 7];
    }
}

class Solver {
    void search(ArrayList <ArrayList <Integer> > maximalSets, boolean[][] graph, boolean[] in, int i) {
        int n = graph.length;
        if (i < n) {
            search(maximalSets, graph, in, i + 1);
            boolean check = true;
            for (int j = 0; j < i && check; ++ j) {
                if (in[j] && (graph[i][j] || graph[j][i])) {
                    check = false;
                }
            }
            if (check) {
                in[i] = true;
                search(maximalSets, graph, in, i + 1);
                in[i] = false;
            }
        } else {
            ArrayList <Integer> maximalSet = new ArrayList <Integer>();
            for (int k = 0; k < n; ++ k) {
                if (in[k]) {
                    maximalSet.add(k);
                }
            }
            maximalSets.add(maximalSet);
        }
    }

    boolean check(Node root, boolean[] hasVariable, int maximalSetCount, int[] values, int i) {
        if (i < 26) {
            int upperBound = hasVariable[i]? maximalSetCount: 1;
            for (int value = 0; value < upperBound; ++ value) {
                values[i] = value;
                if (!check(root, hasVariable, maximalSetCount, values, i + 1)) {
                    return false;
                }
            }
            return true;
        } else {
            return root.evaluate(values) == Node.constants[1];
        }
    }

    ArrayList <Integer> maximize(boolean[][] graph, ArrayList <Integer> set) {
        ArrayList <Integer> result = new ArrayList <Integer>();
        for (int x : set) {
            boolean check = true;
            for (int y : set) {
                if (x != y && graph[x][y]) {
                    check = false;
                }
            }
            if (check) {
                result.add(x);
            }
        }
        return result;
    }

    void run(InputReader in, PrintWriter out) throws IOException {
        int nodeCount = in.nextInt();
        int edgeCount = in.nextInt();
        boolean[][] graph = new boolean[nodeCount][nodeCount];
        for (int i = 0; i < nodeCount; ++ i) {
            graph[i][i] = true;
        }
        for (int i = 0; i < edgeCount; ++ i) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            graph[a][b] = true;
        }
        for (int k = 0; k < nodeCount; ++ k) {
            for (int i = 0; i < nodeCount; ++ i) {
                for (int j = 0; j < nodeCount; ++ j) {
                    graph[i][j] |= graph[i][k] && graph[k][j];
                }
            }
        }
        ArrayList <ArrayList <Integer> > maximalSets = new ArrayList <ArrayList <Integer> >();
        search(maximalSets, graph, new boolean[nodeCount], 0);
        int maximalSetCount = maximalSets.size();
        Map <ArrayList <Integer>, Integer> mapping = new HashMap <ArrayList <Integer>, Integer>();
        for (int i = 0; i < maximalSetCount; ++ i) {
            mapping.put(maximalSets.get(i), i);
        }
        ArrayList <Integer> allVertices = new ArrayList <Integer>();
        for (int i = 0; i < nodeCount; ++ i) {
            allVertices.add(i);
        }
        Node.constants = new int[2];
        Node.constants[0] = mapping.get(maximize(graph, allVertices));
        Node.constants[1] = mapping.get(new ArrayList <Integer>());
        Node.operations = new int[5][][];
        // imply
        Node.operations[3] = new int[maximalSetCount][maximalSetCount];
        for (int i = 0; i < maximalSetCount; ++ i) {
            ArrayList <Integer> a = maximalSets.get(i);
            for (int j = 0; j < maximalSetCount; ++ j) {
                ArrayList <Integer> b = maximalSets.get(j);
                ArrayList <Integer> c = new ArrayList <Integer>();
                for (int y : b) {
                    boolean check = true;
                    for (int x : a) {
                        if (graph[y][x]) {
                            check = false;
                        }
                    }
                    if (check) {
                        c.add(y);
                    }
                }
                Node.operations[3][i][j] = mapping.get(c);
            }
        }
        // not
        Node.operations[0] = new int[1][maximalSetCount];
        for (int i = 0; i < maximalSetCount; ++ i) {
            Node.operations[0][0][i] = Node.operations[3][i][Node.constants[0]];
        }
        // and
        Node.operations[1] = new int[maximalSetCount][maximalSetCount];
        for (int i = 0; i < maximalSetCount; ++ i) {
            for (int j = 0; j < maximalSetCount; ++ j) {
                Set <Integer> s = new HashSet <Integer>();
                s.addAll(maximalSets.get(i));
                s.addAll(maximalSets.get(j));
                
                ArrayList <Integer> c = new ArrayList <Integer>();
                c.addAll(s);
                Collections.sort(c);
                Node.operations[1][i][j] = mapping.get(maximize(graph, c));
            }
        }
        // or
        Node.operations[2] = new int[maximalSetCount][maximalSetCount];
        boolean[][] predecessor = new boolean[maximalSetCount][nodeCount];
        for (int i = 0; i < maximalSetCount; ++ i) {
            for (int y : maximalSets.get(i)) {
                for (int x = 0; x < nodeCount; ++ x) {
                    if (graph[x][y]) {
                        predecessor[i][x] = true;
                    }
                }
            }
        }
        for (int i = 0; i < maximalSetCount; ++ i) {
            for (int j = 0; j < maximalSetCount; ++ j) {
                ArrayList <Integer> c = new ArrayList <Integer>();
                for (int k = 0; k < nodeCount; ++ k) {
                    if (predecessor[i][k] && predecessor[j][k]) {
                        c.add(k);
                    }
                }
                Node.operations[2][i][j] = mapping.get(maximize(graph, c));
            }
        }
        // equal
        Node.operations[4] = new int[maximalSetCount][maximalSetCount];
        for (int i = 0; i < maximalSetCount; ++ i) {
            for (int j = 0; j < maximalSetCount; ++ j) {
                Node.operations[4][i][j] = Node.operations[1][Node.operations[3][i][j]][Node.operations[3][j][i]];
            }
        }
        int queryCount = in.nextInt();
        while (queryCount > 0) {
            queryCount --;
            String expression = in.nextLine();
            expression = expression.replaceAll(" ", "");
            expression = expression.replaceAll("=>", ">");
            boolean[] hasVariable = new boolean[26];
            for (int i = 0; i < expression.length(); ++ i) {
                char token = expression.charAt(i);
                if ('A' <= token && token <= 'Z') {
                    hasVariable[token - 'A'] = true;
                }
            }
            Node root = Node.construct(expression);
            out.println(check(root, hasVariable, maximalSetCount, new int[26], 0)? "valid": "invalid");
out.flush();
        }
        out.flush();
    }
}

public class Solution {
    public static void main(String args[]) {
        try {
            new Solver().run(new InputReader(System.in), new PrintWriter(System.out));
        } catch (IOException e) {
        }
    }
}

class InputReader {
    BufferedReader reader;
    StringTokenizer tokenizer;

    InputReader(InputStream in) {
        reader = new BufferedReader(new InputStreamReader(in));
        tokenizer = new StringTokenizer("");
    }

    String nextLine() throws IOException {
        return reader.readLine();
    }

    String nextToken() throws IOException {
        while (!tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }

    int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }
}
