package io.cody.all_relationships.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class NeedsBillingAddressDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String needsBillingAddress;

}
