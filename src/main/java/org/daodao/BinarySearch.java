package org.daodao;

public class BinarySearch {

    public static void main(String[] args) {
        int[] sortedArray = {1, 3, 5, 7, 9, 11, 13, 15};
        int target = 7;
        
        int result = binarySearch(sortedArray, target);
        
        if (result == -1) {
            System.out.println("目标值 " + target + " 不在数组中");
        } else {
            System.out.println("目标值 " + target + " 在数组中的索引是: " + result);
        }
    }
    
    /**
     * 二分查找实现方法
     * @param arr 已排序的数组
     * @param target 要查找的目标值
     * @return 目标值在数组中的索引，如果不存在则返回-1
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
            
        }
        return -1;
    }

}
