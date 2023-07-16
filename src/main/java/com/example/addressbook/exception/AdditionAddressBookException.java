package com.example.addressbook.exception;

import com.example.addressbook.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class AdditionAddressBookException {


    //Handle the Exceptions regarding validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> validateExceptionHandler(MethodArgumentNotValidException exception) {
        List<FieldError> errorList = exception.getBindingResult().getFieldErrors();
        List<String> errorList1 = errorList.stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList());
        ResponseDTO responseDTO = new ResponseDTO("Invalid Format...", errorList1);
        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
    }


    //Handle the custom Exceptions which are not available in database
    @ExceptionHandler(AddressBookException.class)
    public ResponseEntity<ResponseDTO> customException(AddressBookException exception) {
        ResponseDTO responseDTO = new ResponseDTO(exception.getMessage(), "Exception while Request Processing...");
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }


    //Handle media type Not acceptable exception
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<ResponseDTO> mediaTypeNotAcceptableHandler(HttpMediaTypeNotAcceptableException exception) {
        ResponseDTO responseDTO = new ResponseDTO("Invalid Media", "Check media Type ");
        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_ACCEPTABLE);
    }

    //Handle method Not supported exception
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ResponseDTO> requestMethodNotSupportedHandler(HttpRequestMethodNotSupportedException exception) {
        ResponseDTO responseDTO = new ResponseDTO("Invalid API call", "Check API!!! ");
        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_ACCEPTABLE);
    }

    //Handle the message Not Readable Exception
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseDTO> messageNotReadableHandler(HttpMessageNotReadableException exception){
        ResponseDTO responseDTO = new ResponseDTO("You are entered not required message ","Check message any mistakes are there..!");
        return new ResponseEntity<>(responseDTO,HttpStatus.BAD_GATEWAY);
    }
}
