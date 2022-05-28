package io.cody.all_relationships.model;

import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class YDTO {

    private Long id;

    @Size(max = 255)
    private String hasOneToManyRel;

}
