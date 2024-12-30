package tarnishedghost.dto.record;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Value
@Builder
@Jacksonized
public class RecordListDto {
    List<RecordEntryDto> records;
}
