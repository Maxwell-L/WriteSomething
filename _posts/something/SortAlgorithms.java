/**
 * @author Lin Liangqi
 * @version v-1.0
 */

public final class SortAlgorithms {
    /**
     * <p> 冒泡排序 
     * <p> 时间复杂度 - O(n^2)
     * <p> 空间复杂度 - O(1)
     * @param array 待排序的数组
     * @throws NullPointerException 传入数组若为null则抛出NullPointerException异常
     */
    public static void bubbleSort(int[] array) {
        if(array == null) {
            throw new NullPointerException();
        }
        if(array.length < 2) {
            return;
        }
        for(int i = 1; i < array.length; i++) {
            for(int j = 0; j < array.length - i; j++) {
                if(array[j + 1] < array[j]) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    /**
     * <p> 插入排序 
     * <p> 时间复杂度 - O(n^2)
     * <p> 空间复杂度 - O(1)
     * @param array 待排序的数组
     * @throws NullPointerException 传入数组若为null则抛出NullPointerException异常
     */
    public static void insertionSort(int[] array) {
        if(array == null) {
            throw new NullPointerException();
        }
        if(array.length < 2) {
            return;
        }
        // 将第 i 个元素插入到正确位置
        for(int i = 1; i < array.length; i++) {
            int j = i - 1;
            while(j >= 0 && array[i] < array[j]) {
                j--;
            }
            insert(array, i, ++j);
        }
    }

    private static void insert(int[] array, int elementIndex, int index) {
        if(array == null) {
            throw new NullPointerException();
        }
        if(elementIndex == index) {
            return;
        }
        int temp = array[elementIndex];
        for(int i = elementIndex; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = temp;
    }

    /**
     * <p> 选择排序
     * <p> 时间复杂度 - O(n^2)
     * <p> 空间复杂度 - O(1)
     * @param array 待排序的数组 
     * @throws NullPointerException 传入数组若为null则抛出NullPointerException异常
     */
    public static void selectionSort(int[] array) {
        if(array == null) {
            throw new NullPointerException();
        }
        if(array.length < 2) {
            return;
        }
        for(int i = 0; i < array.length - 1; i++) {
            int rec = i, min = array[i];
            for(int j = i + 1; j < array.length; j++) {
                rec = array[j] < min ? j : rec;
                min = array[j] < min ? array[j] : min;
            }
            swap(array, i, rec);
        }
    }

    /**
     * <p> 希尔排序
     * <p> 时间复杂度 - Between O(nlog n) to O(n^2)
     * <p> 空间复杂度 - O(1)
     * @param array 待排序的数组
     * @throws NullPointerException 传入数组若为null则抛出NullPointerException异常
     */
    public static void shellSort(int[] array) {
        for(int delta = array.length / 2; delta > 0; delta /= 2) {
            for(int i = delta; i < array.length; i++) {
                int j = i;
                while(j >= delta && array[j] < array[j - delta]) {
                    swap(array, j, j - delta);
                    j -= delta;
                }
            }
        }
    }

    /**
     * <p> 快速排序
     * <p> 时间复杂度 - O(nlog n)
     * <p> 空间复杂度 - O(n)
     * @param array 待排序的数组
     * @throws NullPointerException 传入数组若为null则抛出NullPointerException异常
     */
    public static void quickSort(int[] array) {
        if(array == null) {
            throw new NullPointerException();
        }
        if(array.length < 2) {
            return;
        }
        quick(array, 0, array.length - 1, new int[array.length]);
    }

    private static void quick(int[] array, int left, int right, int[] helpArray) {
        if(array == null) {
            throw new NullPointerException();
        }
        if(left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        int pivot = array[mid], start = left, end = right;
        for(int i = left; i <= right; i++) {
            if(i == mid) {
                continue;
            }
            if(array[i] <= array[mid]) {
                helpArray[start++] = array[i];
            } else {
                helpArray[end--] = array[i];
            }
        }
        helpArray[start] = pivot;
        for(int i = left; i <= right; i++) {
            array[i] = helpArray[i];
        }
        quick(array, left, start - 1, helpArray);
        quick(array, start + 1, right, helpArray);
    }

    /**
     * <p> 归并排序
     * <p> 时间复杂度 - O(nlog n)
     * <p> 空间复杂度 - O(n)
     * @param array 待排序的数组
     * @throws NullPointerException 传入数组若为null则抛出NullPointerException异常
     */
    public static void mergeSort(int[] array) {
        if(array == null) {
            throw new NullPointerException();
        }
        if(array.length < 2) {
            return;
        }
        merge(array, 0, array.length - 1, new int[array.length]);
    }

    private static void merge(int[] array, int left, int right, int[] helpArray) {
        if(array == null) {
            throw new NullPointerException();
        }
        if(left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        merge(array, left, mid, helpArray);
        merge(array, mid + 1, right, helpArray);
        int h1 = left, h2 = mid + 1;
        for(int i = left; i <= right; i++) {
            if(h1 <= mid && (h2 > right || array[h1] <= array[h2])) {
                helpArray[i] = array[h1++];
            } else {
                helpArray[i] = array[h2++];
            }
        }
        for(int i = left; i <= right; i++) {
            array[i] = helpArray[i];
        }
    }

    /**
     * <p> 堆排序
     * <p> 时间复杂度 - O(nlog n)
     * <p> 空间复杂度 - O(1)
     * @param array 待排序的数组
     * @throws NullPointerException 传入数组若为null则抛出NullPointerException异常
     */
    public static void heapSort(int[] array) {
        if(array == null) {
            throw new NullPointerException();
        }
        if(array.length < 2) {
            return;
        }
        for(int i = array.length / 2 - 1; i >= 0; i--) {
            if((i * 2 + 2 >= array.length || array[i * 2 + 1] > array[i * 2 + 2]) && array[i * 2 + 1] > array[i]) {
                swap(array, i, i * 2 + 1);
            } else if(i * 2 + 2 < array.length && array[i * 2 + 2] > array[i]){
                swap(array, i, i * 2 + 2);
            }
        }
        for(int i = array.length - 1; i >= 0; i--) {
            swap(array, 0, i);
            int j = 0;
            while(j * 2 + 1 < i) {
                if((j * 2 + 2 >= i || array[j * 2 + 1] > array[j * 2 + 2]) && array[j * 2 + 1] > array[j]) {
                    swap(array, j, j * 2 + 1);
                    j = j * 2 + 1;
                } else if(j * 2 + 2 < i && array[j * 2 + 2] > array[j]) {
                    swap(array, j, j * 2 + 2);
                    j = j * 2 + 2;
                } else {
                    break;
                }
            }
        }
    }

    /**
     * <p> 打印数组
     * @param array 待打印的数组
     * @throws NullPointerException 传入数组若为null则抛出NullPointerException异常
     */
    public static void print(int[] array) {
        if(array == null) {
            throw new NullPointerException();
        }
        System.out.print("[");
        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if(i + 1 < array.length) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    /**
     * <p> 反转数组
     * @param array 待反转的数组
     * @throws NullPointerException 传入数组若为null则抛出NullPointerException异常
     */
    public static void reverse(int[] array) {
        if(array == null) {
            throw new NullPointerException();
        }
        if(array.length < 2) {
            return;
        }
        for(int i = 0; i < array.length / 2; i++) {
            swap(array, i, array.length - i - 1);
        }
    }

    private static void swap(int[] array, int index1, int index2) {
        if(array == null) {
            throw new NullPointerException();
        }
        if(index1 >= array.length || index2 >= array.length) {
            throw new ArrayIndexOutOfBoundsException(array.length);
        }
        if(index1 == index2) {
            return;
        }
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}