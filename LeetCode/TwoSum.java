package LeetCode;

import java.util.*;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i=0; i<nums.length; i++) {
            int required = target - nums[i];
            if(map.containsKey(required)) {
                int[] answer = {map.get(required), i};
                return answer;
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
