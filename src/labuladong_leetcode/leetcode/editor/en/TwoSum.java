//Given an array of integers nums and an integer target, return indices of the 
//two numbers such that they add up to target. 
//
// You may assume that each input would have exactly one solution, and you may 
//not use the same element twice. 
//
// You can return the answer in any order. 
//
// 
// Example 1: 
//
// 
//Input: nums = [2,7,11,15], target = 9
//Output: [0,1]
//Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
// 
//
// Example 2: 
//
// 
//Input: nums = [3,2,4], target = 6
//Output: [1,2]
// 
//
// Example 3: 
//
// 
//Input: nums = [3,3], target = 6
//Output: [0,1]
// 
//
// 
// Constraints: 
//
// 
// 2 <= nums.length <= 10⁴ 
// -10⁹ <= nums[i] <= 10⁹ 
// -10⁹ <= target <= 10⁹ 
// Only one valid answer exists. 
// 
//
// 
//Follow-up: Can you come up with an algorithm that is less than 
//O(n²) time complexity?
//
// Related TopicsArray | Hash Table 
//
// 👍 41916, 👎 1368bug 反馈 | 使用指南 | 更多配套插件 
//
//
//
//

package labuladong_leetcode.leetcode.editor.en;


import java.util.HashMap;

//JAVA:Two Sum
public class TwoSum{
	public static void main(String[] args) {
	Codec codec = new TwoSum().new Codec();
	// TO TEST
}
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // 维护一个 value -> index 的映射
        HashMap<Integer, Integer> valToIdx = new HashMap<>();
        // 遍历该数组
        for(int i = 0; i < nums.length; i++){
            int need = target - nums[i];
            if(valToIdx.containsKey(need)){
                return new int[]{i, valToIdx.get(need)};
            }
            valToIdx.put(nums[i], i);
        }
        return null;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
