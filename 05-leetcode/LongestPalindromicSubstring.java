	class Solution {
	    public String longestPalindrome(String s) {
	        int start = 0;
	        int maxLen = 0;
	        int slen = s.length();
	        if(slen <= 1) return s;
	        for(int i = 0; i < slen; i++){
	            int len = 1;
	            while(i - len >= 0 && i + len < slen){	//for palindromic centered at one point
	                if(s.charAt(i - len) == (s.charAt(i + len))){
	                    if(maxLen < 2 * len + 1){
	                        maxLen = 2 * len + 1;
	                        start = i - len;
	                    }
	                }else{
	                    break;
	                }
	                len++;
	            }
	            //For palindromic centered between characters
	            len = 1;
	            while(i - len + 1 >= 0 && i + len < slen){
	                if(s.charAt(i - len + 1) == s.charAt(i + len)){
	                    if(maxLen < 2 * len){
	                        maxLen = 2 * len;
	                        start = i + 1 - len;
	                    }
	                }else{
	                    break;
	                }
	                len++;
	            }
	        }
	        if(maxLen == 0){
	            return s.substring(0,1);
	        }
	        return s.substring(start, start+maxLen);
	    }
	}
