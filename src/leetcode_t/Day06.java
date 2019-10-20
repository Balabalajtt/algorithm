package leetcode_t;

/**
 * Created by 江婷婷 on 2019/10/20.
 */
public class Day06 {}

/*
 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。

 示例 1:
 输入: 1->2->3->4->5->NULL, k = 2
 输出: 4->5->1->2->3->NULL
 解释:
 向右旋转 1 步: 5->1->2->3->4->NULL
 向右旋转 2 步: 4->5->1->2->3->NULL

 示例 2:
 输入: 0->1->2->NULL, k = 4
 输出: 2->0->1->NULL
 解释:
 向右旋转 1 步: 2->0->1->NULL
 向右旋转 2 步: 1->2->0->NULL
 向右旋转 3 步: 0->1->2->NULL
 向右旋转 4 步: 2->0->1->NULL
 */
class T_61 {
    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;
        }
        ListNode p = head;
        int length = 1;
        while (p.next != null) {
            p = p.next;
            length++;
        }
        p.next = head;
        p = head;
        k = k % length;
        for (int i = 0; i < length - k - 1; i++) {
            p = p.next;
        }
        head = p.next;
        p.next = null;
        return head;
    }
}

/*
 给定一个链表，判断链表中是否有环。
 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 如果 pos 是 -1，则在该链表中没有环。

 示例 1：
 输入：head = [3,2,0,-4], pos = 1
 输出：true
 解释：链表中有一个环，其尾部连接到第二个节点。
 */
class T_141 {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next;
            if (fast == slow) {
                return true;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return false;
    }
}

/*
 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 如果 pos 是 -1，则在该链表中没有环。
 说明：不允许修改给定的链表。

 示例 1：
 输入：head = [3,2,0,-4], pos = 1
 输出：tail connects to node index 1
 解释：链表中有一个环，其尾部连接到第二个节点。
 */
class T_142 {
    public ListNode detectCycle_01(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next;
            if (fast == slow) {
                ListNode p = fast.next;
                int length = 1;
                // 计算环大小
                while (p != fast) {
                    p = p.next;
                    length++;
                }
                slow = head;
                fast = head.next;
                // 双指针 快指针先走环大小步
                for (int i = 1; fast != slow; i++) {
                    fast = fast.next;
                    if (i >= length) {
                        slow = slow.next;
                    }
                }
                return fast;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return null;
    }

    public ListNode detectCycle_02(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                slow = head;
                // 推导发现在相遇后，slow从head开始，fast从相遇点开始，每次都走一步
                // 再次相遇的点即为环入口
                // n 非环长度 m 环长度 q入口到相遇点的长度  x、y常数
                // 2(n + xm + q) == n + ym + q
                // n = (m - q) + (y - 2x + 1)m
                // 则相当于slow走到入口时，fast刚好走了几圈加上相遇点回到环入口的距离
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }
        return null;
    }
}

/*
 编写一个程序，找到两个单链表相交的起始节点。
 */
class T_160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode p = headA;
        ListNode q = headB;
        // la长m+k lb长n+k
        // la走完自己的 再走lb的
        // lb走完自己的 再走la的
        // 最后 m+k+n == n+k+m 相遇点就是相交点
        while (p != q) {
            p = p == null ? headB : p.next;
            q = q == null ? headA : q.next;
        }
        return p;
    }
}

/*
 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。
 */
class T_237 {
    public void deleteNode(ListNode node) {
        int t = node.val;
        node.val = node.next.val;
        node.next.val = t;
        node.next = node.next.next;
    }
}

/*
给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 示例 1:
 输入: 123
 输出: 321
 */
class T_7 {
    public static void main(String[] args) {
        System.out.println(new T_7().reverse(-123));
    }
    public int reverse(int x) {
        int ans = 0;
        int cur;
        while (x != 0) {
            cur = x % 10;
            // int最高位的十进制值
            if (ans > Integer.MAX_VALUE / 10 || ans < Integer.MIN_VALUE / 10
                    || (ans == Integer.MAX_VALUE && cur > 7)
                    || (ans == Integer.MIN_VALUE && cur < -8)) {
                return 0;
            }
            ans = ans * 10 + x % 10;
            x = x / 10;
        }
        return ans;
    }
}