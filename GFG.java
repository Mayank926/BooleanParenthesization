package algos.dp.booleanParenthesization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class GFG {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int N = Integer.parseInt(in.readLine());
            String S = in.readLine();

            Solution ob = new Solution();
            System.out.println(ob.countWays(N, S));
        }
    }
}// } Driver Code Ends


//User function Template for Java

class Solution {
    static Map<String, Integer> mp;

    static int countWays(int N, String S) {
        if (N == 0)
            return 0;
        if (N == 1) {
            if ("T".equals(S))
                return 1;
            else
                return 0;
        }
        if (N % 2 == 0)
            return 0;
        char[] ch = S.toCharArray();
        mp = new HashMap<>();
        return solve(ch, 0, N - 1, true);

    }

    static int solve(char[] arr, int beg, int end, boolean requiredTrue) {

        if (end == beg)
            return arr[beg] == 'T' ? (requiredTrue ? 1 : 0) : (!requiredTrue ? 1 : 0);


        StringBuilder sb = new StringBuilder();
        sb.append(beg);
        sb.append(" ");
        sb.append(end);
        sb.append(" ");
        sb.append(requiredTrue);
        String temp = sb.toString();

        if (mp.containsKey(temp)) {
            return mp.get(temp);
        }
        int result = 0;

        for (int k = beg + 1; k < end; k = k + 2) {
            char operator = arr[k];
            int lt = solve(arr, beg, k - 1, true);
            int lf = solve(arr, beg, k - 1, false);
            int rt = solve(arr, k + 1, end, true);
            int rf = solve(arr, k + 1, end, false);
            switch (operator) {
                case '&':
                    if (requiredTrue)
                        result = result + (lt * rt);
                    else
                        result = result + (lt * rf) + (lf * rt) + (lf * rf);
                    break;
                case '|':
                    if (requiredTrue)
                        result = result + (lt * rf) + (lf * rt) + (lt * rt);
                    else
                        result = result + (lf * rf);
                    break;
                case '^':
                    if (requiredTrue)
                        result = result + (lt * rf) + (lf * rt);
                    else
                        result = result + (lt * rt) + (lf * rf);
                    break;
            }
        }

        mp.put(temp, result % 1003);
        return mp.get(temp);
    }
}
