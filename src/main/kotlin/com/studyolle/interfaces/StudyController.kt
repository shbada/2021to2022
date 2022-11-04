package com.studyolle.interfaces

import com.studyolle.application.StudyFacade
import com.studyolle.common.output.CommonResponse
import com.studyolle.common.output.ResponseDto
import com.studyolle.interfaces.dto.StudyDto
import com.studyolle.interfaces.mapper.StudyDtoMapper
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/study")
class StudyController(
    private val studyFacade: StudyFacade,
) {

    /**
     * 스터디 등록
     */
    @PostMapping
    fun studyRegister(
        @RequestBody registerForm: StudyDto.RegisterForm
    ) {
        studyFacade.createNewStudy(StudyDtoMapper.of(registerForm))
    }

    /**
     * 스터디 단건조회
     */
    @GetMapping("/{studyIdx}")
    fun getStudy(
        @PathVariable studyIdx: Long
    ): ResponseEntity<*> {
        return CommonResponse.send(studyFacade.getStudy(studyIdx))
    }
}