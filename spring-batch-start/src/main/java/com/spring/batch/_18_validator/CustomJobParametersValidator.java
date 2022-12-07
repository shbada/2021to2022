package com.spring.batch._18_validator;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;

public class CustomJobParametersValidator implements JobParametersValidator {
    /**
     * 총 2번의 검증
     * 1) job 이 실행되기 전에 jobRepository 의 기능이 시작하기 전
     * - SimpleJobLauncher 에 validate() 메서드 호출됨
     * - repository 에 생성하기전, 파라미터 검증을 체크한다.
     * 2) job 이 실행되기 직전
     * - SimpleJob 이 수행될때 AbstractJob(인터페이스)의 jobParametersValidator
     *  (설정하지 않으면 DefaultJobParametersValidator)  setJobParametersValidator 이 호출됨
     *  그리고 AbstractJob 의 execute() 될때 validate() 를 호출한다.
     *
     *  [debug 모드로 실행]
     *  JobBuilderHelper 의 validator()에서 jobParametersValidator 에 저장
     *  AbstractJob 의 setJobParametersValidator() 호출
     *  SimpleJobLauncher 에서 validate() 호출
     *  -> 검증 수행
     *
     *  AbstractJob 의 validate() 호출
     *  -> 검증 수행
     *
     *  DefaultJobParametersValidator
     *  - optionalKeys
     *  - requiredKeys
     * @param jobParameters
     * @throws JobParametersInvalidException
     */
    @Override
    public void validate(JobParameters jobParameters) throws JobParametersInvalidException {
        // value 가 null 일때
        if (jobParameters.getString("name") == null) {
            throw new JobParametersInvalidException("name parameters is not found ");
        }
    }
}
