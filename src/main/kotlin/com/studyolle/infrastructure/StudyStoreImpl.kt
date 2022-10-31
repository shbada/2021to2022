package com.studyolle.infrastructure

import com.studyolle.domain.Study
import com.studyolle.domain.study.StudyStore
import org.springframework.stereotype.Component

@Component
class StudyStoreImpl(
    private val studyRepository: StudyRepository
) : StudyStore {
    override fun createNewStudy(study: Study) {
        studyRepository.save(study)
    }
}