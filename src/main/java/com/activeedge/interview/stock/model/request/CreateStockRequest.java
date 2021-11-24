package com.activeedge.interview.stock.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateStockRequest implements Serializable {
      @JsonProperty("email")
      @Email(message = "{ligare-gateway.user.validation.request.email.invalid}")
      @NotBlank(message = "{ligare-gateway.user.validation.request.email.empty-fields}")
      private String email;

      @JsonProperty("password")
      @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@$!%*?&])[A-Za-z0-9@$!%*?&]{8,}$", message = "{ligare-gateway.user.validation.request.password.requirement}")
      @NotNull(message = "{ligare-gateway.user.validation.request.password.empty-fields}")
      private String password;

      @JsonProperty("first_name")
      @NotEmpty(message = "{ligare-gateway.user.validation.request.first-name.empty-fields}")
      private String firstName;

      @JsonProperty("last_name")
      @NotEmpty(message = "{ligare-gateway.user.validation.request.last-name.empty-fields}")
      private String lastName;

      @JsonProperty("roles")
      @NotEmpty(message = "{ligare-gateway.user.validation.request.roles.empty-fields}")
      private List<String> roles;
}
