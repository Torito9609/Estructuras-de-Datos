 package tree;

 import java.util.ArrayDeque;
 import java.util.Queue;

 public class BinaryTree<T>{
    protected static class Node<T>{
        T value;
        Node<T> left;
        Node<T> right;

        Node(T value){
            this.value = value;
        }
    }
    protected Node<T> root;

    public BinaryTree(){
        root = null;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public int height(){
        return height(root);
    }

    private int height(Node<T> node){
        if(node == null) return 0;
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public int size(){
        return size(root);
    }

    private int size(Node<T> node){
        if(node == null) return 0;
        int leftSize = size(node.left);
        int rightSize = size(node.right);
        return 1 + leftSize + rightSize;
    }

    public void inOrder(){
        System.out.println("InOrder: ");
        inOrder(root);
        System.out.println();
    }

    private void inOrder(Node<T> node){
        if(node == null) return;
        inOrder(node.left);
        System.out.print(node.value + ", ");
        inOrder(node.right);
    }

    public void preOrder(){
        System.out.println("PreOrder: ");
        preOrder(root);
        System.out.println();
    }

    private void preOrder(Node<T> node){
        if(node == null) return;
        System.out.print(node.value + ", ");
        preOrder(node.left);
        preOrder(node.right);
    }

    public void postOrder(){
        System.out.println("PostOrder: ");
        postOrder(root);
        System.out.println();
    }

    private void postOrder(Node<T> node){
        if(node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.value + ", ");
    }

    public void levelOrder(){
        System.out.println("LevelOrder: ");
        if(root == null) return;
        Queue<Node<T>> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            Node<T> node = queue.poll();
            System.out.print(node.value + ", ");
            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }
        }
        System.out.println();
    }
}
