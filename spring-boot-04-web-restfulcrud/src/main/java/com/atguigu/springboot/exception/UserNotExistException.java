package com.atguigu.springboot.exception;

/**
 * 自定义的异常类,hello请求中用到
 */
public class UserNotExistException extends RuntimeException {
    public UserNotExistException() {
        super("用户不存在");
    }
}
