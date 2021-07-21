package jsrozman.hw3;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StopwatchCPU;
import jsrozman.hw3.BST.Node;
import algs.days.day16.ComparableTimSort;
import algs.hw3.CountedItem;
import algs.hw3.PrimitiveTimSort;

/**
 * 
 * Use the existing SortTrial class, and write your own for your implementation
 * of TimSort and also the HeapSort 
 * 
 * https://shakespeare.folger.edu/shakespeares-works/hamlet/download/
 * 
 * What is the longest word which is not a modern English word, according to
 * our dictionary?
 */
public class Q1 {
	
	/** Return time to sort array using merge sort. */
	public static double mergeSort(Comparable<?>[] A) {
		StopwatchCPU start = new StopwatchCPU(); 
		edu.princeton.cs.algs4.Merge.sort(A);
		return start.elapsedTime();
	}
	
	/** Return time to sort array using quick sort. */
	public static double quickSort(Comparable<?>[] A) {
		StopwatchCPU start = new StopwatchCPU();
		edu.princeton.cs.algs4.Quick.sort(A);
		return start.elapsedTime();
	}
	
	/** Return time to sort array using Insertion Sort. */
	public static double insertionSort(Comparable<?>[] A) {
		StopwatchCPU start = new StopwatchCPU();
		edu.princeton.cs.algs4.Insertion.sort(A);
		return start.elapsedTime();
	}
	
	/** Return time to sort array using Selection Sort. */
	public static double selectionSort(Comparable<?>[] A) {
		StopwatchCPU start = new StopwatchCPU();
		edu.princeton.cs.algs4.Selection.sort(A);
		return start.elapsedTime();
	}
	
	/** Return time to sort array using Heap Sort. */
	public static double heapSort(Comparable<?>[] A) {
		StopwatchCPU start = new StopwatchCPU();
		edu.princeton.cs.algs4.Heap.sort(A);
		return start.elapsedTime();
	}
	
	/** Return time to sort array using Primitive Tim Sort. */
	public static double primitiveTimSort(Comparable<?>[] A) {
		StopwatchCPU start = new StopwatchCPU();
		PrimitiveTimSort.sort(A);
		return start.elapsedTime();
	}
	
	/** Return time to sort array using Optimized Tim Sort. */
	public static double builtinSort(Comparable<?>[] A) {
		StopwatchCPU start = new StopwatchCPU();
		ComparableTimSort.sort(A);
		return start.elapsedTime();
	}
	
	/** Determine if the array is sorted. */
	@SuppressWarnings("unchecked")
	public static boolean isSorted(Comparable[] A) {
		for(int i = 0; i < A.length-1; i++) {
			if(A[i].compareTo(A[i+1]) > 0) {
				return false;
			}
		}
		
		return true;
	}

	/** 
	 * Given a sorted array of CountedItem<String> objects, ensure that for 
	 * any two index positions, i and j, if A[i] is equal to A[j] and i < j, 
	 * then A[i].earlier(A[j]) is true.
	 * 
	 * Performance must be O(N).
	 */
	@SuppressWarnings("unchecked")
	public static boolean isSortedArrayStable(CountedItem[] A) {
		if(isSorted(A)) {
			for(int i = 0; i < A.length-1; i++) {
				if(A[i].equals(A[i+1])) {
					if(A[i+1].earlier(A[i])){
					return false;
					}
				}
			}
			return true;
		}
		
		return false;
	}
	
	/** 
	 * Given an array of integers, return a CountedItem<Integer> array. If you construct
	 * and add the objects from left to right, then for two duplicate values A[i] and A[j],
	 * you know that the counter for A[i] is smaller than the counter for A[j] if i < j. 
	 */
	static CountedItem<Integer>[] toCountedArray(Integer vals[]) {
		CountedItem<Integer>[] copy = new CountedItem[vals.length];
		for (int i  = 0; i < copy.length; i++) {
			copy[i] = new CountedItem<>(vals[i]);
		}
		
		return copy;
	}
	
