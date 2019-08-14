package com.wiserv.util.sort;

import java.util.Arrays;

/**
 * 排序算法整理
 */
public class SortAlgorithm {


	/**
	 * 直接插入排序算法
	 */
	public static void insertSort(int[] a) {
		for(int i = 1;i < a.length;i++) {
			if(a[i] < a[i-1]) {
				int j = i-1;
				int x = a[i];
				while(j >= 0 && x < a[j]) {
					a[j+1] = a[j];
					j--;
				}
				a[j+1] = x;
			}
		}
	}

	/**
	 * 希尔排序（插入算法改进）
	 */
	public static void shellSort(int[] a) {	
		int dk = a.length/2;  
	    while( dk >= 1  ){  				//dk为缩进量
	        ShellInsertSort(a,dk);  
	        dk = dk/2;  
	    }  	
	}
	private static void ShellInsertSort(int[] a, int dk) {
		for(int i= dk; i<a.length; ++i){  
	        if(a[i] < a[i-dk]){          
	            int j = i-dk;     
	            int x = a[i];              
	            while( j >= 0 && x < a[j]){     
	                a[j+dk] = a[j];  
	                j -= dk;             
	            }  
	            a[j+dk] = x;            
	        }  
	    } 	
	}
	/**
	 * 选择排序
	 */
	public static void selectSort(int[] a)  {		
		for(int i = 0;i < a.length;i++) {
			int min = i;
			for(int j = i+1;j < a.length;i++) {
				if(a[j] < a[min]) {
					min = j;
				}
			}
			int temp = a[i]; a[i] = a[min]; a[min] = temp;
		}
	}

	/**
	 * 	选择排序优化(每一次选择出最大和最小下标)
	 */
	public static void selectSortImproved(int[] a) {
		int min,max,temp;
		for(int i = 1;i <= a.length/2;i++) {
			min = i; max = i;
			for(int j = i+1;j <= a.length -i;j++) {
				if(a[j] < a[min]) {
					min = j;
					continue;
				}
				if(a[j] > a[max]) {
					max = j;
				}
			}
			temp = a[i-1]; a[i-1] = a[min]; a[min] = temp;
			temp = a[a.length-i]; a[a.length-i] = a[max]; a[max] = temp;
		}
	}
	/**
	 * 堆排序
	 */
	public static void heapSort(int[] a) {
		//将序列建立成堆
		for (int i = (a.length -1) / 2 ; i >= 0; i--)  {
			heapAdjust(a,i,a.length);
		}
		//交换栈顶元素，对剩下元素建立大顶堆
		for (int i = a.length - 1; i > 0;i--) {			//交换堆顶元素重新建堆
			int temp = a[i]; a[i] = a[0]; a[0] = temp;
			heapAdjust(a, 0, i);
		}
	}

	/**
	 *
	 * @param a
	 * @param s
	 * @param length
	 */
	private static void heapAdjust(int a[],int s,int length) {
	    int tmp  = a[s];  
	    int child = 2 * s + 1;
	    while (child < length) {  
	        if(child + 1 < length && a[child] < a[child + 1]) {
	            ++ child ;
	        }  
	        if(a[s] < a[child]) {  
	            a[s] = a[child]; 
	            s = child;         
	            child = 2 * s + 1;
	        }  else {            
	             break;  
	        }  
	        a[s] = tmp;        
	    }  
	}
	/**
	 * 冒泡排序
	 */
	public static void bubbleSort(int[] a) {
		for(int i = 0;i < a.length -1;i++) {
			for(int j = 0; j < a.length-1;j++) {
				if(a[j] > a[j+1]) {
					int temp = a[j];
					a[j] = a[j+1];
					a[j+1] = temp;
				}
			}
		}
	}

