package com.redo.studyolle.modules.service;

import com.redo.studyolle.modules.domain.entity.Tag;
import com.redo.studyolle.modules.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    /**
     * 태그가 존재하지 않을 경우 등록, 그 외 조회
     * @param tagTitle
     * @return
     */
    public Tag findOrCreateNew(String tagTitle) {
        Tag tag = tagRepository.findByTitle(tagTitle);

        if (tag == null) {
            tag = tagRepository.save(Tag.builder().title(tagTitle).build());
        }

        return tag;
    }

}
