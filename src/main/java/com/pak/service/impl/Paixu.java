package com.pak.service.impl;

import java.util.Arrays;

/**
 * Created by huyiwei on 2019/4/22.
 */
public class Paixu
{
    static String[] strAfter;
    static int paixu(String[] str){
        String zifu;
        int zifuNum;
        int[] shuzi;
        zifu = (str[0].split("[0-9]",2))[0];
        zifuNum = zifu.length();
        shuzi = new int[str.length];
        strAfter = new String[str.length];
        for(int i=0;i<str.length;i++){
            shuzi[i] = Integer.valueOf(str[i].substring(zifuNum));
        }
        Arrays.sort(shuzi);
        for(int i=0;i<str.length;i++){
            strAfter[i] = zifu + shuzi[i];
            System.out.println(strAfter[i]);
        }
        return str.length;
    }
    public static void main(String[] args){
        System.out.println(test(10));
    }

    public static int test(int n){
        if(n==1){
            return 1;
        }else{
            return n+test(n-1);
        }
    }
}
