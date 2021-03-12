package me.mica.learn;

import java.util.Arrays;

public class HeapSort {


    public static void main(String[] args) {
        int[] array = {2, 4, 6, 8, 3, 1, 7, 0};
        new HeapSort().sort(array);
        Arrays.stream(array).forEach(System.out::println);
    }


    private int leftChild(int node) {
        return node << 1;
    }

    private int rightChild(int node) {
        return (node << 1) + 1;
    }

    private int parent(int node) {
        return node >> 1;
    }


    private void adjust(int[] array, int midNode, int len) {
        int leftChild = leftChild(midNode);
        int rightChild = rightChild(midNode);

        if (leftChild < len) {
            if (array[midNode] < array[leftChild]) {
                swap(array, midNode, leftChild);
                adjust(array, leftChild, len);
            }
            if (rightChild < len && array[midNode] < array[rightChild]) {
                swap(array, midNode, rightChild);
                adjust(array, rightChild, len);
            }
        }
    }


    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public void sort(int[] array) {
        int arrayLen = array.length;
        for (int i = arrayLen; i > 0; -- i) {
            adjust(array, parent(i), i);
            swap(array, 0, i - 1);
        }
    }
}
