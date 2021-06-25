package com.lhp.functionInterfaces;

/**
 * @author Amumu
 * @create 2019/12/16 15:04
 */
public interface TestDefaultMethod {
    default int factorial(int n,int result){
        if(n==1){
            return result;
        }
        return factorial(n-1,n*result);
    }
}
