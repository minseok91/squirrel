package com.dinger.squirrel.handler;

import com.dinger.squirrel.exception.BadRequestException;
import com.dinger.squirrel.exception.NotAllowedRefererException;
import com.dinger.squirrel.presentation.ApiResponse;
import com.dinger.squirrel.exception.NotFoundException;
import com.dinger.squirrel.presentation.ApiResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handlerServerError(Exception e) {
        log.error("[SERVER_ERROR] {}", e.getMessage(), e);

        final ApiResponse<Object> apiResponse = ApiResponse.builder()
                .status(ApiResponseStatus.INTERNAL_SERVER_ERROR)
                .build();

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }

    @ExceptionHandler(NotAllowedRefererException.class)
    public ResponseEntity<Object> handleNotAllowedReferer(Exception e) {
        log.warn("[FORBIDDEN] {}", e.getMessage());

        final ApiResponse<Object> apiResponse = ApiResponse.builder()
                .status(ApiResponseStatus.FORBIDDEN)
                .build();
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }

    @ExceptionHandler({NoHandlerFoundException.class, NotFoundException.class})
    public ResponseEntity<Object> handleNoHandlerFound(Exception e) {
        log.warn("[NOT_FOUND] {}", e.getMessage());

        final ApiResponse<Object> apiResponse = ApiResponse.builder()
                .status(ApiResponseStatus.NOT_FOUND)
                .build();

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(Exception e) {
        log.warn("[BAD_REQUEST] {}", e.getMessage());

        final ApiResponse<Object> apiResponse = ApiResponse.builder()
                .status(ApiResponseStatus.BAD_REQUEST)
                .build();

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }
}
