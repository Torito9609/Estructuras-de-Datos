package disjointset;

public class DisjointSet {
    private final int[] parent;
    private final int[] rank;

    public DisjointSet(int n){
        parent = new int[n+1];
        rank = new int[n+1];

        for(int i = 1; i <=n; i++){
            makeSet(i);
        }
    }

    private void makeSet(int x){
        parent[x] = x;
        rank[x] = 0;
    }

    public int find(int x){
        if(x != parent[x]){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);

        if(rootX == rootY) return;;

        if(rank[rootX] > rank[rootY]){
            parent[rootY] = rootX;
        } else if(rank[rootX] < rank[rootY]){
            parent[rootX] = rootY;
        } else{
            parent[rootX] = rootY;
            rank[rootX]++;
        }
    }

    public boolean connected(int x, int y){
        return  find(x) == find(y);
    }
}
