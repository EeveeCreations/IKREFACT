package nl.hsleiden.svdj8.controllers;

import nl.hsleiden.svdj8.models.ExceptionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CommonErrorController implements ErrorController {

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping(value = "error")
    @ResponseBody
    public ExceptionResponse error(WebRequest webRequest, HttpServletResponse response) {
        return new ExceptionResponse(response.getStatus(), getErrorAttributes(webRequest));
    }

    private Map<String, Object> getErrorAttributes(WebRequest webRequest) {
        ErrorAttributeOptions errorAttributeOptions = ErrorAttributeOptions
                .defaults()
                .including(ErrorAttributeOptions.Include.MESSAGE)
                .including(ErrorAttributeOptions.Include.STACK_TRACE);
        return new HashMap<>(errorAttributes.getErrorAttributes(webRequest, errorAttributeOptions));
    }
}