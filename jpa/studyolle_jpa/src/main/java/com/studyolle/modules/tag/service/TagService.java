package com.studyolle.modules.tag.service;

import com.studyolle.entity.Tag;
import com.studyolle.modules.tag.repository.TagRepository;
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

        // 존재하지않는 tag 라면 신규 등록
        if (tag == null) {
            tag = tagRepository.save(Tag.builder().title(tagTitle).build());
        }

        return tag;
    }

}
