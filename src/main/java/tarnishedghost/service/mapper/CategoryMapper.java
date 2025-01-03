package tarnishedghost.service.mapper;

import tarnishedghost.domain.Category;
import tarnishedghost.dto.category.CategoryDto;
import tarnishedghost.dto.category.CategoryEntryDto;
import tarnishedghost.dto.category.CategoryListDto;
import tarnishedghost.structure.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = RecordMapper.class)
public interface CategoryMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "categoryName", target = "categoryName")
    @Mapping(source = "isPublic", target = "isPublic")
    @Mapping(source = "records", target = "records")
    Category toCategory(CategoryEntity categoryEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(source = "categoryName", target = "categoryName")
    @Mapping(source = "isPublic", target = "isPublic")
    @Mapping(source = "records", target = "records")
    Category toCategory(CategoryDto categoryDto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "categoryName", target = "categoryName")
    @Mapping(source = "isPublic", target = "isPublic")
    @Mapping(source = "records", target = "records")
    CategoryEntity toCategoryEntity(Category category);

    @Mapping(source = "categoryName", target = "categoryName")
    @Mapping(source = "isPublic", target = "isPublic")
    @Mapping(source = "records", target = "records")
    CategoryDto toCategoryDto(Category category);

    default CategoryListDto toCategoryListDto(List<Category> categories) {
        return CategoryListDto.builder().categories(toCategoryEntry(categories)).build();
    }

    List<CategoryEntryDto> toCategoryEntry(List<Category> categories);

    List<Category> toCategoryList(List<CategoryEntity> byIsPublicTrue);
}
