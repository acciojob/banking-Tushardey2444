package com.driver;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception

        super(name,balance,5000);

        this.tradeLicenseId=tradeLicenseId;
        if(balance<500){
            throw new Exception("Insufficient Balance");
        }
    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        boolean flag=true;
        for(int i=0;i<this.tradeLicenseId.length()-1;i++){
            if(this.tradeLicenseId.charAt(i)==this.tradeLicenseId.charAt(i+1)){
                flag=false;
                break;
            }
        }

        if(!flag){
            int[] freq=new int[26];
            int max=0;
            char Ch='A';
            int n=tradeLicenseId.length();
            for(char ch:tradeLicenseId.toCharArray()){
                freq[ch-'A']++;
                if(freq[ch-'A']>max){
                    max=freq[ch-'A'];
                    Ch=ch;
                }
            }
            if(max>((n+1)/2)){
                throw new Exception("Valid License can not be generated");
            }

            char[] ans=new char[tradeLicenseId.length()];
            int idx=0;
            while(max>0){
                ans[idx]=Ch;
                idx+=2;
                if(idx>=ans.length){
                    idx=1;
                }
                max--;
                if(max==0){
                    freq[Ch-'A']=0;
                    Pair p=maxFreq(freq);
                    max=p.max;
                    Ch=p.ch;
                }
            }
            this.tradeLicenseId=new String(ans);
        }
    }
    public Pair maxFreq(int[] arr){
        char ch='A';
        int max=0;
        for(int i=0;i<26;i++){
            if(arr[i]>max){
                max=arr[i];
                ch=(char)(i+'A');
            }
        }
        return new Pair(max,ch);
    }
}
class Pair{
    int max;
    char ch;

    public Pair(int max, char ch) {
        this.max = max;
        this.ch = ch;
    }
}
