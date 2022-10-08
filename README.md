# ddd-practice-package-project
- 사이드 프로젝트 바로가기 : https://github.com/cIonecoder

## Package

- __interfaces__
  - 사용자에게 정보를 보여주고 사용자의 명령을 해석하는 일을 책임진다.
  - DTO, Mapper, Controller
- __application__
  - 수행할 작업을 정의하고 표현력 있는 도메인 객체가 문제를 해결하게 한다. 이 계층에서 책임지는 작업은 업무상 중요하거나 다른 시스템의 응용 계층과 상호 작용하는 데 필요한 것들이다. 이 계층은 얇게 유지되고, 오직 작업을 조정하고 아래에 위치한 계층에 포함된 도메인 객체의 협력자에게 작업을 위임한다.
  - xxxFacade
- __domain__
  - 업무 개념과 업무 상황에 대한 정보, 업무 규칙을 표현하는 일을 책임진다.
  - Service, Command, Query, Entity
- __infrastructure__
  - 상위 계층을 지원하는 일반화된 기술적 기능을 제공한다.
  - RedisConnector, Low level 구현체
  
---

## flow
```
interfaces -> application -> domain <- infrastructure
```
- 패키지간의 의존성이 한 방향으로 흐름
- 하위 레벨의 패키지에서 변경 발생시, 상위 레벨의 패키지에 변경이 발생하지 않음
- 도메인에 대한 로직 변경은 도메인 계층에서만 발생해야하며, 도메인 계층이 다른 계층에 의존성을 갖게되면 해당 계층에도 수정 사항이 발생할 수 있음
- 유지보수성, 확장성 고려한 구조 

--- 

## In Project
```bash
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── expedia
│   │   │           └── b2b
│   │   │               ├── B2bApplication.java
│   │   │               ├── application
│   │   │               │   ├── accommodation
│   │   │               │   │   └── AccommodationFacade.java
│   │   │               │   └── dto
│   │   │               │       ├── AccommodationCommonCodeDto.java
│   │   │               │       ├── AccommodationDto.java
│   │   │               │       ├── AccommodationGroupCommonCodeDto.java
│   │   │               │       ├── AccommodationRoleDto.java
│   │   │               │       ├── AccommodationRoomDto.java
│   │   │               │       ├── AccommodationRoomFeeDto.java
│   │   │               │       ├── AccommodationRoomInfoDto.java
│   │   │               │       └── RequestSaveAccommodationDto.java
│   │   │               ├── domain
│   │   │               │   ├── Accommodation.java
│   │   │               │   ├── AccommodationCommonCode.java
│   │   │               │   ├── AccommodationGroupCommonCode.java
│   │   │               │   ├── AccommodationRole.java
│   │   │               │   ├── AccommodationRoom.java
│   │   │               │   ├── AccommodationRoomFee.java
│   │   │               │   ├── AccommodationRoomInfo.java
│   │   │               │   └── accommodation
│   │   │               │       ├── AccommodationService.java
│   │   │               │       └── AccommodationStore.java
│   │   │               ├── infrastructure
│   │   │               │   ├── AccommodationCommonCodeRepository.java
│   │   │               │   ├── AccommodationGroupCommonCodeRepository.java
│   │   │               │   ├── AccommodationRepository.java
│   │   │               │   ├── AccommodationRoleRepository.java
│   │   │               │   ├── AccommodationRoomFeeRepository.java
│   │   │               │   ├── AccommodationRoomInfoRepository.java
│   │   │               │   ├── AccommodationRoomRepository.java
│   │   │               │   └── AccommodationStoreImpl.java
│   │   │               └── interfaces
│   │   │                   ├── controller
│   │   │                   │   └── AccommodationController.java
│   │   │                   └── dto
│   │   │                       ├── AccommodationCommonCodeDto.java
│   │   │                       ├── AccommodationDto.java
│   │   │                       ├── AccommodationGroupCommonCodeDto.java
│   │   │                       ├── AccommodationRoleDto.java
│   │   │                       ├── AccommodationRoomDto.java
│   │   │                       ├── AccommodationRoomFeeDto.java
│   │   │                       ├── AccommodationRoomInfoDto.java
│   │   │                       └── RequestSaveAccommodationDto.java
│   │   └── resources
│   │       ├── application-dev.yml
│   │       └── application.yml
│   └── test
│       ├── generated_tests
│       ├── java
│       │   └── com
│       │       └── expedia
│       │           └── b2b
│       │               ├── B2bApplicationTests.java
│       │               ├── acceptance
│       │               │   ├── AcceptanceTest.java
│       │               │   └── accommodation
│       │               │       ├── AccommodationAcceptanceTest.java
│       │               │       ├── AccommodationParams.java
│       │               │       └── AccommodationSteps.java
│       │               └── documentation
│       │                   ├── Documentation.java
│       │                   ├── DocumentationUtils.java
│       │                   └── accommodation
│       │                       ├── AccommodationDocumentationTest.java
│       │                       └── AccommodationDocumentationTestFilter.java
│       └── resources
│           └── application.yml
```
