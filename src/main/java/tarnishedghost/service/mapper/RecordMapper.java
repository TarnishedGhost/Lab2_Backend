package tarnishedghost.service.mapper;

import tarnishedghost.domain.Record;
import tarnishedghost.dto.record.RecordDto;
import tarnishedghost.dto.record.RecordEntryDto;
import tarnishedghost.dto.record.RecordListDto;
import tarnishedghost.structure.RecordEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecordMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "date", target = "date")
    @Mapping(source = "expense", target = "expense")
    Record toRecord(RecordEntity recordEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(source = "categoryId", target = "categoryId")
    @Mapping(target = "date", ignore = true)
    @Mapping(source = "expense", target = "expense")
    Record toRecord(RecordDto recordDto);

    @Mapping(source = "categoryId", target = "categoryId")
    @Mapping(source = "expense", target = "expense")
    RecordDto toRecordDto(Record recordById);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(source = "date", target = "date")
    @Mapping(source = "expense", target = "expense")
    RecordEntity toRecordEntity(Record record);

    default RecordListDto toRecordListDto(List<Record> records) {
        return RecordListDto.builder().records(toRecordEntry(records)).build();
    }

    List<RecordEntryDto> toRecordEntry(List<Record> records);

    List<Record> toRecordList(List<RecordEntity> recordEntities);
}
