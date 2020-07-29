package de.fh.albsig.hs88455.controller;

import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomErrorController implements ErrorController {

  @Override
  public String getErrorPath() {
    return "/error";
  }

  @RequestMapping(value = "error")
  @ResponseBody
  public String error(HttpServletRequest request) {
    int statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
    Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");

    Logger logger = LogManager.getLogger(CustomErrorController.class);
    logger.error("An error has accured while performing the request.");

    return String.format(
        "<html><body><h2>An error has accured while performing your request.</h2><div>Status code: <b>%s</b></div>"
            + "<div>Exception Message: <b>%s</b></div><body></html>",
        statusCode, exception == null ? "N/A" : exception.getMessage());
  }

}
