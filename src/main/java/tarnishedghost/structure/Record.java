package tarnishedghost.structure;

import lombok.Builder;
import lombok.Value;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
@Value

public class Record {
    UUID id;
    String userId;
    String categoryId;
    ZonedDateTime date;
    Double expense;
}