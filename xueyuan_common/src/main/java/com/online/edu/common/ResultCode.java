package com.online.edu.common;

//定义返回数据使用的状态码
public interface ResultCode {
    int SUCCESS = 20000; //成功
    int ERROR = 20001;  //失败
    int AUTH = 30000;  //权限不够
}
