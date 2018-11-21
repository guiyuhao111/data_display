package cn.zxtop.data_display.util;


import cn.zxtop.data_display.entity.JsonResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ControllerExceptionHandler {
    private static Logger logger = LogManager.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonResult handleException(Exception e) {
        logger.error(e.getMessage());
        e.printStackTrace();
        return new JsonResult(e);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public JsonResult handleException(RuntimeException e) {
        logger.error(e.getMessage());
        e.printStackTrace();
        return new JsonResult(e);
    }
}