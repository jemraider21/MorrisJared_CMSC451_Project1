import java.util.Arrays;

public class MergeSort implements SortInterface {

    int count = 0;
    long timeStart = 0;
    long timeEnd = 0;

    // Iterative approach from GeeksForGeeks
    // https://www.geeksforgeeks.org/iterative-merge-sort/
    public int[] iterativeSort(int[] array) throws UnsortedException {
        count++;
        timeStart = System.nanoTime();
        if (array == null) {
            return new int[0];
        }
        if (array.length > 1) {
            int mid = array.length / 2;

            // Split left part
            int[] left = new int[mid];
            for (int i = 0; i < mid; i++) {
                left[i] = array[i];
            }

            // Split right part
            int[] right = new int[array.length - mid];
            for (int i = mid; i < array.length; i++) {
                right[i - mid] = array[i];
            }
            int[] left2 = iterativeSort(left);
            int[] right2 = iterativeSort(right);

            int i = 0;
            int j = 0;
            int k = 0;

            // Merge left and right arrays
            while (i < left2.length && j < right2.length) {
                if (left2[i] < right2[j]) {
                    array[k] = left2[i];
                    i++;
                } else {
                    array[k] = right2[j];
                    j++;
                }
                k++;
            }
            // Collect remaining elements
            while (i < left2.length) {
                array[k] = left2[i];
                i++;
                k++;
            }
            while (j < right.length) {
                array[k] = right2[j];
                j++;
                k++;
            }
        }
        timeEnd = System.nanoTime();
        checkSortedArray(array);
        return array;
    }

    // Recursive approach from GeeksForDeeks
    // https://www.geeksforgeeks.org/merge-sort/
    public int[] recursiveSort(int[] array) throws UnsortedException {
        count++;
        timeStart = System.nanoTime();

        // array = recursiveSort(array, array.length);

        if (array.length > 1) {
            int middle = array.length / 2;

            // Split left part
            int[] left = new int[middle];
            for (int i = 0; i < middle; i++) {
                left[i] = array[i];
            }

            // Split right part
            int[] right = new int[array.length - middle];
            for (int i = middle; i < array.length; i++) {
                right[i - middle] = array[i];
            }

            int[] left2 = recursiveSort(left);
            int[] right2 = recursiveSort(right);

            array = recursiveMerge(array, left2, right2, middle, array.length - middle);
        }

        timeEnd = System.nanoTime();
        checkSortedArray(array);

        return array;
    }

    // Based off of post from Baeldung
    // https://www.baeldung.com/java-merge-sort
    private int[] recursiveMerge(int[] array, int[] leftHalf, int[] rightHalf, int left, int right) {
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < left && j < right) {
            if (leftHalf[i] <= rightHalf[j]) {
                array[k++] = leftHalf[i++];
            } else {
                array[k++] = rightHalf[j++];
            }
        }

        while (i < left) {
            array[k++] = leftHalf[i++];
        }

        while (j < right) {
            array[k++] = rightHalf[j++];
        }
        return array;
    }

    public int getCount() {
        int result = count;
        count = 0;
        return result;
    }

    public long getTime() {
        long time = timeEnd - timeStart;
        timeEnd = 0;
        timeStart = 0;
        return time;
    }

    private void checkSortedArray(int[] list) throws UnsortedException {
        for (int i = 0; i < list.length - 1; i++) {
            if (list[i] > list[i + 1]) {
                for (int j = 0; i < list.length - 1; j++) {
                    System.out.println(" " + list[j]);
                }
                throw new UnsortedException("The array was not sorted correctly: \n" +
                        list[i] + " at index " + i + " and " + list[i + 1] + " at index " + (i + 1));
            }
        }
    }

}