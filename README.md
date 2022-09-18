# B2B Service

### Package
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
