package heap;

public class HeapSort {

    public static void heapSort(int[] arr){
        buildHeap(arr);

        int heapSize = arr.length;

        while(heapSize > 1){
            swap(arr, 0, heapSize - 1);
            heapSize--;
            siftDown(arr, 0, heapSize);
        }
    }

    private static void buildHeap(int[] arr){
        int heapSize = arr.length;
        for(int i = arr.length / 2 - 1; i >= 0; i--){
            siftDown(arr, i, heapSize);
        }
    }

    private static void siftDown(int[] arr, int i, int heapSize){
        int maxIndex = i;

        int l = leftChild(i);
        if(l < heapSize && arr[l] > arr[maxIndex]){
            maxIndex = l;
        }

        int r = rightChild(i);
        if(r < heapSize && arr[r] > arr[maxIndex]){
            maxIndex = r;
        }

        if(i != maxIndex){
            swap(arr, i, maxIndex);
            siftDown(arr, maxIndex, heapSize);
        }
    }

    private static int leftChild(int i){return 2 * i + 1;}

    private static int rightChild(int i){return 2 * i + 2;}

    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
