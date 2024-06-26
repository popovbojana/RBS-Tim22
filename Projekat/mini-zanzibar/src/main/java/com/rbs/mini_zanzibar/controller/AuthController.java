package com.rbs.mini_zanzibar.controller;

import com.rbs.mini_zanzibar.dto.AuthDTO;
import com.rbs.mini_zanzibar.dto.ResponseDTO;
import com.rbs.mini_zanzibar.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/acl")
    public ResponseEntity<String> saveAcl(@Valid @RequestBody AuthDTO authDTO) {
        authService.saveAcl(authDTO);
        return new ResponseEntity<>(String.format("Successfully saved ACL: %s#%s@%s.", authDTO.getObject(), authDTO.getRelation(), authDTO.getUser()), HttpStatus.OK);
    }

    @GetMapping(value = "/acl/check")
    public ResponseEntity<ResponseDTO> findAcl(@RequestParam String object,
                                                @RequestParam String relation,
                                                @RequestParam String user) {
        return new ResponseEntity<>(authService.findAcl(object, relation, user), HttpStatus.OK);
    }

}
