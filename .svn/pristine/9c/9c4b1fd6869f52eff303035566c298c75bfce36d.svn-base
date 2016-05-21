package com.itexpertnepal.wcb.ui.aspect;



import com.itexpertnepal.simpleinvoice.utl.logger.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;


import org.omnifaces.util.Messages;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
/**
 * 
 * @author binay
 */
@Component
@Aspect
public class ExceptionHandler {

    @Log
    private Logger logger;


    //@AfterThrowing(pointcut = "execution(* com.drose.dms.ui.controller..*.*(..))", throwing = "throwable")
    //"execution(* *.*(..)) && @annotation(catchException)
    @Around("execution(* *.*(..)) && @annotation(com.itexpertnepal.simpleinvoice.ui.aspect.CatchException)")
    public Object hanldeException(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.debug("Inside " + joinPoint.getSignature().getName());
        try {
            return joinPoint.proceed();
        } catch (Exception exception) {
            Messages.addError(null, exception.getMessage());
        }
        return null;
    }
//@Before(value = "execution(public void *.*(..)) && @annotation(validateBean)", argNames = "validateBean")
//    @Around("execution(!public * com.drose.dms.ui.controller..*set*(..))")

    public void hanldeExceptionWithOutReturn(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.debug("Inside " + joinPoint.getSignature().getName());
        try {
            joinPoint.proceed();
        } catch (Exception exception) {
            Messages.addError(null, exception.getMessage());
        }
    }
}
