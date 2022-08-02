package com.studyolle.modules.study;

import com.studyolle.entity.Account;
import com.studyolle.entity.Study;
import com.studyolle.modules.study.repository.StudyRepository;
import com.studyolle.modules.study.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudyFactory {

    @Autowired
    StudyService studyService;
    @Autowired
    StudyRepository studyRepository;

    public Study createStudy(String path, Account manager) {
        Study study = new Study();
        study.setPath(path);
        studyService.createNewStudy(study, manager);
        return study;
    }

}
