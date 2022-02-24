// { Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;

class GFG{
    public static void main(String args[])throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0){
            int N = Integer.parseInt(in.readLine());
            String S = in.readLine();
            
            Solution ob = new Solution();
            System.out.println(ob.countWays(N, S));
        }
    }
}// } Driver Code Ends


//User function Template for Java

class Solution{
    static Map<String,Integer> mp= new HashMap<>();
    static int minParenthesization=0;
    static int countWays(int N, String S){
        if(N==0)
            return 0;
        if(N==1){
            if("T".equals(S))
                return 1;
            else
                return 0;
        }
        if(N%2==0)
            return 0;
        char[] ch = S.toCharArray();
        return solve(ch,0,N-1,true);
        
    }
    
    static int solve(char[] arr,int beg, int end, boolean requiredTrue){
        Integer existingResult = mp.get(""+beg+end+requiredTrue);
        if(existingResult!=null)
            return existingResult.intValue();
        int result=0;
        if(end==beg)
            return arr[beg]=='T'?(requiredTrue?1:0):(!requiredTrue?1:0);
        for(int k=beg+1;k<end;k=k+2){
            char operator = arr[k];
            int lt = solve(arr,beg,k-1,true);
            int lf = solve(arr,beg,k-1,false);
            int rt = solve(arr,k+1,end,true);
            int rf = solve(arr,k+1,end,false);
            switch(operator){
                case '&':
                    if(requiredTrue)
                        result = result + (lt * rt);
                    else
                        result = result + (lt * rf) + (lf * rt) + (lf * rf);
                    break;
                case '|':
                    if(requiredTrue)
                        result = result + (lt * rf) + (lf * rt) + (lt * rt);
                    else
                        result = result + (lf * rf);
                    break;
                case '^':
                    if(requiredTrue)
                        result = result + (lt * rf) + (lf * rt);
                    else
                        result = result + (lt * rt) + (lf * rf);
                    break;
            }
        }
         
        mp.put(""+beg+end+requiredTrue,result);
        return result;
    }
}
