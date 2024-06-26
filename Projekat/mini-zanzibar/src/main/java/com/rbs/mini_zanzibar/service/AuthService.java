package com.rbs.mini_zanzibar.service;

import com.rbs.mini_zanzibar.dto.AuthDTO;
import com.rbs.mini_zanzibar.dto.ResponseDTO;

public interface AuthService {

    void saveAcl(AuthDTO authDTO);

    ResponseDTO findAcl(String object, String relation, String user);

}
