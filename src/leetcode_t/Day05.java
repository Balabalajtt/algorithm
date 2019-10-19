package leetcode_t;

import java.util.*;

/**
 * Created by 江婷婷 on 2019/10/19.
 */
public class Day05 {}

/*
 给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。

 示例:
 输入: [1,2,3,4]
 输出: [24,12,8,6]
 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。

 进阶：
 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 */
class T_238 {
    public int[] productExceptSelf(int[] nums) {
        int[] ans = new int[nums.length];
        Arrays.fill(ans, 1);
        int left = 1;
        int right = 1;
        for (int i = 1; i < nums.length; i++) {
            left *= nums[i - 1];
            ans[i] *= left;
            right *= nums[nums.length - i];
            ans[nums.length - i - 1] *= right;
        }
        return ans;
    }
}

/*
 给定一个整数数组，判断是否存在重复元素。
 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
 */
class T_217 {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }
}

/*
 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。

 示例 1:
 输入:
 [
  [ 1, 2, 3 ],
  [ 4, 5, 6 ],
  [ 7, 8, 9 ]
 ]
 输出: [1,2,3,6,9,8,7,4,5]
 */
class T_54 {
    // 同剑指offer T_19
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        int m = matrix.length;
        if (m == 0) return ans;
        int n = matrix[0].length;
        if (n == 0) return ans;
        int count = (Math.min(m, n) + 1) / 2;
        for (int k = 0, i, j; k < count; k++) {
            for (i = k, j = k; j < n - k; ans.add(matrix[i][j++]));
            for (i++, j--; i < m - k; ans.add(matrix[i++][j]));
            for (i--, j--; j >= k && k + 1 < m - k; ans.add(matrix[i][j--])) ;
            for (i--, j++; i > k && k < n - k - 1; ans.add(matrix[i--][j])) ;
        }
        return ans;
    }
}

/*
 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。

 示例:
 输入: 3
 输出:
 [
  [ 1, 2, 3 ],
  [ 8, 9, 4 ],
  [ 7, 6, 5 ]
 ]
 */
class T_59 {

    public int[][] generateMatrix_01(int n) {
        int[][] ans = new int[n][n];
        int limit = n * n;
        int num = 1;
        for (int k = 0, i, j; num <= limit; k++) {
            for (i = k, j = k; j < n - k; ans[i][j++] = num++);
            for (i++, j--; i < n - k; ans[i++][j] = num++);
            for (i--, j--; j >= k && k + 1 < n - k; ans[i][j--] = num++) ;
            for (i--, j++; i > k && k < n - k - 1; ans[i--][j] = num++) ;
        }
        return ans;
    }

    // 边界法
    public int[][] generateMatrix_02(int n) {
        int[][] ans = new int[n][n];
        int limit = n * n;
        int num = 1;
        int t = 0, r = n - 1, b = n - 1, l = 0, i;
        while (num <= limit) {
            for (i = l; i <= r; i++) ans[t][i] = num++;
            t++;
            for (i = t; i <= b; i++) ans[i][r] = num++;
            r--;
            for (i = r; i >= l; i--) ans[b][i] = num++;
            b--;
            for (i = b; i >= t; i--) ans[i][l] = num++;
            l++;
        }
        return ans;
    }
}

/*
 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。

 说明:
 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。

 示例:
 输入:
 nums1 = [1,2,3,0,0,0], m = 3
 nums2 = [2,5,6],       n = 3
 输出: [1,2,2,3,5,6]
 */
class T_88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        m--;
        n--;
        for (int i = nums1.length - 1; i >= 0; i--) {
            if (m < 0) {
                nums1[i] = nums2[n--];
            } else if (n < 0) {
                nums1[i] = nums1[m--];
            } else if (nums1[m] > nums2[n]) {
                nums1[i] = nums1[m--];
            } else {
                nums1[i] = nums2[n--];
            }
        }
    }
}

/*
 反转一个单链表。

 示例:
 输入: 1->2->3->4->5->NULL
 输出: 5->4->3->2->1->NULL

 进阶:
 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 */
class T_206 {
    public ListNode reverseList_01(ListNode head) {
        ListNode p = null;
        ListNode ans = null;
        while (head != null) {
            ans = head;
            head = head.next;
            ans.next = p;
            p = ans;
        }
        return ans;
    }

    public ListNode reverseList_02(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode ans = reverseList_02(head.next);
        head.next.next = head;
        head.next = null;
        return ans;
    }
}

/*
 给出两个 非空 的链表用来表示两个非负的整数。
 其中，它们各自的位数是按照逆序的方式存储的，并且它们的每个节点只能存储一位数字。
 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

 示例：
 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 输出：7 -> 0 -> 8
 原因：342 + 465 = 807
 */
class T_2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p = new ListNode(-1);
        ListNode head = p;
        int j = 0;
        while (l1 != null || l2 != null) {
            int val = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + j;
            j = val / 10;
            p.next = new ListNode(val % 10);
            p = p.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        if (j != 0) {
            p.next = new ListNode(j);
            p = p.next;
            p.next = null;
        }
        return head.next;
    }
}

/*
 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

 示例：
 输入：1->2->4, 1->3->4
 输出：1->1->2->3->4->4
 */
class T_21 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode p = head;
        for (; l1 != null && l2 != null; p = p.next) {
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
        }
        p.next = l1 == null ? l2 : l1;
        return head.next;
    }
}

/*
 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 示例:
 输入:
 [
   1->4->5,
   1->3->4,
   2->6
 ]
 输出: 1->1->2->3->4->4->5->6
 */
class T_23 {

    // 优先队列 O(nlogk)
    public ListNode mergeKLists_01(ListNode[] lists) {
        ListNode head = new ListNode(-1);
        ListNode p = head;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode list : lists) {
            if (list != null) {
                pq.add(list);
            }
        }
        while (!pq.isEmpty()) {
            ListNode min = pq.poll();
            p.next = min;
            p = p.next;
            if (min.next != null) {
                pq.add(min.next);
            }
        }
        return head.next;
    }

    // 分治 O(nlogk)
    public ListNode mergeKLists_02(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int start, int end) {
        if (start > end) {
            return null;
        } if (start == end) {
            return lists[start];
        } else if (start + 1 == end) {
            ListNode l1 = lists[start];
            ListNode l2 = lists[end];
            ListNode head = new ListNode(-1);
            ListNode p = head;
            for (; l1 != null && l2 != null; p = p.next) {
                if (l1.val < l2.val) {
                    p.next = l1;
                    l1 = l1.next;
                } else {
                    p.next = l2;
                    l2 = l2.next;
                }
            }
            p.next = l1 == null ? l2 : l1;
            return head.next;
        } else {
            int mid = start + (end - start) / 2;
            ListNode l1 = merge(lists, start, mid);
            ListNode l2 = merge(lists, mid + 1, end);
            return merge(new ListNode[]{l1, l2}, 0, 1);
        }
    }
}