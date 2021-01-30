package CompareSorts;

public class SortAlgorithms {

    public static void insertionSort(int[] arr) {
        for(int cursorOne = 1; cursorOne < arr.length; cursorOne++) {
            int cursorTwo = cursorOne -1;
            int swapValue = arr[cursorOne];
            while (cursorTwo >= 0 && (swapValue < arr[cursorTwo])) {
                    arr[cursorTwo +1] = arr[cursorTwo];
                    cursorTwo--;
            }
            arr[cursorTwo+1] = swapValue;
        }
    }

    private static void merge(int[] array, int left, int middle, int right) {
        int leftArrayLength = middle - left;
        int rightArrayLength = right - middle;

        // copy values to temp arrays
        int[] tempLeft = new int[leftArrayLength];
        int[] tempRight = new int[rightArrayLength];

        // copy to the temp arrays
        System.arraycopy(array, left , tempLeft, 0, leftArrayLength);
        System.arraycopy(array, middle, tempRight, 0, rightArrayLength);

        // merge arrays into the main referenced array
        int tempLeftIndex = 0;
        int tempRightIndex = 0;

        // main array index
        int mainIndex = left;

        while (tempLeftIndex < leftArrayLength && tempRightIndex < rightArrayLength) {
            if (tempLeft[tempLeftIndex] <= tempRight[tempRightIndex]) {
                array[mainIndex] = tempLeft[tempLeftIndex];
                tempLeftIndex++;
            } else {
                array[mainIndex] = tempRight[tempRightIndex];
                tempRightIndex++;
            }
            mainIndex++;
        }

        // finish copying arrays values
        while (tempLeftIndex < leftArrayLength) {
            array[mainIndex] = tempLeft[tempLeftIndex];
            tempLeftIndex++;
            mainIndex++;
        }
        while (tempRightIndex < rightArrayLength) {
            array[mainIndex] = tempRight[tempRightIndex];
            tempRightIndex++;
            mainIndex++;
        }

    }

    private static void mergeSort(int[] array, int left, int right) {
        if(left <= right) { // base cae
            return;
        }
        int middle = (left + right -1)/2;
        mergeSort(array, left, middle);
        mergeSort(array, middle+1, right);
        merge(array, left, middle, right);
    }

    public static void mergeSort(int[] array) {
        mergeSort(array, 0, array.length);
    }

    private static void printArray(int[] array) {
        for(int n : array) {
            System.out.println(n);
        }
    }


}
