package com.bs.base.exception;

/**
 * @description 项目异常类
 * @author Mr.M
 * @date 2022/9/6 11:29
 * @version 1.0
 */
public class BSException extends RuntimeException {

   private String errMessage;

   public BSException() {
      super();
   }

   public BSException(String errMessage) {
      super(errMessage);
      this.errMessage = errMessage;
   }

   public String getErrMessage() {
      return errMessage;
   }

   public static void cast(CommonError commonError){
       throw new BSException(commonError.getErrMessage());
   }
   public static void cast(String errMessage){
       throw new BSException(errMessage);
   }

}
