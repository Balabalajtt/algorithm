package jian_zhi_offer;

/**
 * Created by 江婷婷 on 2019/10/16.
 */
public class Day02 {}

/*
 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 */
class T_11 {

    public int NumberOf1_01(int n) {
        int count = 0;
        int t = 1;
        while (t != 0) {
            if ((n & t) != 0) {
                count++;
            }
            t <<= 1;
        }
        return count;
    }

    public int NumberOf1_02(int n) {
        int count = 0;
        while (n != 0) {
            n &= n - 1;
            count++;
        }
        return count;
    }
}

/*
 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 保证base和exponent不同时为0
 */
class T_12 {

    public double Power_01(double base, int exponent) {
        if (exponent > 0) {
            return calculate(base, exponent);
        } else if (exponent < 0) {
            return 1 / calculate(base, -1 * exponent);
        } else {
            return 1;
        }
    }

    private double calculate(double base, int exponent) {
        if (exponent == 1) {
            return base;
        } else if (exponent % 2 == 0) {
            double mid = Power_01(base, exponent / 2);
            return mid * mid;
        } else {
            double mid = Power_01(base, (exponent - 1) / 2);
            return base * mid * mid;
        }
    }

    // 快速幂
    public double Power_02(double base, int exponent) {
        int count = Math.abs(exponent);
        int pos = 1;
        double ans = 1;
        while (pos != 0) {
            if ((pos & count) != 0) {
                ans *= base;
            }
            pos <<= 1;
            base *= base;
        }
        return exponent >= 0 ? ans : 1 / ans;
    }
}

/*
 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
 所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 */
class T_13 {
    public void reOrderArray(int [] array) {
        int start = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0 && start == -1) {
                start = i;
            } else if (array[i] % 2 == 1 && start != -1) {
                int cur = array[i];
                System.arraycopy(array, start, array, start + 1, i - start);
                array[start] = cur;
                start++;
            }
        }
    }
}

/*
 输入一个链表，输出该链表中倒数第k个结点。
 */
class T_14 {
    public ListNode FindKthToTail(ListNode head, int k) {
        ListNode p1 = head;
        ListNode p2 = head;
        int i = 1;
        while (p2 != null) {
            if (i++ > k) {
                p1 = p1.next;
            }
            p2 = p2.next;
        }
        return i > k ? p1 : null;
    }
}

/*
 输入一个链表，反转链表后，输出新链表的表头。
 */
class T_15 {

    public ListNode ReverseList_01(ListNode head) {
        ListNode q, p = head;
        head = null;
        while (p != null) {
            q = p;
            p = p.next;
            q.next = head;
            head = q;
        }
        return head;
    }

    public ListNode ReverseList_02(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode ans = ReverseList_02(head.next);
        head.next.next = head;
        head.next = null;
        return ans;
    }
}