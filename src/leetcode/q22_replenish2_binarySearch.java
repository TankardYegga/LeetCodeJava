package leetcode;

/**
 * Description: JVM
 * Creator: levin
 * Date: 10/3/2022
 * Time: 10:42 AM
 * Email: levinforward@163.com
 */
public class q22_replenish2_binarySearch {

    public static int binarySearch(int[] arr, int target){
        int start = 0;
        int end = arr.length - 1;

        while (start <= end){
            int mid = start + (end - start) / 2;
            if(target == arr[mid]){
                return mid;
            }else if(target > arr[mid]){
                start = mid + 1;
            }else {
                end = mid - 1;
            }
        }

        return -1;
    }

    public static int binarySearchRepeatedFirst(int[] arr, int target){
        if(arr == null){
            return -1;
        }

        int left = 0;
        int right = arr.length - 1;

        while (left <= right){
            int mid = left + (right - left) / 2;

            if(target == arr[mid]){
                while (mid >= 0 && target == arr[mid]){
                    mid--;
                }
                return mid + 1;
            }else if(target > arr[mid]){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static int binarySearchRepeatedFirst2(int[] arr, int target){
        if(arr == null){
            return -1;
        }

        int left = 0;
        int right = arr.length - 1;

        while (left <= right){
            int mid = left + (right - left) / 2;
            if(target == arr[mid]){
                if(mid == 0){
                    return 0;
                }else if(mid >= 1 && arr[mid - 1] != target){
                    return mid;
                }else if(mid >= 1 && arr[mid - 1] == target){
                    right = mid - 1;
                }else{
                    continue;
                }
            }else if(target > arr[mid]){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args){
        int[] arr = {2, 2, 2, 3, 3, 4, 4, 5, 5, 5, 6};
        int target = 4;
        int index = binarySearch(arr, target);
        System.out.println("index:" + index);
        int index2 = binarySearchRepeatedFirst(arr, target);
        System.out.println("index2:" + index2);
        int index3 = binarySearchRepeatedFirst2(arr, target);
        System.out.println("index3:" + index3);
    }
}
