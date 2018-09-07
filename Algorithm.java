package com.ktz;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class GroupNode {
    public int val;
    public List<GroupNode> afters;
    public boolean visited;

    public GroupNode(int val) {
        this.val = val;
        afters = new ArrayList<>();
        visited = false;
    }
}

public class Algorithm {
    //交换排序
    //1.冒泡排序   算法复杂度为O(n2)   稳定排序
    //冒泡排序第一个版本
    public void bubbleSort1(int[] nums) {
        int len = nums.length;
        if (len == 0)
            return;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                if (nums[j] > nums[j + 1])
                    swap(nums, j, j + 1);
            }
        }
    }

    //冒泡排序第二个版本
    public void bubbleSort2(int[] nums) {
        int len = nums.length;
        boolean changed;
        do {
            len--;
            changed = false;
            for (int i = 0; i < len; i++) {
                if (nums[i] > nums[i + 1]) {
                    swap(nums, i, i + 1);
                    changed = true;
                }
            }
        } while (changed);
    }

    //冒泡排序第三个版本
    public void bubbleSort3(int[] nums) {
        int n = nums.length;
        int newn;
        do {
            newn = 0;
            for (int i = 1; i < n; i++) {
                if (nums[i - 1] > nums[i]) {
                    swap(nums, i - 1, i);
                    newn = i;
                }
            }
            n = newn;
        } while (newn > 0);
    }

    //2.鸡尾酒排序 算法复杂度为O(n2)   稳定排序
    public void cocktailSort2(int[] nums) {
        int len = nums.length;
        if (len == 0)
            return;
        int right = len - 1;
        int left = 0;
        while (left < right) {
            for (int i = left; i < right; i++) {
                if (nums[i] < nums[i + 1]) {
                    swap(nums, i, i + 1);
                }
            }
            right--;
            for (int i = right; i > left; i--) {
                if (nums[i - 1] > nums[i]) {
                    swap(nums, i - 1, i);
                }
            }
            left++;
        }
    }

    //3.快速排序 算法复杂度为O(nlogn)  不稳定的排序算法
    public void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private void quickSort(int[] nums, int head, int tail) {
        if (head >= tail || nums == null || nums.length <= 1)
            return;
        int i = head;
        int j = tail;
        int pivot = nums[(head + tail) >> 1];
        while (i <= j) {
            while (nums[i] < pivot)
                i++;
            while (nums[j] > pivot)
                j--;
            if (i < j) {
                swap(nums, i, j);
                i++;
                j--;
            } else if (i == j) {
                i++;
            }
        }
        quickSort(nums, head, j);
        quickSort(nums, i, tail);
    }

    //选择排序
    //1.选择排序  时间复杂度为O(n2)  不稳定的排序算法
    public void selectionSort(int[] nums) {
        int len = nums.length;
        if (len == 0) return;
        int min;
        for (int i = 0; i < len; i++) {
            min = i;
            for (int j = i + 1; j < len; j++) {
                if (nums[min] > nums[j]) min = j;
            }
            if (min != i) swap(nums, min, i);
        }
    }

    //插入排序
    //1.插入排序   时间复杂度为O(n2)  稳定的排序算法
    public void insertionSort(int[] nums) {
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            int cur = nums[i];
            int j = i - 1;
            while (j >= 0 && nums[j] > cur) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = cur;
        }
    }

    //2.希尔排序  最佳版本O(nlogn) 不稳定的排序算法
    public void shellSort(int[] nums) {
        int len = nums.length;
        int step = len / 2;
        int cur;
        int i;
        int j;
        while (step >= 1) {
            for (i = step; i < len; i++) {
                cur = nums[i];
                j = i - step;
                while (j >= 0 && nums[j] > cur) {
                    nums[j + step] = nums[j];
                    j -= step;
                }
                nums[j + step] = cur;
            }
            step /= 2;
        }
    }

    //归并排序
    public void mergeSort(int[] nums) {
        int len = nums.length;
        if (len == 0) return;
        int[] result = new int[len];
        mergeSort(nums, result, 0, len - 1);
    }

    private void mergeSort(int[] nums, int[] result, int start, int end) {
        if (start >= end)
            return;
        int len = end - start;
        int mid = (len >> 1) + start;
        int start1 = start;
        int end1 = mid;
        int start2 = mid + 1;
        int end2 = end;
        mergeSort(nums, result, start1, end1);
        mergeSort(nums, result, start2, end2);

        //归并操作
        int k = start;
        while (start1 <= end1 && start2 <= end2) {
            result[k++] = nums[start1] < nums[start2] ? nums[start1++] : nums[start2++];
        }
        while (start1 <= end1) {
            result[k++] = nums[start1++];
        }
        while (start2 <= end2) {
            result[k++] = nums[start2++];
        }
        for (int i = start; i <= end; i++) {
            nums[i] = result[i];
        }
    }

    //搜索算法
    //深度优先搜索
    public static void deepSearch(GroupNode node) {
        if (node == null) return;
        ArrayDeque<GroupNode> queue = new ArrayDeque<>();
        queue.addLast(node);
        node.visited = true;
        while (!queue.isEmpty()) {
            GroupNode cur = queue.poll();
            System.out.println(cur.val);
            for (GroupNode tmp : cur.afters) {
                if (!tmp.visited) {
                    queue.addLast(tmp);
                    tmp.visited = true;
                }
            }
        }
    }

    //广度优先搜索
    public static void wideSearch(GroupNode node) {
        if (node == null) return;
        //存储后继节点
        Stack<GroupNode> stack = new Stack<>();
        //存储上一级节点
        Stack<GroupNode> stack1 = new Stack<>();
        stack.push(node);
        node.visited = true;
        System.out.print(node.val);
        while (!stack.isEmpty() || !stack1.isEmpty()) {
            GroupNode cur;
            if (stack.isEmpty()) {
                cur = stack1.pop();
            } else {
                cur = stack.pop();
            }
            //判断当前节点的子节点是否被遍历完
            boolean isRepush = false;
            for (GroupNode tmp : cur.afters) {
                if (!tmp.visited) {
                    System.out.print(tmp.val);
                    stack.push(tmp);
                    tmp.visited = true;
                    isRepush = true;
                    break;
                }
            }
            if (isRepush) {
                stack1.push(cur);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
}
