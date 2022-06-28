# springbatch_test
[springbatch] 블로그 포스팅을 위한 Job Repository

### 아래 github repository 복습
https://github.com/westssun/spring-batch-start


##### 포스팅 바로가기
| | | |
|-|-|-|
|순번|제목|URL|
|1|1. Hello Spring Batch!|https://devfunny.tistory.com/754|
|2|2. ApplicationRunner 구현 클래스로 Job 수행시키기|https://devfunny.tistory.com/755|
|3|3. Batch Job 수행시 Step 실패하는 경우 |https://devfunny.tistory.com/758|
|4|4. Tasklet 클래스를 생성하여 Job에 설정하기 |https://devfunny.tistory.com/759|
|5|5. Batch Job 수행시 Step 실패하는 경우2 (Step 실패와 StepExceution 관계)|https://devfunny.tistory.com/765|
|6|6. ExecutionContext (StepExecution, JobExecution 관계) 예제로 흐름 파악하기|https://devfunny.tistory.com/769|
|7|7. BATCH 테이블 Prefix 변경해보기|https://devfunny.tistory.com/774|
|8|8. JobRepository 를 사용하여 특정 JobName, JobParameters 에 해당하는 최신에 수행된 Job 정보 가져오기|https://devfunny.tistory.com/769|
|0|(기존 포스팅) SpringBatch에서 JobLauncher 동기/비동기 실행|https://devfunny.tistory.com/688|
|9|9. FlowBuilder를 사용해서 FlowJob 생성하기|https://devfunny.tistory.com/776|
|10|10. STEP의 EXIT_CODE, STATUS 설정하기|https://devfunny.tistory.com/778|
|11|11. JobParameters 검증 CustomValidator 만들기 (JobParametersValidator 구현), 스프링 배치가 제공해주는 DefaultJobParametersValidator 사용해보기|https://devfunny.tistory.com/779|
|12|12. preventRestart() 지정에 따른 수행 과정 파악하기|https://devfunny.tistory.com/786|
|13|13. incrementer 설정하기, JobParametersIncrementer 를 구현하여 커스텀 설정하기|https://devfunny.tistory.com/790|
|14|14. 여러개 Tasklet을 선언할 경우, 마지막 Tasklet을 실행한다|https://devfunny.tistory.com/792|
|15|15. STEP이 COMPELETED 상태로 완료되어도 재실행에 포함시키기 (allowStartIfCompleted(true)), STEP별 재실행 가능 횟수 설정하기 (startLimit())|https://devfunny.tistory.com/794|
|16|16. ParentJob, ChildJob 관계 (JobStep 사용)|https://devfunny.tistory.com/798|
|16|16. ParentJob, ChildJob 관계 (JobStep 사용)|https://devfunny.tistory.com/798|
|17|17. JobFlowBuilder 흐름 제어하기 (Step 성공/실패에 따라 분기처리)|https://devfunny.tistory.com/801|
|18|18. Job, Flow 혼용하여 Job 수행시키기|https://devfunny.tistory.com/802|
|0|(기존 포스팅) BATCH_JOB_EXECUTION, BATCH_STEP_EXECUTION의 EXIT_CODE, STATUS 컬럼 셋팅 정보|https://devfunny.tistory.com/698|
|19|19. EXIT_CODE를 직접 만들어서 적용해보기|https://devfunny.tistory.com/803|
|20|20. JobExecutionDecider 구현체 생성해서 커스텀 값으로 from().on() 설정|https://devfunny.tistory.com/805|
|21|21. Chunk 설정에 따른 reader(), processor(), writer() 수행 과정|https://devfunny.tistory.com/818|
|22|22. ItemReader, ItemProcessor, ItemWriter의 메서드 직접 구현해보기|https://devfunny.tistory.com/821|
|23|23. ItemStreamReader, ItemStreamWriter 구현 클래스 생성하기 (open(), update(), close())|https://devfunny.tistory.com/824|
|24|24. FlatFileItemReader (.csv 파일 읽어와서 데이터 출력하는 배치 생성)|https://devfunny.tistory.com/825|