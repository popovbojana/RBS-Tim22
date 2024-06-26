package com.rbs.mini_zanzibar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthDTO {

    @NotBlank(message = "Object must not be blank.")
    @Pattern(regexp = "^[a-zA-Z0-9]+:[a-zA-Z0-9]+$", message = "Object must be in format 'namespace:document'.")
    private String object;

    @NotBlank(message = "Relation must not be blank.")
    private String relation;

    @NotBlank(message = "User must not be blank.")
    private String user;

}
