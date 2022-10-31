package com.studyolle.domain.study

import com.studyolle.domain.Study

interface StudyStore {
    fun createNewStudy(study: Study)
}