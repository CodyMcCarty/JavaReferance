package io.cody.all_relationships.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class XDTO {

    private Long id;

    @Size(max = 255)
    private String hasManyToOneRel;

    @Size(max = 255)
    private String hasOneToOne;

    @NotNull
    private Clearnance clearance;

    private Long ooFXTZ;

}
