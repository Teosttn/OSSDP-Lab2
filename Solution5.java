import java.util.Arrays;

/**
 * @description:
 *
 * 给你一个整数数组 nums 和一个整数 target 。
 *
 * 请你统计并返回 nums 中能满足其最小元素与最大元素的 和 小于或等于 target 的 非空 子序列的数目。
 *
 * 由于答案可能很大，请将结果对 109 + 7 取余后返回。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [3,5,6,7], target = 9
 * 输出：4
 * 解释：有 4 个子序列满足该条件。
 * [3] -> 最小元素 + 最大元素 <= target (3 + 3 <= 9)
 * [3,5] -> (3 + 5 <= 9)
 * [3,5,6] -> (3 + 6 <= 9)
 * [3,6] -> (3 + 6 <= 9)
 * 示例 2：
 *
 * 输入：nums = [3,3,6,8], target = 10
 * 输出：6
 * 解释：有 6 个子序列满足该条件。（nums 中可以有重复数字）
 * [3] , [3] , [3,3], [3,6] , [3,6] , [3,3,6]
 * 示例 3：
 *
 * 输入：nums = [2,3,3,4,6,7], target = 12
 * 输出：61
 * 解释：共有 63 个非空子序列，其中 2 个不满足条件（[6,7], [7]）
 * 有效序列总数为（63 - 2 = 61）
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 106
 * 1 <= target <= 106
 *
 */
public class Solution5 {
    static final int P = 1000000007;
    static final int MAX_N = 100005;

    int[] f = new int[MAX_N];

    public int numSubseq(int[] nums, int target) {
        pretreatment();

        Arrays.sort(nums);  // 排序数组

        int ans = 0;
        int n = nums.length;
        int left = 0, right = n - 1;

        while (left <= right) {
            if (nums[left] + nums[right] <= target) {
                // 左边的元素和右边的元素可以组成子序列，贡献f[right - left]
                ans = (ans + f[right - left]) % P;
                left++;  // 左边指针右移
            } else {
                right--;  // 右边指针左移
            }
        }

        return ans;
    }

    public void pretreatment() {
        f[0] = 1;  // 初始化为1，因为空序列不计数
        for (int i = 1; i < MAX_N; ++i) {
            f[i] = (f[i - 1] << 1) % P;
        }
    }

    public int binarySearch(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (nums[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}

class L2022211858_5_Test {
    public static void main(String[] args) {
        Solution5 solution = new Solution5();

        // 测试用例 1: 正常等价类测试
        // 测试目的：验证多个元素情况下，最小和最大元素的和小于等于目标值时，正确返回子序列的数量。
        // 用例设计：目标值为9，数组中不同的子序列都满足条件
        int[] nums1 = {3, 5, 6, 7};
        int target1 = 9;
        System.out.println(solution.numSubseq(nums1, target1));  // 输出: 4

        // 测试用例 2: 有重复元素的等价类测试
        // 测试目的：验证数组中有重复元素时，算法是否能正确处理这些重复元素，并计算出满足条件的子序列数目。
        // 用例设计：目标值为10，数组中存在重复的3值
        int[] nums2 = {3, 3, 6, 8};
        int target2 = 10;
        System.out.println(solution.numSubseq(nums2, target2));  // 输出: 6

        // 测试用例 3: 边界值测试
        // 测试目的：验证数组中最大值和最小值接近目标值的情况，是否能够正确计算有效子序列数目。
        // 用例设计：目标值为12，数组中的最大值和最小值接近边界
        int[] nums3 = {2, 3, 3, 4, 6, 7};
        int target3 = 12;
        System.out.println(solution.numSubseq(nums3, target3));  // 输出: 61

        // 测试用例 4: 空数组边界测试
        // 测试目的：验证空数组时，算法能否正确返回0。
        // 用例设计：数组为空，目标值为任意值
        int[] nums4 = {};
        int target4 = 5;
        System.out.println(solution.numSubseq(nums4, target4));  // 输出: 0

        // 测试用例 5: 单元素数组边界测试
        // 测试目的：验证只有一个元素时，算法能否处理并返回正确的子序列数。
        // 用例设计：数组中只有一个元素，目标值大于或等于该元素
        int[] nums5 = {5};
        int target5 = 5;
        System.out.println(solution.numSubseq(nums5, target5));  // 输出: 1

        // 测试用例 6: 全部元素相同的边界测试
        // 测试目的：验证数组中所有元素相同且满足条件的情况下，是否正确计算子序列数。
        // 用例设计：数组中所有元素相同，目标值足够大，可以包含所有元素的子序列
        int[] nums6 = {4, 4, 4, 4};
        int target6 = 10;
        System.out.println(solution.numSubseq(nums6, target6));  // 输出: 15
    }
}
