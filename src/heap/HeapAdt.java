package heap;

public class HeapAdt {
    private final int[] heap;
    private int size;
    private final int maxSize;

    public HeapAdt(int maxSize){
        if(maxSize <= 0) throw new IllegalArgumentException("maxSize has to be > 0");
        this.heap = new int[maxSize];
        size = 0;
        this.maxSize = maxSize;
    }

    private int parent(int i){
        return (i-1)/2;
    }

    private int leftChild(int i){
        return 2*i + 1;
    }

    private int rightChild(int i){
        return 2*i + 2;
    }

    private void swap(int i, int j){
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public void siftUp(int i){
        while (i > 0){
            int p = parent(i);

            if(heap[p] >= heap[i]) break;

            swap(p, i);
            i = p;
        }
    }

    public void insert(int p){
        if(size == maxSize) throw  new IllegalStateException("The heap is full.");
        heap[size] = p;
        siftUp(size);
        size++;
    }

    public void siftDown(int i){
        int maxIndex = i;

        int l = leftChild(i);
        if(l < size && heap[l] > heap[maxIndex]){
            maxIndex = l;
        }

        int r = rightChild(i);
        if(r < size && heap[r] > heap[maxIndex]){
            maxIndex = r;
        }

        if(i != maxIndex){
            swap(i, maxIndex);
            siftDown(maxIndex);
        }
    }

    public int extractMax(){
        if(size == 0) throw new IllegalStateException("The heap is empty.");

        if(size == 1) {
            size--;
            return heap[0];
        }

        int result = heap[0];
        heap[0] = heap[size-1];
        size--;
        siftDown(0);
        return result;
    }

    public void changePriority(int i, int p){
        if(i < 0 || i >= size) throw new IllegalArgumentException("index is not valid.");

        int oldP = heap[i];
        if(p == oldP) return;

        heap[i] = p;
        if(p > oldP){
            siftUp(i);
        }
        else{
            siftDown(i);
        }
    }

    public int remove(int i){
        if(size == 0) throw new IllegalStateException("The heap is empty.");

        if(i < 0 || i >= size) throw new IllegalArgumentException("index is not valid.");

        if(i == size - 1){
            int deleted = heap[size - 1];
            size--;
            return deleted;
        }

        int deleted = heap[i];

        heap[i] = heap[size - 1];
        size--;

        if (i > 0 && heap[i] > heap[parent(i)]) {
            siftUp(i);
        } else {
            siftDown(i);
        }

        return deleted;
    }
}
