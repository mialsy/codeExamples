package sorting;

import java.util.Arrays;

/** A class demonstrating several sorting algorithms covered in the Data Structures and Algorithms course */
public class SortingAlgorithms {

    /**
     * Selection sort
     * @param arr input array
     */
    public static void selectionSort(int[] arr) {
        if (arr == null)
            return;
        for (int i = 0; i < arr.length; i++) {
            // find the current smallest element in the
            // sublist from index i until the end of the list
            int indexOfMin = i;
            for (int j = indexOfMin + 1; j < arr.length; j++) {
                if (arr[j] < arr[indexOfMin])  {
                    indexOfMin = j;
                }
            }
            // Now we know the smallest element in the sublist from i to n-1: arr[indexOfMin]
            // swap it with the element at index i
            int tmp = arr[indexOfMin];
            arr[indexOfMin] = arr[i];
            arr[i] = tmp;
        }
    }

    /**
     * Bubble sort
     * @param arr input array
     */
    public static void bubbleSort(int[] arr) {
        if (arr == null)
            return;
        for (int pos = 0; pos < arr.length - 1; pos++) {
            // Bubble up the smallest element to the front of the list
            for (int j = arr.length - 1; j > pos; j--) {
                if (arr[j - 1] > arr[j] ) {
                    int tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1]= tmp;
                }
            }
        }
    }

    /**
     * Insertion sort
     * @param arr input array
     */
    public static void insertionSort(int[] arr) {
        if (arr == null)
            return;

        for (int i = 1; i < arr.length; i++) {
            int curr = arr[i];
            // Compare with elements of the sorted list, starting at i - 1;
            int j = i - 1;
            while ((j >= 0) && (curr < arr[j])) {
                arr[j + 1] = arr[j]; // shift elements to make space for curr
                j--;
            }
            arr[j + 1] = curr; // place curr
        }
    }

    /** --- Not  covered in cs245 -----------
     * Sort a given array using shell sort and n/2, n/4, n/8 etc increments. The
     * code is modified from the code of Prof. Galles.
     */
    public static void shellSort(int[] arr) {
        if (arr == null)
            return;
        int n = arr.length;
        int increment, offset;
        for (increment = n / 2; increment > 0; increment = increment / 2)
            for (offset = 0; offset < increment; offset++)
                insertionSort(arr, offset, increment);
    }

    /**
     * Another version of the insertion sort that sorts a sublist of a given
     * list. Used in Shell Sort. Takes an offset (the first element of the list
     * will be at arr[offset]) and increment (the gap between the elements of
     * the list)
     */
    public static void insertionSort(int[] arr, int offset, int increment) {
        if (arr == null)
            return;
        int i, j;
        int n = arr.length;
        for (i = offset + increment; i < n; i = i + increment) {
            int curr = arr[i];
            j = i - increment;
            while (j >= 0 && arr[j] > curr) {
                arr[j + increment] = arr[j];
                j = j - increment;
            }
            arr[j + increment] = curr;
        }
    }

    /** An exercise we did in class - merges two sorted arrays together. Not used in any sorting method.
     * See merge(int[] arr, int[] temp, int low, int mid, int high)  instead.
     * @param arr1 sorted array 1
     * @param arr2 sorted array 2
     * @return sorted array that combines elements from arr1 and arr2
     */
    public static int[] merge(int[] arr1, int[] arr2) {

        int[] temp = new int[arr1.length + arr2.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while (k < arr1.length + arr2.length) {
            if (i >= arr1.length) {// ran out of elements in the i list
                temp[k] = arr2[j];
                k++;
                j++;
            } else if (j >= arr2.length) {// ran out of elements in the j list
                temp[k] = arr1[i];
                k++;
                i++;
            } else if (arr1[i] < arr2[j]) { // place arr[i] in temp, move i
                temp[k] = arr1[i];
                k++;
                i++;
            } else {
                temp[k] = arr2[j]; // place arr[j] in temp, move j
                k++;
                j++;
            }
        }

        return temp;
    }

    /** public method for mergeSort - called from outside of the class */
    public static void mergeSort(int[] arr) {
        int[] temp = new int[arr.length];
        mergeSort(arr, temp, 0, arr.length - 1);
    }

    /**
     * A private mergeSort method - takes an array, a temporary array, and the
     * indices that specify what part of the list we are working with (we need
     * to sort the part from low to high)
     *
     * @param arr
     * @param temp
     * @param low
     * @param high
     */
    private static void mergeSort(int[] arr, int[] temp, int low, int high) {
        if (low >= high)
            return;
        // divide in half
        int mid = (low + high) / 2;
        mergeSort(arr, temp, low, mid);
        mergeSort(arr, temp, mid + 1, high);

        merge(arr, temp, low, mid, high); // merge two sorted halves into one
        // sorted list
        System.out.println(Arrays.toString(arr));

    }

    /**
     * A helper method used in Merge Sort.
     * Merges two sorted sublists together, one that goes from low to mid, another
     * goes from mid+1 to high. Uses a temporary array.
     *
     * @param arr input array
     * @param temp
     * @param low index where the first sorted sublist starts
     * @param mid index where the first sorted sublist ends
     * @param high index where the second sorted sublist ends
     */
    public static void merge(int[] arr, int[] temp, int low, int mid, int high) {
        int k = low; // the index in the temp array we will use for merging
        int i = low;
        int j = mid + 1; // where the second list starts
        while (k <= high) {
            if (i > mid) {// ran out of elements in the i sublist
                temp[k] = arr[j];
                k++;
                j++;
            } else if (j > high) {// ran out of elements in the j sublist
                temp[k] = arr[i];
                k++;
                i++;
            } else if (arr[i] < arr[j]) { // place arr[i] in temp, move i
                temp[k] = arr[i];
                k++;
                i++;
            } else {
                temp[k] = arr[j]; // place arr[j] in temp, move j
                k++;
                j++;
            }
        }
        // copy the result from temp back to arr
        for (k = low; k <= high; k++)
            arr[k] = temp[k];
    }


    public static void sortAB (String[] letters) {
        int i = 0;
        int j = letters.length - 1;
        while (i < j ) {
            while (i < j  && letters[i].equals("A"))
                    i++;
            while (i < j  && letters[j].equals("B"))
                j--;
            if (i < j) {
                letters[i] = "A";
                letters[j] = "B";
                i++;
                j--;
            }
        }
    }

        /**
         * Quick sort - public method
         * @param arr input array if integers
         */
    public static void quickSort(int arr[]) {
        quickSort(arr, 0, arr.length - 1);
    }

    /**
     * Quick sort implementation. Uses partition as a helper method.
     * @param arr array of integers
     * @param low index of the first element of the sub-array to sort
     * @param high index of the last element of the sub-array to sort
     */
    private static void quickSort(int arr[], int low, int high) {
        int indexOfPivot;
        if (low <= high) {
            indexOfPivot = partition(arr, low, high); // the index of pivot element
            System.out.println("Pivot: " + arr[indexOfPivot]);
            System.out.println(Arrays.toString(arr));
            quickSort(arr, low, indexOfPivot - 1);
            quickSort(arr, indexOfPivot + 1, high);
        }
    }

    /**
     * Helper method for the quick sort. Rearranges the array so that it
     * first has elements < pivot, then pivot, and then elements >= pivot.
     * Chooses the middle element as the pivot.
     * @param arr array
     * @param low index of the first element of the sub-array
     * @param high index of the last element of the sub-array
     * @return index of the pivot after partition
     */
    public static int partition(int arr[], int low, int high) {
        int mid = (low + high) / 2;
        int pivotElem = arr[mid];

        // Swap pivot with the element at the last index
        int tmp = arr[high];
        arr[high] = pivotElem;
        arr[mid] = tmp;

        int i = low;
        int j = high - 1;
        while (i <= j) {
            while ( (arr[i] < pivotElem))
                i++;

            while ((j >= low) && (arr[j] >= pivotElem))
                j--;

            if (i > j)
                break;
            else { // swap elements at indices i and j
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        } // while
        if (i > j) {
            // swap arr[i] and last elem
            tmp = arr[i];
            arr[i] = arr[high];
            arr[high] = tmp;
        }
        return i;
    }



    public static void main(String[] args) {
        int[] array = {17, 10, 15, 13, 4, 12, 7, 9, 16, 8, 5, 14, 3};
        System.out.println(Arrays.toString(array));
        // selectionSort(array);
        // bubbleSort(array);
        // insertionSort(array);
        mergeSort(array);
        System.out.println(Arrays.toString(array));
        String[] letters = {"A", "B", "A", "A", "B", "A", "B", "B", "B", "A", "B"};
        sortAB(letters);
        System.out.println(Arrays.toString(letters));
    }
}
