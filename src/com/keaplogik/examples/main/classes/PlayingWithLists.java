package com.keaplogik.examples.main.classes;

import com.keaplogik.examples.data.structures.LinkedList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 */
public class PlayingWithLists {


    public static void main(String[] args) {

        LinkedList<String> linkedList = new LinkedList<String>();

        linkedList.add("Hello");
        linkedList.add("World");
        linkedList.add("In");
        linkedList.add("Java");

        final Iterator<String> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println();
        System.out.println();
        System.out.println(linkedList.get(1));

        String[] strings = new String[10];
        System.out.println(strings.length >>> 1);

        List<Integer> values = new ArrayList<Integer>();
        values.add(6);
        values.add(9);
        values.add(2);
        values.add(5);
        values.add(1);
        values.add(7);
        values.add(0);
        values.add(5);

        List<Integer> result = new PlayingWithLists().quickSort(values);

        for (Integer value : result) {
            System.out.println(value);
        }

    }

    public List<Integer> quickSort(List<Integer> values) {
        if (values.size() <= 1) {
            return values;
        }

        int pivotPoint = values.size() >>> 1;
        int pivotValue = values.remove(pivotPoint);

        List<Integer> left = new ArrayList<Integer>();
        List<Integer> right = new ArrayList<Integer>();
        for (int value : values) {
            if (value > pivotValue) {
                right.add(value);
            } else if (value < pivotValue) {
                left.add(value);
            }
        }
        final List<Integer> result = new ArrayList<Integer>();
        result.addAll(quickSort(left));
        result.add(pivotValue);
        result.addAll(quickSort(right));
        return result;
    }

    public boolean binarySearch(int[] a, int b) {
        if (a.length == 0) {
            return false;
        }
        int low = 0;
        int high = a.length - 1;

        while (low <= high) {
            int middle = (low + high) >>> 1;
            if (b > a[middle]) {
                low = middle + 1;
            } else if (b < a[middle]) {
                high = middle - 1;
            } else { // The element has been found
                return true;
            }
        }
        return false;
    }
    //Derek
    public boolean binarySearch(int[] a, int start, int end, int b) {
        if (a.length == 0) {
            return false;
        }
        int low = start;
        int high = end;

        while (low <= high) {
            int middle = (low + high) >>> 1;
            if (b > a[middle]) {
                low = middle + 1;
            } else if (b < a[middle]) {
                high = middle - 1;
            } else { // The element has been found
                return true;
            }
        }
        return false;
    }
    
    public int findLoacationOfLowestValuesIndex(Integer[] arr) {
        int low = 0;
        int high = arr.length - 1;
        //Is the lower bound value greater then the upper bound value? 
        while (arr[low] > arr[high]) {
            int mid = (low + high) >>> 1;
            if (arr[mid] > arr[high]) { //Is the middle value greater then the upper value
                low = mid + 1; // yes move low past mid point.
            } else {
                high = mid; // Else move the upper bounds to the mid point and try again.
            }
        }
        return low;
    }

}
