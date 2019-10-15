package jian_zhi_offer;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by 江婷婷 on 2019/10/14.
 */
public class Day01 {}

/*
 在一个二维数组中（每个一维数组的长度相同），
 每一行都按照从左到右递增的顺序排序，
 每一列都按照从上到下递增的顺序排序。
 请完成一个函数，输入这样的一个二维数组和一个整数，
 判断数组中是否含有该整数。
 */
class T_01 {
    public boolean Find(int target, int [][] array) {
        if (array.length < 1) {
            return false;
        }
        for (int i = array.length - 1, j = 0; i >= 0 && j < array[0].length; ) {
            if (array[i][j] > target) {
                i--;
            } else if (array[i][j] < target) {
                j++;
            } else {
                return true;
            }
        }
        return false;
    }
}

/*
 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 */
class T_02 {
    public String replaceSpace(StringBuffer str) {
//        return str.toString().replaceAll(" ", "%20");
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                count++;
            }
        }
        int oldLength = str.length();
        int length = oldLength + 2 * count;
        str.setLength(length);
        for (int i = oldLength - 1, j = str.length() - 1; i < j; i--) {
            if (str.charAt(i) == ' ') {
                str.setCharAt(j--, '0');
                str.setCharAt(j--, '2');
                str.setCharAt(j--, '%');
            } else {
                str.setCharAt(j--, str.charAt(i));
            }
        }
        return str.toString();
    }
}

/*
 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
 */
class T_03 {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if (listNode == null) {
            return new ArrayList<>();
        }
        ArrayList<Integer> list = printListFromTailToHead(listNode.next);
        list.add(listNode.val);
        return list;
    }
}

/*
 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 */
class T_04 {
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        return construct(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    private TreeNode construct(int[] pre, int pS, int pE, int[] in, int iS, int iE) {
        if (pE < pS) {
            return null;
        }
        TreeNode root = new TreeNode(pre[pS]);
        int divide = iS;
        while (root.val != in[divide]) {
            divide++;
        }
        root.left = construct(pre, pS + 1, pS - iS + divide, in, iS, divide - 1);
        root.right = construct(pre, pE - iE + divide + 1, pE, in, divide + 1, iE);
        return root;
    }
}

/*
 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 */
class T_05 {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        if (stack2.empty()) {
            throw new RuntimeException("Queue is Empty!");
        }
        return stack2.pop();
    }
}

/*
 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 */
class T_06 {
    public int minNumberInRotateArray_01(int[] array) {
        int i = array.length == 0 ? 0 : array.length - 1;
        while (i > 0 && array[i - 1] <= array[i]) {
            i--;
        }
        return array[i];
    }

    public int minNumberInRotateArray_02(int[] array) {
        int low = 0;
        int high = array.length - 1;
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (array[mid] <= array[high]) {
                high = mid;
            } else if (array[mid] >= array[low]) {
                low = mid;
            }
        }
        return array[high];
    }
}

/*
 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
 n<=39
 */
class T_07 {
    public int Fibonacci(int n) {
        int p = 0;
        int q = 1;
        while (n != 0) {
            q = p + q;
            p = q - p;
            n--;
        }
        return p;
    }
}

/*
 一只青蛙一次可以跳上1级台阶，也可以跳上2级。
 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 */
class T_08 {
    public int JumpFloor_01(int target) {
        if (target <= 0) {
            return 0;
        } else if (target <= 2) {
            return target;
        } else {
            return JumpFloor_01(target - 1) + JumpFloor_01(target - 2);
        }
    }

    public int JumpFloor_02(int target) {
        if (target <= 0) {
            return 0;
        }
        int p = 1;
        int q = 1;
        while (target > 0) {
            q = p + q;
            p = q - p;
            target--;
        }
        return p;
    }
}

/*
 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 */
class T_09 {
    // f(n) = 2 * f(n-1)
    public int JumpFloorII(int target) {
        int count = 1;
        while (--target > 0) {
            count <<= 1;
        }
        return count;
    }
}

/*
 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
 请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 */
class T_10 {
    public int RectCover(int target) {
        if (target == 0) {
            return 0;
        }
        int p = 1;
        int q = 2;
        while (--target > 0) {
            q = p + q;
            p = q - p;
        }
        return p;
    }
}
