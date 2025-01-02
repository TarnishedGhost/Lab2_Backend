package tarnishedghost.dto.category;

import tarnishedghost.dto.record.RecordDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Value
@Builder
@Jacksonized
public class CategoryDto {

    @NotBlank(message = "Category name cannot be blank")
    String categoryName;

    @NotNull(message = "Public status cannot be null")
    Boolean isPublic;

    List<RecordDto> records;
}
