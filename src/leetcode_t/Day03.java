package leetcode_t;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Created by 江婷婷 on 2019/10/17.
 */
public class Day03 {}

/*
 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 注意：答案中不可以包含重复的三元组。

 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 满足要求的三元组集合为：
 [[-1, 0, 1], [-1, -1, 2]]
 */
class T_15 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        int i, p, q, left, sum;
        for (i = 0; i < nums.length - 2 && nums[i] <= 0; ) {
            p = i + 1;
            q = nums.length - 1;
            left = 0 - nums[i];
            while (p < q) {
                sum = nums[p] + nums[q];
                if (sum == left) {
                    ArrayList<Integer> l = new ArrayList<>();
                    l.add(nums[i]);
                    l.add(nums[p]);
                    l.add(nums[q]);
                    ans.add(l);
                }
                if (sum <= left) {
                    while (p++ < q && nums[p] == nums[p - 1]);
                }
                if (sum >= left) {
                    while (q-- > p && nums[q] == nums[q + 1]);
                }
            }
            while (i++ < nums.length - 2 && nums[i] == nums[i - 1]);
        }
        return ans;
    }
}

/*
 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。
 返回这三个数的和。假定每组输入只存在唯一答案。

 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 */
class T_16 {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        int i, p, q, left, sum;
        for (i = 0; i < nums.length - 2; i++) {
            p = i + 1;
            q = nums.length - 1;
            left = target - nums[i];
            while (p < q) {
                sum = nums[p] + nums[q];
                if (sum == left) {
                    return target;
                }
                if (Math.abs(target - ans) > Math.abs(left - sum)) {
                    ans = nums[i] + sum;
                }
                if (sum <= left) {
                    p++;
                }
                if (sum >= left) {
                    q--;
                }
            }
        }
        return ans;
    }
}

/*
 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

 有效字符串需满足：
 左括号必须用相同类型的右括号闭合。
 左括号必须以正确的顺序闭合。
 注意空字符串可被认为是有效字符串。
 */
class T_20 {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (stack.empty()) {
                return false;
            } else if (c == ')' && stack.pop() != '(') {
                return false;
            } else if (c == '}' && stack.pop() != '{') {
                return false;
            } else if (c == ']' && stack.pop() != '[') {
                return false;
            }
        }
        return stack.empty();
    }
}

/*
 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 */
class T_26 {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int end = 0;
        for (int i = 0 ; i < nums.length; i++) {
            if (nums[i] != nums[end]) {
                nums[++end] = nums[i];
            }
        }
        return end + 1;
    }
}

/*
 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 说明：你不能倾斜容器，且 n 的值至少为 2。
 */
class T_11 {
    // 贪心
    public int maxArea(int[] height) {
        int max = 0, cur;
        for (int i = 0, j = height.length - 1; i < j; ) {
            cur = (j - i) * Math.min(height[i], height[j]);
            if (cur > max) {
                max = cur;
            }
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return max;
    }
}

/*
 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 */
class T_43 {

    public String multiply_01(String num1, String num2) {
        StringBuilder ans = new StringBuilder("0");
        for (int i = num1.length() - 1; i >= 0; i--) {
            // 乘每一位
            StringBuilder cur = new StringBuilder();
            int carry = 0;
            int k, a;
            for (k = num2.length() - 1; k >= 0; k--) {
                a = (num1.charAt(i) - '0') *  (num2.charAt(k) - '0') + carry;
                carry = a / 10;
                cur.insert(0, a % 10);
            }
            if (carry != 0) {
                cur.insert(0, carry);
            }
            if (cur.charAt(0) == '0') {
                continue;
            }

            // 移位
            for (k = 0; k < num1.length() - 1 - i; k++) {
                cur.append("0");
            }

            if (ans.length() < cur.length()) {
                StringBuilder t = ans;
                ans = cur;
                cur = t;
            }

            // 加到结果
            carry = 0;
            int p = cur.length() - 1, q = ans.length() - 1;
            for ( ; q >= 0; p--, q--) {
                a = (ans.charAt(q) - '0') +  (p >= 0 ? (cur.charAt(p) - '0') : 0) + carry;
                ans.setCharAt(q, (char) ('0' + a % 10));
                carry = a / 10;
            }
            if (carry != 0) {
                ans.insert(0, carry);
            }

        }
        return ans.toString();
    }

    // num1[i] * num2[j] = res[i+j] res[i+j+1]
    public String multiply_02(String num1, String num2) {
        int[] ans = new int[num1.length() + num2.length()];
        int a, b;
        for (int i = num1.length() - 1; i >= 0; i--) {
            a = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= 0; j--) {
                b = num2.charAt(j) - '0';
                int sum = ans[i + j + 1] + a * b;
                ans[i + j + 1] = sum % 10;
                ans[i + j] += sum / 10;
            }
        }

        StringBuilder res = new StringBuilder();
        int i = 0;
        while (i < ans.length - 1 && ans[i] == 0) {
            i++;
        }
        for (; i < ans.length; i++) {
            res.append(ans[i]);
        }

        return res.toString();
    }

}

/*
 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 */
class T_344 {
    public void reverseString(char[] s) {
        for (int i = 0, j = s.length - 1; i < j; i++, j--) {
            char t = s[i];
            s[i] = s[j];
            s[j] = t;
        }
    }
}

/*
 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 */
class T_557 {
    public String reverseWords(String s) {
        StringBuilder ans = new StringBuilder();
        int k = 0;
        char c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (c != ' ') {
                ans.insert(k, c);
            } else {
                ans.append(' ');
                k = ans.length();
            }
        }
        return ans.toString();
    }
}

