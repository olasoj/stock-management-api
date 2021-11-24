package com.activeedge.interview.stock.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateStockRequest implements Serializable {
    @JsonProperty("first_name")
    @NotEmpty(message = "{ligare-gateway.user.team-member.validation.request.first-name.invalid}")
    private String firstName;

    @JsonProperty("last_name")
    @NotEmpty(message = "{ligare-gateway.user.team-member.validation.request.last-name.invalid}")
    private String lastName;

    @JsonProperty("roles")
    @NotEmpty(message = "{ligare-gateway.user.team-member.validation.request.roles.invalid}")
    private List<String> roles;
}