	public static void trial1_1() {
		System.out.println("Q1.1");
		
		// create array of integers with opportunities for duplicates
		Integer vals[] = new Integer[4096];
		for (int i = 0; i < vals.length; i++) { vals[i] = StdRandom.uniform(128); }
		
		// using this SAME ARRAY, create different CountedItem<> arrays and 
		CountedItem<Integer>[] c1 = toCountedArray(vals);
		CountedItem<Integer>[] c2 = toCountedArray(vals);
		CountedItem<Integer>[] c3 = toCountedArray(vals);
		CountedItem<Integer>[] c4 = toCountedArray(vals);
		CountedItem<Integer>[] c5 = toCountedArray(vals);
		CountedItem<Integer>[] c6 = toCountedArray(vals);
		CountedItem<Integer>[] c7 = toCountedArray(vals);
		
		// determine which of the sorting algorithms are stable, and which ones are not.
		heapSort(c1);
		System.out.println("HeapSort:\t\t" + isSortedArrayStable(c1));
		insertionSort(c2);
		System.out.println("InsertionSort:\t\t" + isSortedArrayStable(c2));
		mergeSort(c3);
		System.out.println("MergeSort:\t\t" + isSortedArrayStable(c3));
		quickSort(c4);
		System.out.println("QuickSort:\t\t" + isSortedArrayStable(c4));
		selectionSort(c5);
		System.out.println("SelectionSort:\t\t" + isSortedArrayStable(c5));
		primitiveTimSort(c6);
		System.out.println("TimSort Primitive:\t" + isSortedArrayStable(c6));
		builtinSort(c7);
		System.out.println("TimSort Optimized:\t" + isSortedArrayStable(c7));
	}
	
	public static void trial1_2() {
		System.out.println("Q1.2");
		int N = 1048576;
		System.out.println("\tN\tHeap\tMerge\tPrimTS\tQuick\tTimSort");
		while(N <= 16777216) {
			Integer[] A = new Integer[N];
			for(int i = 0; i < N; i++) {
				A[i] = StdRandom.uniform(N);
			}
			
			CountedItem<Integer>[] c1 = toCountedArray(A);
			CountedItem<Integer>[] c2 = toCountedArray(A);
			CountedItem<Integer>[] c3 = toCountedArray(A);
			CountedItem<Integer>[] c4 = toCountedArray(A);
			CountedItem<Integer>[] c5 = toCountedArray(A);
			
			System.out.println(String.format("%8d\t%5.4f\t%5.4f\t%5.4f\t%5.4f\t%5.4f", 
					N, heapSort(c1), mergeSort(c2), primitiveTimSort(c3), quickSort(c4), builtinSort(c5) ));
			N*=2;
		}
		
		// completed by student
	}
	
	public static void trial1_3() {
		System.out.println("Q1.3");
		int N = 1048576;
		System.out.println("\tN\tHeap\tMerge\tPrimTS\tQuick\tTimSort");
		while(N <= 16777216) {
			Integer[] A = new Integer[N];
			for(int i = 0; i < N; i++) {
				A[i] = StdRandom.uniform(N/512);
			}
			
			CountedItem<Integer>[] c1 = toCountedArray(A);
			CountedItem<Integer>[] c2 = toCountedArray(A);
			CountedItem<Integer>[] c3 = toCountedArray(A);
			CountedItem<Integer>[] c4 = toCountedArray(A);
			CountedItem<Integer>[] c5 = toCountedArray(A);
			System.out.println(String.format("%8d\t%5.4f\t%5.4f\t%5.4f\t%5.4f\t%5.4f", 
					N, heapSort(c1), mergeSort(c2), primitiveTimSort(c3), quickSort(c4), builtinSort(c5) ));
			N*=2;
		}
		// completed by student
	}
	
	public static void trial1_4() {
		System.out.println("Q1.4");
		int N = 1048576;
		int copy = N;
		System.out.println("\tN\tHeap\tMerge\tPrimTS\tQuick\tTimSort");
		while(N <= 16777216) {
			Integer[] A = new Integer[N];
			for(int i = 0; i < N; i++) {
				A[i] = copy;
				copy--;
			}
			
			CountedItem<Integer>[] c1 = toCountedArray(A);
			CountedItem<Integer>[] c2 = toCountedArray(A);
			CountedItem<Integer>[] c3 = toCountedArray(A);
			CountedItem<Integer>[] c4 = toCountedArray(A);
			CountedItem<Integer>[] c5 = toCountedArray(A);
			System.out.println(String.format("%8d\t%5.4f\t%5.4f\t%5.4f\t%5.4f\t%5.4f", 
					N, heapSort(c1), mergeSort(c2), primitiveTimSort(c3), quickSort(c4), builtinSort(c5) ));				
			N*=2;
		}
		// completed by student
	}
	
	public static void main(String[] args) {
		trial1_1();
		trial1_2();
		trial1_3();
		trial1_4();
	}
}
