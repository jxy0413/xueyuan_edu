package com.online.edu.eduservice.Exception;

import com.online.edu.common.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalException {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public R error(Exception e){
        e.printStackTrace();
        return  R.error().message("出现了异常");
    }

    //2.对特定的异常进行处理
    @ResponseBody
    @ExceptionHandler(ArithmeticException.class)
    public R error(ArithmeticException e){
        e.printStackTrace();
        return  R.error().message("0不能为除数,出现了异常");
    }


    //3.对自定义的异常进行处理
    @ResponseBody
    @ExceptionHandler(EduException.class)
    public R error(EduException e){
        e.printStackTrace();
        return  R.error().message(e.getMessage()).code(e.getCode());
    }

}
