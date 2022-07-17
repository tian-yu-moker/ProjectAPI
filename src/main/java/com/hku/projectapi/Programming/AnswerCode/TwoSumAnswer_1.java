package com.hku.projectapi.Programming.AnswerCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Feed the test cases into the method, and compare with the user posted one.
 */
public class TwoSumAnswer_1
{
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }
}
