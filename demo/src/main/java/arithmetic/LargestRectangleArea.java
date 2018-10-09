package arithmetic;

import java.util.Stack;
import org.junit.jupiter.api.Test;

/**
 * Created by BaoCai on 2018/9/26. Description:
 */
public class LargestRectangleArea {

    @Test
    public void largestRectangleArea() {
        int[] heights = {3, 1, 2, 5, 6, 2, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2};
        System.out.println(largestRectangleArea(heights));
        System.out.println(largestRectangleArea2(heights));
    }

    private int largestRectangleArea(int[] heights) {
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            int h = heights[i];
            int before = 0;
            int after = 0;

            inner:
            for (int j = Math.max(i - 1, 0); j >= 0; j--) {
                if (heights[j] < h) {
                    break inner;
                }
                if (j != i) {
                    before++;
                }
            }

            for (int j = Math.min(i + 1, heights.length); j < heights.length; j++) {
                if (heights[j] < h) {
                    break;
                }
                if (j != i) {
                    before++;
                }
            }

            max = Math.max(max, h * (before + after + 1));
        }
        return max;
    }

    //int[] heights = {3, 2, 5, 6, 2, 3, 2,2,2,2,2,2,2,2,2};
    public int largestRectangleArea2(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<Integer>();
        int maxSize = 0;
        int i = 0;
        for (; i < heights.length; i++) {
            if (stack.empty() || heights[stack.peek()] <= heights[i]) {
                stack.push(i);
            } else {
                int tp = stack.pop();
                int beginIndex = stack.empty() ? -1 : stack.peek();
                maxSize = max(maxSize, heights[tp] * (i - 1 - beginIndex));
                i--;
            }
        }
        while (!stack.empty()) {
            int tp = stack.pop();
            int beginIndex = stack.empty() ? -1 : stack.peek();
            maxSize = max(maxSize, heights[tp] * (i - 1 - beginIndex));
        }
        return maxSize;
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }

}
