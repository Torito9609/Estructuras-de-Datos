package tree;

public class BinarySearchTree<T extends Comparable<T>>{
    private static class Node<T>{
        T value;
        Node<T> parent;
        Node<T> leftChild;
        Node<T> rightChild;
        Node(T value){
            this.value = value;
        }
    }
    private Node<T> root;

    public boolean isEmpty(){
        return root == null;
    }

    private Node<T> find(T value, Node<T> node){
        if(node == null) return null;

        int cmp = value.compareTo(node.value);

        if(cmp == 0) return node;

        else if(cmp < 0) return find(value, node.leftChild);

        else return find(value, node.rightChild);
    }

    public boolean contains(T value){
        Node<T> found = find(value, root);
        return found != null;
    }

    public void insert(T value){
        Node<T> newNode = new Node<>(value);

        if(root == null){
            root = newNode;
            return;
        }

        Node<T> current = root;
        Node<T> parent = null;

        while (current != null){
            parent = current;
            int cmp = value.compareTo(current.value);

            if(cmp == 0){
                return;
            }
            else if(cmp < 0){
                current = current.leftChild;
            }
            else {
                current = current.rightChild;
            }
        }

        newNode.parent = parent;
        if(value.compareTo(parent.value) < 0){
            parent.leftChild = newNode;
        }
        else{
            parent.rightChild = newNode;
        }
    }

    private Node<T> next(Node<T> node){
        if(node == null) return null;
        return node.rightChild != null
                ? leftDescendant(node.rightChild)
                : rightAncestor(node);
    }

    private Node<T> leftDescendant(Node<T> node){
        if(node == null) return null;
        return node.leftChild == null
                ? node
                : leftDescendant(node.leftChild);
    }

    private Node<T> rightAncestor(Node<T> node) {
        if (node == null || node.parent == null) return null;
        return (node == node.parent.leftChild)
                ? node.parent
                : rightAncestor(node.parent);
    }

    public T nextValue(T value){
        Node<T> node = find(value, root);

        if(node == null) return null;
        Node<T> successor = next(node);
        return successor == null
                ? null
                : successor.value;
    }
}
