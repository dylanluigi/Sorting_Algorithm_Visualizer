package main.algorithms;

import main.SortArray;


public class MergeSort implements SortingAlgorithmInterface {

    private long stepDelay = 20;


    private int[] getSubArray(SortArray array, int begin, int size) {
        int arr[] = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = array.getValue(begin + i);
        }
        return arr;
    }



    private void merge(SortArray array, int left, int middle, int right) {
        int leftSize = middle - left + 1;
        int rightSize = right - middle;

        int leftArray[] = getSubArray(array, left, leftSize);
        int rightArray[] = getSubArray(array, middle + 1, rightSize);

        int i = 0, j = 0, k = left;
        while (i < leftSize && j < rightSize) {
            if (leftArray[i] <= rightArray[j]) {
                array.updateSingle(k, leftArray[i], getDelay(), true);
                i++;
            } else {
                array.updateSingle(k, rightArray[j], getDelay(), true);
                j++;
            }
            k++;
        }

        while (i < leftSize) {
            array.updateSingle(k, leftArray[i], getDelay(), true);
            i++;
            k++;
        }

        while (j < rightSize) {
            array.updateSingle(k, rightArray[j], getDelay(), true);
            j++;
            k++;
        }
    }



    private void mergeSort(SortArray array, int left, int right) {
        if (left < right) {
            int middleIndex = (left + right) / 2;

            mergeSort(array, left, middleIndex);
            mergeSort(array, middleIndex + 1, right);
            merge(array, left, middleIndex, right);
        }
    }


    @Override
    public void runSort(SortArray array) {
        int left = 0;
        int right = array.arraySize() - 1;
        mergeSort(array, left, right);
    }

    @Override
    public String getName() {
        return "Merge Sort";
    }

    @Override
    public long getDelay() {
        return stepDelay;
    }

    @Override
    public void setDelay(long delay) {
        this.stepDelay = delay;
    }


}