	/**
	 * 冒泡算法改进1(记录交换的位置)
	 */
	public static void bubbleSortImproved1(int[] a) {
		  
	    int i= a.length -1;  
	    while ( i > 0) {   
	        int pos= 0; 
	        for (int j= 0; j < i; j++)  
	            if (a[j] > a[j+1]) {  
	                pos= j;  
	                int tmp = a[j]; a[j] = a[j+1]; a[j+1] = tmp;  
	            }   
	        i= pos;   
	     }   
		
	}
	/**
	 * 每一趟同时向两边冒泡
	 */
	public static void bubbleSortImproved2(int[] a) {
	    int low = 0;   
	    int high= a.length -1; 
	    int tmp,j;  
	    while (low < high) {  
	        for (j= low; j < high; ++j) 
	            if (a[j]> a[j+1]) {  
	                tmp = a[j]; a[j] = a[j+1]; a[j+1] = tmp;  
	            }   
	        --high;                  
	        for ( j=high; j > low; --j)   
	            if (a[j] < a[j-1]) {  
	                tmp = a[j]; a[j]=a[j-1];a[j-1]=tmp;  
	            }  
	        ++low;                 
	    } 
	}
	/**
	 * 	交换排序（快速排序）
	 */
	public static void quicksort(int[] a, int left, int right) {
        int dp;
        if (left < right) {
            dp = partition(a, left, right);
            quicksort(a, left, dp - 1);
            quicksort(a, dp + 1, right);
        }
	    }
	 
	 private static int partition(int a[], int left, int right) {
        int pivot = a[left];
        while (left < right) {
            while (left < right && a[right] >= pivot)
                right--;
            if (left < right)
                a[left++] = a[right];
            while (left < right && a[left] <= pivot)
                left++;
            if (left < right)
                a[right--] = a[left];
        }
        a[left] = pivot;
        return left;
	   }
	/**
	 * 归并算法
	 */
	public static int[] mergeSort(int[] a, int low, int high) {  
        int mid = (low + high) / 2;  
        if (low < high) {  	            
        	mergeSort(a, low, mid);
        	mergeSort(a, mid + 1, high);             
            merge(a, low, mid, high); 
        }  
        return a;  
	    }  
	  
    public static void merge(int[] a, int low, int mid, int high) {  
        int[] temp = new int[high - low + 1];  
        int i = low;// 左指针
        int j = mid + 1;// 右指针
        int k = 0;           
        while (i <= mid && j <= high) {  		// 把较小的数先移到新数组中
            if (a[i] < a[j]) {  
                temp[k++] = a[i++];  
            } else {  
                temp[k++] = a[j++];  
            }  
        }             
        while (i <= mid) {  					 // 把左边剩余的数移入数组
            temp[k++] = a[i++];  
        }      
        while (j <= high) {  					// 把右边边剩余的数移入数组
            temp[k++] = a[j++];  
        }          										
        for (int k2 = 0; k2 < temp.length; k2++) {  // 把新数组中的数覆盖a数组
            a[k2 + low] = temp[k2];  
        }  
    }
	/**
	 * 	基数排序（多关键字排序）
	 */
    public static void radixSort(int[] a,int radix,int d) {
    	 // 缓存数组
        int[] tmp = new int[a.length];  
        // buckets用于记录待排序元素的信息
        // buckets数组定义了max-min个桶
        int[] buckets = new int[radix];  
  
        for (int i = 0, rate = 1; i < d; i++) {  
 
            // 重置count数组，开始统计下一个关键字
            Arrays.fill(buckets, 0);  
            // 将data中的元素完全复制到tmp数组中
            System.arraycopy(a, 0, tmp, 0, a.length);  
  
            // 计算每个待排序数据的子关键字
            for (int j = 0; j < a.length; j++) {  
                int subKey = (tmp[j] / rate) % radix;  
                buckets[subKey]++;  
            }  
  
            for (int j = 1; j < radix; j++) {  
                buckets[j] = buckets[j] + buckets[j - 1];  
            }  
  
            // 按子关键字对指定的数据进行排序
            for (int m = a.length - 1; m >= 0; m--) {  
                int subKey = (tmp[m] / rate) % radix;  
                a[--buckets[subKey]] = tmp[m];  
            }  
            rate *= radix;  
        }  
    }
}
