package csh.beantage.domain.products.controller;

import csh.beantage.domain.products.dto.AdminLoginRequestDto;
import csh.beantage.global.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestCont {

    private static final String ADMIN_ID = "admin";
    private static final String ADMIN_PW = "admin";

    @PostMapping("/admin/login")
    @Operation(summary = "관리자 로그인")
    public ResponseEntity<ResponseDto> login(@RequestBody AdminLoginRequestDto requestDto) {
        if (!ADMIN_ID.equals(requestDto.id()) || !ADMIN_PW.equals(requestDto.password())) {
            throw new IllegalArgumentException("아이디 또는 비밀번호가 올바르지 않습니다.");
        }
        return new ResponseEntity<>(new ResponseDto("200-1", "로그인 되었습니다.", null), HttpStatus.OK);

    }
}
