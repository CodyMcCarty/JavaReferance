package io.cody.all_relationships.model;

import java.util.List;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class HubDTO {

    private UUID id;

    @NotNull
    @Size(max = 255)
    private String manyExOne;

    private Long moFHubTX;

    @NotNull
    private Long omFYTHub;

    private List<Long> mmFromHubToBs;

}
