package tarnishedghost.service;

import tarnishedghost.domain.Record;
import tarnishedghost.structure.RecordEntity;
import tarnishedghost.repo.CategoryRepository;
import tarnishedghost.repo.RecordRepository;
import tarnishedghost.repo.UserRepository;
import tarnishedghost.service.errorHandler.CategoryNotFoundException;
import tarnishedghost.service.errorHandler.InvalidArgumentsException;
import tarnishedghost.service.errorHandler.RecordNotFoundException;
import tarnishedghost.service.errorHandler.UserNotFoundException;
import tarnishedghost.service.mapper.RecordMapper;
import jakarta.persistence.PersistenceException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RecordService {
    private final RecordRepository recordRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final RecordMapper recordMapper;

    @Transactional(readOnly = true)
    public Record getRecordById(UUID id) {
        try {
            return recordMapper.toRecord(recordRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
    }

    @Transactional
    public Record addRecord(Record record) {
        try {
            RecordEntity recordEntity = recordMapper.toRecordEntity(record);
            UUID userId = recordEntity.getUser().getId();
            UUID categoryId = recordEntity.getCategory().getId();
            userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
            categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));

            return recordMapper.toRecord(recordRepository.save(recordMapper.toRecordEntity(record)));
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
    }

    @Transactional
    public void deleteRecordById(UUID id) {
        try {
            recordRepository.deleteById(id);
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
    }

    @Transactional(readOnly = true)
    public List<Record> getRecords(UUID userId, UUID categoryId) {
        if (userId == null && categoryId == null) {
            throw new InvalidArgumentsException("Either userId or categoryId must be provided");
        } else if (userId == null) {
            categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));
        } else {
            userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        }
        return recordMapper.toRecordList(recordRepository.findByUserIdAndCategoryId(userId, categoryId));
    }
}
