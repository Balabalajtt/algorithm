package leetcode_t;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by 江婷婷 on 2019/10/15.
 */
public class Day01 {}

/*
 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 示例:
 给定 nums = [2, 7, 11, 15], target = 9
 因为 nums[0] + nums[1] = 2 + 7 = 9
 所以返回 [0, 1]
 */
class T_001 {
    public int[] twoSum(int[] nums, int target) {
        int[] ans = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                ans[0] = map.get(target - nums[i]);
                ans[1] = i;
                break;
            } else {
                map.put(nums[i], i);
            }
        }
        return ans;
    }
}

/*
 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。

 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 你可以假设 nums1 和 nums2 不会同时为空。

 示例 1:
 nums1 = [1, 3]
 nums2 = [2]
 则中位数是 2.0

 示例 2:
 nums1 = [1, 2]
 nums2 = [3, 4]
 则中位数是 (2 + 3)/2 = 2.5
 */
class T_004 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int k1 = (nums1.length + nums2.length - 1) / 2;
        int k2 = (nums1.length + nums2.length) / 2;
        return (getKth(k1, nums1, nums2) + getKth(k2, nums1, nums2)) / 2.0;
    }

    private int getKth(int k, int[] nums1, int[] nums2) {
        while (nums1.length > 0 && nums2.length > 0) {
            int i1 = nums1.length / 2;
            int i2 = nums2.length / 2;
            if (nums1[i1] < nums2[i2]) {
                if (k <= i1 + i2) {
                    nums2 = Arrays.copyOfRange(nums2, 0, i2);
                } else {
                    k -= i1 + 1;
                    nums1 = Arrays.copyOfRange(nums1, i1 + 1, nums1.length);
                }
            } else {
                if (k <= i1 + i2) {
                    nums1 = Arrays.copyOfRange(nums1, 0, i1);
                } else {
                    k -= i2 + 1;
                    nums2 = Arrays.copyOfRange(nums2, i2 + 1, nums2.length);
                }
            }
        }
        return nums1.length > 0 ? nums1[k] : nums2[k];
    }
}

/*
 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

 示例 1：
 输入: "babad"
 输出: "bab"
 注意: "aba" 也是一个有效答案。

 示例 2：
 输入: "cbbd"
 输出: "bb"
 */
class T_005 {
    public String longestPalindrome(String s) {
        int max = 0;
        int from = 0;
        int to = -1;
        for (int i = 0; i < s.length() && 2 * (s.length() - i) > max; ) {
            int high = i;
            int low = i;
            while (high + 1 < s.length() && s.charAt(high + 1) == s.charAt(i)) {
                high++;
            }
            i = high + 1;
            while (low - 1 >= 0 && high + 1 < s.length() && s.charAt(low - 1) == s.charAt(high + 1)) {
                low--;
                high++;
            }
            if (high - low + 1 > max) {
                max = high - low + 1;
                from = low;
                to = high;
            }
        }
        return s.substring(from, to + 1);
    }
}

/*
 编写一个函数来查找字符串数组中的最长公共前缀。如果不存在公共前缀，返回空字符串 ""。

 示例 1:
 输入: ["flower","flow","flight"]
 输出: "fl"

 示例 2:
 输入: ["dog","racecar","car"]
 输出: ""
 解释: 输入不存在公共前缀。

 说明:所有输入只包含小写字母 a-z 。
 */
class T_014 {

    public String longestCommonPrefix(String[] strs) {
        if (strs.length <= 0) {
            return "";
        } else {
            return strs[0].substring(0, compare(strs, 0, strs.length - 1));
        }
    }

    private int compare(String[] strs, int start, int end) {
        if (start >= end) {
            return strs[start].length();
        } else {
            int count = 0;
            int l1 = compare(strs, start, (start + end - 1) / 2);
            int l2 = compare(strs, (start + end - 1) / 2 + 1, end);
            int limit = Math.min(l1, l2);
            for (int i = 0; i < limit; i++) {
                if (strs[start].charAt(i) == strs[end].charAt(i)) {
                    count++;
                } else {
                    break;
                }
            }
            return count;
        }
    }

}

