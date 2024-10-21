import java.util.Arrays;

/**
 * @description:
 *
 * ����һ���������� nums ��һ������ target ��
 *
 * ����ͳ�Ʋ����� nums ������������СԪ�������Ԫ�ص� �� С�ڻ���� target �� �ǿ� �����е���Ŀ��
 *
 * ���ڴ𰸿��ܴܺ��뽫����� 109 + 7 ȡ��󷵻ء�
 *
 *
 *
 * ʾ�� 1��
 *
 * ���룺nums = [3,5,6,7], target = 9
 * �����4
 * ���ͣ��� 4 �������������������
 * [3] -> ��СԪ�� + ���Ԫ�� <= target (3 + 3 <= 9)
 * [3,5] -> (3 + 5 <= 9)
 * [3,5,6] -> (3 + 6 <= 9)
 * [3,6] -> (3 + 6 <= 9)
 * ʾ�� 2��
 *
 * ���룺nums = [3,3,6,8], target = 10
 * �����6
 * ���ͣ��� 6 ���������������������nums �п������ظ����֣�
 * [3] , [3] , [3,3], [3,6] , [3,6] , [3,3,6]
 * ʾ�� 3��
 *
 * ���룺nums = [2,3,3,4,6,7], target = 12
 * �����61
 * ���ͣ����� 63 ���ǿ������У����� 2 ��������������[6,7], [7]��
 * ��Ч��������Ϊ��63 - 2 = 61��
 *
 *
 * ��ʾ��
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 106
 * 1 <= target <= 106
 *
 */
class Solution5 {
    static final int P = 1000000007;
    static final int MAX_N = 100005;

    int[] f = new int[MAX_N];

    public int numSubseq(int[] nums, int target) {
        pretreatment();

        Arrays.sort(nums);  // ��������

        int ans = 0;
        int n = nums.length;
        int left = 0, right = n - 1;

        while (left <= right) {
            if (nums[left] + nums[right] <= target) {
                // ��ߵ�Ԫ�غ��ұߵ�Ԫ�ؿ�����������У�����f[right - left]
                ans = (ans + f[right - left]) % P;
                left++;  // ���ָ������
            } else {
                right--;  // �ұ�ָ������
            }
        }

        return ans;
    }

    public void pretreatment() {
        f[0] = 1;  // ��ʼ��Ϊ1����Ϊ�����в�����
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

public class Solution5Test {
    public static void main(String[] args) {
        Solution5 solution = new Solution5();

        // �������� 1
        int[] nums1 = {3, 5, 6, 7};
        int target1 = 9;
        System.out.println(solution.numSubseq(nums1, target1));  // ���: 4

        // �������� 2
        int[] nums2 = {3, 3, 6, 8};
        int target2 = 10;
        System.out.println(solution.numSubseq(nums2, target2));  // ���: 6

        // �������� 3
        int[] nums3 = {2, 3, 3, 4, 6, 7};
        int target3 = 12;
        System.out.println(solution.numSubseq(nums3, target3));  // ���: 61
    }
}