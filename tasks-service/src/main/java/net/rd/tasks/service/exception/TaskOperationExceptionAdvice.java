package net.rd.tasks.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class TaskOperationExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(TaskOperationEntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String taskOperationNotFound(TaskOperationEntityNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(TaskOperationInvalidException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String invalidTaskOperationInput(TaskOperationInvalidException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(TaskOperationQueryParamInvalidException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String invalidTaskOperationQueryInput(TaskOperationQueryParamInvalidException ex) {
        return ex.getMessage();
    }

}
