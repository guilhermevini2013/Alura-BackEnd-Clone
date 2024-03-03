package com.alura.aluraAPI.dtos.person.insert;

import com.alura.aluraAPI.models.person.TypeSignature;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record StudentInsertDTO(@NotBlank(message = "Required field")
                               @Schema(description = "Name for student")
                               @Size(max = 80, message = "Max 80 character")
                               String name,
                               @Email(message = "Email incorrect")
                               @Schema(description = "Email for student")
                               @NotBlank(message = "Required field")
                               String email,
                               @NotBlank(message = "Required field")
                               @Schema(description = "Password for student")
                               @Size(min = 6, message = "minimum of 6 characters")
                               String password,
                               @NotNull(message = "Required field")
                               @Schema(description = "Type signature(PRO or PLUS)", enumAsRef = true)
                               TypeSignature typeSignature) {
}
