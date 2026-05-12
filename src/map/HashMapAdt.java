package map;

public class HashMapAdt<K, V> {
    private static class Node<K, V>{
        private K key;
        private V value;
        private Node<K,V> next;

        private Node(K key, V value){
            this.key = key;
            this.value = value;
        }
    }
    private static final double LOAD_FACTOR = 0.75;
    private Node<K,V>[] table;
    private int size;
    private int capacity;

    public HashMapAdt(int capacity){
        if(capacity <= 0){
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }
        this.capacity = capacity;
        this.size = 0;
        this.table = (Node<K, V>[]) new Node[capacity];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public void put(K key, V value){
        int index = hash(key);
        Node<K, V> current = table[index];

        while(current != null){
            if(current.key.equals(key)){
                current.value = value;
                return;
            }
            current = current.next;
        }

        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = table[index];
        table[index] = newNode;
        size++;

        if ((double) size / capacity >= LOAD_FACTOR) {
            resize();
        }
    }

    public V get(K key){
        int index = hash(key);
        Node<K, V> current = table[index];

        while (current != null){
            if(current.key.equals(key)) return current.value;
            current = current.next;
        }

        return null;
    }

    public boolean containsKey(K key){
        int index = hash(key);
        Node<K, V> current = table[index];

        while(current != null){
            if(current.key.equals(key)) return true;
            current = current.next;
        }
        return false;
    }

    public V remove(K key){
        int index = hash(key);
        Node<K, V> current = table[index];

        if(current == null) return null;

        if(current.key.equals(key)){
            V deleted = current.value;
            table[index] = current.next;
            size--;
            return deleted;
        }

        Node<K, V> prev = table[index];
        current = table[index].next;

        while (current != null){
            if(current.key.equals(key)){
                V deleted = current.value;
                prev.next = current.next;
                size--;
                return deleted;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    private void resize(){
        Node<K, V> [] oldTable = this.table;
        capacity = capacity * 2;
        this.table = (Node<K, V>[]) new Node[capacity];
        size = 0;
        for(Node<K,V> bucket : oldTable){
            while(bucket != null){
                put(bucket.key, bucket.value);
                bucket = bucket.next;
            }
        }
    }
}
