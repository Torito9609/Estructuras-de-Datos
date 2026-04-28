import disjointset.DisjointSet;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DisjointSet ds = new DisjointSet(6);

        ds.union(2, 4);
        ds.union(5, 2);
        ds.union(3, 1);
        ds.union(2, 3);
        ds.union(2, 6);

        System.out.println(ds.connected(5, 6)); // true
        System.out.println(ds.connected(1, 4)); // true
        System.out.println(ds.connected(1, 6)); // true
    }
}