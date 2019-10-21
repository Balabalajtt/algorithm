package leetcode_t;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by 江婷婷 on 2019/10/21.
 */
public class Day07 {}

/*
 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 */
class T_9 {
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;
        int y = 0;
        while (x > y) {
            y = y * 10 + x % 10;
            x /= 10;
        }
        return x == y || x == y / 10;
    }
}

/*
 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 说明：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 */
class T_136 {
    // 0^a=a, a^a=0
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            ans ^= num;
        }
        return ans;
    }
}

/*
 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
 说明：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 */
class T_137 {

    public int singleNumber_01(int[] nums) {
        HashSet<Long> set = new HashSet<>();
        long sum = 0;
        // 3*(a+b+c)-(a+b+b+b+c+c+c) == 2*a
        for (long num : nums) {
            sum -= num;
            if (!set.contains(num)) {
                set.add(num);
                sum += 3 * num;
            }
        }
        return (int) (sum / 2);
    }

    // 统计每位出现次数
    public int singleNumber_02(int[] nums) {
        int ans = 0;
        int count = 0;
        for (int pos = 1; pos != 0; pos <<= 1, count = 0) {
            for (int num : nums) if ((num & pos) != 0) count++;
            ans = count % 3 == 0 ? ans : ans|pos;
        }
        return ans;
    }

    // one的第k位为1 two的第k位为0 表示num的第k位出现了 3*m+1次
    // one的第k位为0 two的第k位为1 表示num的第k位出现了 3*m+2次
    // one的第k位为0 two的第k位为0 表示num的第k位出现了 3*m+3次
    public int singleNumber_03(int[] nums) {
        int one = 0, two = 0;
        for (int num : nums) {
            one = one ^ num & ~two;
            two = two ^ num & ~one;
        }
        return one;
    }
}

/*
 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 你可以假设数组是非空的，并且给定的数组总是存在众数。
 */
class T_169 {
    public int majorityElement(int[] nums) {
        int ans = -1;
        int count = 0;
        for (int num : nums) {
            if (count == 0) {
                ans = num;
            }
            count += (ans == num) ? 1 : -1;
        }
        return ans;
    }
}

/*
 给定一个大小为 n 的数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1)。
 */
class T_229 {
    public List<Integer> majorityElement(int[] nums) {
        if (nums.length < 1) return new ArrayList<>();
        int n1, n2, c1, c2;
        n1 = n2 = nums[0];
        c1 = c2 = 0;
        for (int num : nums) {
            if (n1 == num) {
                c1++;
            } else if (n2 == num){
                c2++;
            } else if (c1 == 0){
                n1 = num;
                c1++;
            } else if (c2 == 0) {
                n2 = num;
                c2++;
            } else {
                c1--;
                c2--;
            }
        }

        c1 = c2 = 0;
        for (int num : nums) {
            if (num == n1) c1++;
            if (num == n2) c2++;
        }
        ArrayList<Integer> list = new ArrayList<>();
        if (c1 * 3 > nums.length) list.add(n1);
        if (n1 != n2 && c2 * 3 > nums.length) list.add(n2);
        return list;
    }
}

/*
 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
 */
class T_231 {
    public boolean isPowerOfTwo_01(int n) {
        if (n <= 0) return false;
        for (n--; n != 0; n >>= 1) {
            if ((n&1) != 1) return false;
        }
        return true;
    }

    // 如果n是2的幂次方，n&(n-1) == 0
    public boolean isPowerOfTwo_02(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}