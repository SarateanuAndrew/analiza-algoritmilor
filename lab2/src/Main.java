import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();

        for (int i = 10000; i <= 100000; i = i + 10000) {
            int[] arr = new int[i];
            Arrays.setAll(arr, j -> random.nextInt(1000));
            int n = arr.length - 1;
            System.out.println("+++++++++++++++++++++++++++ For n = " + i + " +++++++++++++++++++++++++++++");
            System.out.println("Quick Sort");
            long start1 = System.nanoTime();
            quickSort(arr, 0, n);
            long end1 = System.nanoTime();
            System.out.println("Elapsed Time in nano seconds: " + (end1 - start1));

            System.out.println();
            System.out.println("Merge Sort");
            long start2 = System.nanoTime();
            mergeSort(arr, 0, n);
            long end2 = System.nanoTime();
            System.out.println("Elapsed Time in nano seconds: " + (end2 - start2));

            System.out.println();
            System.out.println("Heap Sort");
            long start3 = System.nanoTime();
            heapSort(arr);
            long end3 = System.nanoTime();
            System.out.println("Elapsed Time in nano seconds: " + (end3 - start3));

            System.out.println();
            System.out.println("Counting Sort");
            long start4 = System.nanoTime();
            countingSort(arr);
            long end4 = System.nanoTime();
            System.out.println("Elapsed Time in nano seconds: " + (end4 - start4));

            System.out.println();
            System.out.println("Selection Sort");
            long start5 = System.nanoTime();
            selectionSort(arr);
            long end5 = System.nanoTime();
            System.out.println("Elapsed Time in nano seconds: " + (end5 - start5));

            System.out.println();
            System.out.println("1. Sieve of Eratosthenes");
            long startTime1 = System.nanoTime();
            sieveOfEratosthenes1(i);
            long endTime1 = System.nanoTime();
            System.out.println("Elapsed Time in nano seconds: " + (endTime1 - startTime1));

            System.out.println();
            System.out.println("2. Sieve of Eratosthenes");
            long startTime2 = System.nanoTime();
            sieveOfEratosthenes2(i);
            long endTime2 = System.nanoTime();
            System.out.println("Elapsed Time in nano seconds: " + (endTime2 - startTime2));

            System.out.println();
            System.out.println("3. Sieve of Eratosthenes");
            long startTime3 = System.nanoTime();
            sieveOfEratosthenes3(i);
            long endTime3 = System.nanoTime();
            System.out.println("Elapsed Time in nano seconds: " + (endTime3 - startTime3));

            System.out.println();
            System.out.println("4. Sieve of Eratosthenes");
            long startTime4 = System.nanoTime();
            sieveOfEratosthenes4(i);
            long endTime4 = System.nanoTime();
            System.out.println("Elapsed Time in nano seconds: " + (endTime4 - startTime4));

            System.out.println();
            System.out.println("5. Sieve of Eratosthenes");
            long startTime5 = System.nanoTime();
            sieveOfEratosthenes5(i);
            long endTime5 = System.nanoTime();
            System.out.println("Elapsed Time in nano seconds: " + (endTime5 - startTime5));

            System.out.println();
        }
    }

    //Quick Sort Start
    public static void quickSort(int[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);
            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    public static int partition(int[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
                int swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }
        int swapTemp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = swapTemp;
        return i + 1;
    }
    //Quick Sort End

    //MergeSort Start
    static void merge(int[] arr, int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int[] L = new int[n1];
        int[] R = new int[n2];

        /*Copy data to temp arrays*/
        System.arraycopy(arr, l, L, 0, n1);
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        /* Merge the temp arrays */

        // Initial indexes of first and second sub arrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // merge()
    static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second halves
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }
    //MergeSort End

    //HeapSort Start
    public static void heapSort(int[] arr) {
        int n = arr.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // One by one extract an element from heap
        for (int i = n - 1; i >= 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    static void heapify(int[] arr, int n, int i) {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < n && arr[l] > arr[largest])
            largest = l;

        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest])
            largest = r;

        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }
    //HeapSort End

    //Counting Sort Start
    static void countingSort(int[] array) {
        int size = array.length;
        int[] output = new int[size + 1];

        // Find the largest element of the array
        int max = array[0];
        for (int i = 1; i < size; i++) {
            if (array[i] > max)
                max = array[i];
        }
        int[] count = new int[max + 1];

        // Initialize count array with all zeros.
        for (int i = 0; i < max; ++i) {
            count[i] = 0;
        }

        // Store the count of each element
        for (int j : array) {
            count[j]++;
        }

        // Store the cummulative count of each array
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        // Find the index of each element of the original array in count array, and
        // place the elements in output array
        for (int i = size - 1; i >= 0; i--) {
            output[count[array[i]] - 1] = array[i];
            count[array[i]]--;
        }

        // Copy the sorted elements into original array
        System.arraycopy(output, 0, array, 0, size);
    }

    //Counting Sort End

    //Selection Sort Start
    static void selectionSort(int[] arr) {
        int n = arr.length;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i + 1; j < n; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j;

            // Swap the found minimum element with the first
            // element
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }
    //Selection Sort End

    //1. sieveOfEratosthenes Start
    public static void sieveOfEratosthenes1(int n) {
        boolean[] c = new boolean[n + 1];
        Arrays.fill(c, true);
        c[0] = false;
        c[1] = false;
        int i = 2;
        while (i <= n) {
            if (c[i]) {
                int j = 2 * i;
                while (j <= n) {
                    c[j] = false;
                    j += i;
                }
            }
            i++;
        }
    }
    //1. sieveOfEratosthenes End

    //2. sieveOfEratosthenes Start
    public static void sieveOfEratosthenes2(int n) {
        boolean[] c = new boolean[n + 1];
        Arrays.fill(c, true);
        c[0] = false;
        c[1] = false;
        int i = 2;
        while (i <= n) {
            int j = 2 * i;
            while (j <= n) {
                c[j] = false;
                j += i;
            }
            i++;
        }
    }
    //2. sieveOfEratosthenes End

    //3. sieveOfEratosthenes Start
    public static void sieveOfEratosthenes3(int n) {
        boolean[] c = new boolean[n + 1];
        Arrays.fill(c, true);
        c[0] = false;
        c[1] = false;
        int i = 2;
        while (i <= n) {
            if (c[i]) {
                int j = i + 1;
                while (j <= n) {
                    if (j % i == 0) {
                        c[j] = false;
                    }
                    j++;
                }
            }
            i++;
        }
    }
    //3. sieveOfEratosthenes End

    //4. sieveOfEratosthenes Start
    public static void sieveOfEratosthenes4(int n) {
        boolean[] c = new boolean[n + 1];
        Arrays.fill(c, true);
        c[0] = false;
        c[1] = false;
        int i = 2;
        while (i <= n) {
            int j = 1;
            while (j < i) {
                if (i % j == 0) {
                    c[i] = false;
                }
                j++;
            }
            i++;
        }
    }
    //4. sieveOfEratosthenes End

    //5. sieveOfEratosthenes Start
    public static void sieveOfEratosthenes5(int n) {
        boolean[] c = new boolean[n + 1];
        Arrays.fill(c, true);
        c[0] = false;
        c[1] = false;
        int i = 2;
        while (i <= n) {
            int j = 2;
            while (j <= Math.sqrt(i)) {
                if (i % j == 0) {
                    c[i] = false;
                }
                j++;
            }
            i++;
        }
    }
    //5. sieveOfEratosthenes End
}