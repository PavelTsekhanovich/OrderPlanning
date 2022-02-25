package com.itrexgroup.orderplanning.api.controller.advice;

import com.itrexgroup.orderplanning.api.dto.response.ErrorHandlerDto;
import com.itrexgroup.orderplanning.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ErrorHandlerDto> handleNotFoundException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ErrorHandlerDto.builder().message(ex.getMessage()).build(), HttpStatus.NOT_FOUND);
    }

}
