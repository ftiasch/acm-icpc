// SGU 307 -- Cipher
#include <cstdio>
#include <cstring>
#include <climits>
#include <algorithm>

#define X(i) ((i) - 1)
#define Y(i) ((i) + n - 2)
#define POINT(i, j) (((i) << 1) | (j))

using std::min;

const int N = 2222;
const int M = 2222222;

int n, m, sum[N][N], con[N][N];
int nodeCount, edgeCount, firstEdge[N], to[M], nextEdge[M];
int dfsCnt, dfn[N], low[N];
int stackSize, stack[N];
int sccCnt, scc[N], co[N], sccAns[N];

void insertEdge(int u, int v){
	to[edgeCount] = v;
	nextEdge[edgeCount] = firstEdge[u];
	firstEdge[u] = edgeCount ++;
//	printf("%d->%d\n", u, v);
}

void conflict(int a, int b){
//	printf("%d %d\n", a, b);
	insertEdge(a, b ^ 1);
	insertEdge(b, a ^ 1);
}

void dfs(int u){
	if(dfn[u] == -1){
		stack[stackSize ++] = u;
		int tmp = dfn[u] = low[u] = dfsCnt ++;
		for(int iter = firstEdge[u]; iter != -1; iter = nextEdge[iter]){
			int v = to[iter];
			dfs(v);
			tmp = min(tmp, low[v]);
		}
		low[u] = tmp;
		if(dfn[u] == low[u]){
			co[sccCnt] = u;
			do{
				scc[stack[-- stackSize]] = sccCnt;
				low[stack[stackSize]] = INT_MAX;
			}while(stack[stackSize] != u);
			sccCnt += 1;
		}
	}
}

int main(){
	scanf("%d%d", &n, &m);
	for(int i = 1; i < n; ++ i){
		char buffer[N];
		scanf("%s", buffer);
		for(int j = 1; j < m; ++ j){
			sum[i][j] = buffer[j - 1] - '0';
		}
	}
	for(int first = 0; first < 2; ++ first){
		memset(con, 0, sizeof(con));
		con[0][0] = first;
		for(int i = 1; i < n; ++ i){
			for(int j = 1; j < m; ++ j){
				con[i][j] = sum[i][j] - (con[i - 1][j] + con[i][j - 1] + con[i - 1][j - 1]);
			}
		}
		/*	
		for(int i = 1; i < n; ++ i){
			for(int j = 1; j < m; ++ j){
				printf("%d", con[i][j]);
			}
			printf("\n");
		}
		*/
		edgeCount = 0;
		memset(firstEdge, -1, sizeof(firstEdge));
		for(int i = 1; i < n; ++ i){
			for(int j = 1; j < m; ++ j){
				for(int x = 0; x < 2; ++ x){
					for(int y = 0; y < 2; ++ y){
						int tmp = con[i][j];
						tmp += (i & 1)? -y: y;
						tmp += (j & 1)? -x: x;
						if(tmp < 0 || tmp > 1){
						//	printf("%d %d %d %d\n", i, j, x, y);
							conflict(POINT(X(i), x), POINT(Y(j), y));
						}
					}
				}
			}
		}
		nodeCount = (n + m - 2) << 1;
		dfsCnt = sccCnt = stackSize = 0;
		memset(dfn, -1, sizeof(dfn));
		for(int i = 0; i < nodeCount; ++ i){
			dfs(i);
		}
		bool failed = false;
		for(int i = 0; i < nodeCount; ++ i){
			failed |= scc[i] == scc[i ^ 1];
		}
		if(failed){
			continue;
		}
		memset(sccAns, -1, sizeof(sccAns));
		for(int i = 0; i < sccCnt; ++ i){
			if(sccAns[i] == -1){
				sccAns[i] = 1;
				sccAns[scc[co[i] ^ 1]] = 0;
			}		
		}
		/*
		for(int i = 0; i < nodeCount; ++ i){
			printf("%d = %d\n", i, scc[i]);
		}
		for(int i = 0; i < sccCnt; ++ i){
			printf("%d: %d\n", i, sccAns[i]);
		}
		*/
		for(int i = 1; i < n; ++ i){
			con[i][0] = sccAns[scc[POINT(X(i), 1)]];
		}
		for(int j = 1; j < m; ++ j){
			con[0][j] = sccAns[scc[POINT(Y(j), 1)]];
		}
		for(int i = 1; i < n; ++ i){
			for(int j = 1; j < m; ++ j){
				con[i][j] = sum[i][j] - (con[i - 1][j] + con[i][j - 1] + con[i - 1][j - 1]);
			}
		}
		for(int i = 0; i < n; ++ i){	
			for(int j = 0; j < m; ++ j){
				printf("%d", con[i][j]);
			}
			printf("\n");
		}
		return 0;
	}
	printf("CORRUPT\n");
	return 0;
}
