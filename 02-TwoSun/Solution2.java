import java.util.HashMap;

class Solution2 {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> IndexOf = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++)
            IndexOf.put(nums[i], i);
        for (int i = 0; i < nums.length; i++) {
            if (IndexOf.get(target - nums[i]) != null) {
                Integer j = IndexOf.get(target - nums[i]);
                if (j != i) {
                    return new int[] { i, j };
                }
            }
        }
        return new int[1];
    }
}
