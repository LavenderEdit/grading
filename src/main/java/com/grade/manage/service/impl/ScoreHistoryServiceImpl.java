package com.grade.manage.service.impl;

import com.grade.manage.dto.create.ScoreCreateDTO;
import com.grade.manage.dto.detail.ScoreDetailDTO;
import com.grade.manage.dto.simple.ScoreDTO;
import com.grade.manage.dto.summary.ScoreHistorySummary;
import com.grade.manage.dto.update.ScoreUpdateDTO;
import com.grade.manage.exception.UserNotFoundException;
import com.grade.manage.mapper.ScoreMapper;
import com.grade.manage.model.ScoreHistory;
import com.grade.manage.model.User;
import com.grade.manage.repository.ScoreHistoryRepository;
import com.grade.manage.repository.UserRepository;
import com.grade.manage.service.ScoreHistoryService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Studios TKOH!
 */
@Service
@RequiredArgsConstructor
@Transactional
public class ScoreHistoryServiceImpl implements ScoreHistoryService {

    private final ScoreHistoryRepository scoreHistoryRepository;
    private final UserRepository userRepository;
    private final ScoreMapper scoreMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ScoreDTO> findAllDto() {
        return scoreMapper.toDTOList(scoreHistoryRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public ScoreDTO findDtoById(Long id) {
        return scoreMapper.toDTO(getHistoryOrThrow(id));
    }

    @Override
    public ScoreDTO create(ScoreCreateDTO create) {
        ensureUniqueWeekStart(create.getUserId(), create.getWeekStart());
        User user = getUserOrThrow(create.getUserId());

        ScoreHistory entity = scoreMapper.toEntityFromCreate(create);
        entity.setUser(user);

        ScoreHistory saved = scoreHistoryRepository.save(entity);
        return scoreMapper.toDTO(saved);
    }

    @Override
    public ScoreDTO update(ScoreUpdateDTO update) {
        ScoreHistory entity = getHistoryOrThrow(update.getId());

        if (update.getWeekStart() != null && !update.getWeekStart().equals(entity.getWeekStart())) {
            ensureUniqueWeekStart(entity.getUser().getId(), update.getWeekStart());
        }

        scoreMapper.updateEntity(update, entity);
        ScoreHistory saved = scoreHistoryRepository.save(entity);
        return scoreMapper.toDTO(saved);
    }

    @Override
    public boolean delete(Long id) {
        ScoreHistory entity = getHistoryOrThrow(id);
        scoreHistoryRepository.delete(entity);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public ScoreDetailDTO getDetail(Long id) {
        return scoreMapper.toDetailDTO(getHistoryOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScoreHistorySummary> listRecentSummaries(Long userId) {
        getUserOrThrow(userId);
        return scoreMapper.toSummaryDTOList(scoreHistoryRepository.findTop10ByUser_IdOrderByWeekStartDesc(userId));
    }

    private ScoreHistory getHistoryOrThrow(Long id) {
        return scoreHistoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Score history with id %d was not found".formatted(id)));
    }

    private void ensureUniqueWeekStart(Long userId, java.time.LocalDateTime weekStart) {
        if (scoreHistoryRepository.existsByUser_IdAndWeekStart(userId, weekStart)) {
            throw new EntityExistsException("Score history already exists for the provided week");
        }
    }

    private User getUserOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id %d was not found".formatted(id)));
    }
}
