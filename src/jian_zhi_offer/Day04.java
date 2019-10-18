package jian_zhi_offer;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by 江婷婷 on 2019/10/18.
 */
public class Day04 {}

/*
 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 */
class T_16 {
    public ListNode Merge(ListNode list1,ListNode list2) {
        ListNode ans = new ListNode(-1), p = ans;
        for ( ; list1 != null && list2 != null; p = p.next) {
            if (list1.val < list2.val) {
                p.next = list1;
                list1 = list1.next;
            } else {
                p.next = list2;
                list2 = list2.next;
            }
        }
        p.next = list1 != null ? list1 : list2;
        return ans.next;
    }
}

/*
 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 */
class T_17 {
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if (root1 == null || root2 == null) {
            return false;
        }
        return root1.val == root2.val && check(root1, root2)
                || HasSubtree(root1.left, root2)
                || HasSubtree(root1.right, root2);
    }

    private boolean check(TreeNode a, TreeNode b) {
        if (b == null) {
            return true;
        }
        if (a == null || a.val != b.val) {
            return false;
        }
        return check(a.left, b.left) && check(a.right, b.right);
    }
}

/*
 操作给定的二叉树，将其变换为源二叉树的镜像。
 */
class T_18 {
    // 先序后序都可以
    public void Mirror(TreeNode root) {
        if (root == null) {
            return;
        }
        Mirror(root.left);
        Mirror(root.right);
        TreeNode t = root.left;
        root.left = root.right;
        root.right = t;
    }
}

/*
 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
 例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 */
class T_19 {
    public static void main(String[] args) {
        System.out.println(new T_19().printMatrix_02(new int[][]{{1}, {2}, {3}, {4}}));
    }

    public ArrayList<Integer> printMatrix_01(int[][] matrix) {
        ArrayList<Integer> ans = new ArrayList<>();
        int i = -1, j = -1, end = matrix[0].length, bottom = matrix.length, start = -2, top = -1;
        for ( ; bottom - top > 1 && end - start > 1; ) {
            for (j++, i++, start++; j < end && bottom - top > 1; ans.add(matrix[i][j++])) ;
            for (i++, j--, top++; i < bottom && end - start > 1; ans.add(matrix[i++][j])) ;
            for (j--, i--, end--; j > start && bottom - top > 1; ans.add(matrix[i][j--])) ;
            for (i--, j++, bottom--; i > top && end - start > 1; ans.add(matrix[i--][j])) ;
        }
        return ans;
    }

    public ArrayList<Integer> printMatrix_02(int[][] matrix) {
        ArrayList<Integer> ans = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        if (n == 0 || m == 0) {
            return ans;
        }
        int count = (Math.min(m, n) + 1) / 2;
        int i, j, top, right;
        for (int k = 0; k < count; k++) {
            for (i = k, j = k; j < n - k; ans.add(matrix[i][j++]));
            top = i;
            for (i++, j--; i < m - k; ans.add(matrix[i++][j]));
            right = j;
            for (i--, j--; j >= k && i != top; ans.add(matrix[i][j--]));
            for (i--, j++; i > k && j != right; ans.add(matrix[i--][j]));

        }
        return ans;
    }
}

/*
 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
 */
class T_20 {

    Stack<Integer> stack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();

    public void push(int node) {
        stack.push(node);
        if (minStack.isEmpty() || minStack.peek() > node) {
            minStack.push(node);
        }
    }

    public void pop() {
        if (stack.pop().equals(minStack.peek())) {
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return minStack.peek();
    }
}

/*
 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。假设压入栈的所有数字均不相等。
 例如序列1,2,3,4,5是某栈的压入顺序，
 序列4,5,3,2,1是该压栈序列对应的一个弹出序列，
 但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
 */
class T_21 {
    public boolean IsPopOrder(int[] pushA, int[] popA) {
        if (pushA.length != popA.length) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0, j = 0; i < pushA.length; ) {
            stack.push(pushA[i++]);
            while (j < popA.length && !stack.isEmpty() && stack.peek() == popA[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }
}