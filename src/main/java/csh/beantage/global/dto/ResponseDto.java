package csh.beantage.global.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ResponseDto<T> {
    private String resultCode;
    private String message;
    private T data;
}
