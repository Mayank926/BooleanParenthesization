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
    static int count=0;
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
        if(N==3){
            boolean op1=(ch[0]=='T'?true:false);
            boolean op2=(ch[2]=='T'?true:false);
            if(evaluate(op1,op2,ch[1]))
                return 1;
            else
                return 0;
        }
        
        solve(ch,0,N-1,true);
        return count;
    }
    
    static boolean solve(char[] arr,int beg, int end, boolean required){
        boolean result=false;
        if(end==beg)
            return arr[beg]=='T'?required:!required;
        
        for(int i=beg+1;i<end;i=i+2){
            boolean lt = solve(arr,beg,i-1,true);
            boolean rt = solve(arr,i+1,end,true);
            boolean lf = solve(arr,beg,i-1,false);
            boolean rf = solve(arr,i+1,end,false);
            switch(arr[i]){
                case '&':
                    if(required){
                        result = lt & rt;
                        if(result)
                            count++;
                    }else{
                        result = (lt & rf)|(lf & rt)|(lf & rf);
                        if(result)
                            count++;
                    }
                    break;
                case '|':
                    if(required){
                        result = lt | rt;
                        if(result)
                            count++;
                    }else{
                        result = lf & rf;
                        if(result)
                            count++;
                    }
                    break;
                case '^':
                    if(required){
                        result = (lt & rf)|(lf & rt);
                        if(result)
                            count++;
                    }else{
                        result = (lt & rt)|(lf & rf);
                        if(result)
                            count++;
                    }
                    break;
            }
        }
        return result;
    }
    static boolean evaluate(boolean op1,boolean op2, char operand){
        boolean result = false;
        switch(operand){
                case '|':
                    result=op1|op2;
                    break;
                case '&':
                    result = op1&op2;
                    break;
                case '^':
                    result = op1^op2;
                    break;
            }
        return result;    
    }
}
