package huawei;

import org.junit.Test;

import java.util.*;

public class Solution {
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            dp[i][1] = 1;
        }//第一列赋值为1
        for (int j = 1; j <= n; j++) {
            dp[1][j] = 1;
        }//第一行赋值为1
        for (int i = 2; i <= m; i++) {
            for (int j = 2; j <= n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m][n];
    }

    @Test
    public void test() {
        int i = uniquePaths(3, 3);
    }

    public int JumpFloor(int target) {
        if(target <= 2){
            return target;
        }
        int pre2 = 1;
        int pre1 = 2;
        for (int i = 3; i <= target; i++){
            int cur = pre2 + pre1;
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1;
    }

    @Test
    public void testF(){
String s=" {[()]}";
        s=s.replace("()","");
        System.out.println("s = " + s);
    }
}