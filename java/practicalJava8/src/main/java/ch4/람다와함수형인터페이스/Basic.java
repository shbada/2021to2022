package ch4.람다와함수형인터페이스;

import dto.SampleDto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Basic {
    /**
     1) 람다 표현식이 필요한 이유
     자바 기반의 프로그램은 계속해서 비대해지고있고, 유연성과 다양성을 확보하기 위해 이터페이스 기반으로 개발을 많이한다.
     이때문에 프로그램의 명세(인터페이스)와 구현(클래스)을 분리하고 결과적으로 많은 클래스 파일이 생긴다.
     경우에 따라서 구현체는 제공하지 않고 인터페이스만 제공하는 경우도 있다.
     이에 대응하기 별도로 구현된 클래스를 만들기도 하고 간단한 코드라면, 중첩 클래스나 익명 클래스 형태로 실제 내용을 구현하기도한다.
     하지만 익명클래스를 많이 만들면 비즈니스 로직의 구현보다 그것을 담기위한 코드를 더 많이 작성하게되고,
     중복되는 코드가 많아지는 문제가 생긴다.

     람다 표현식은 이런 익명클래스를 대체하는데 유용하다.
     */
    
    private void test() {
        List<SampleDto> sampleDtoList = new ArrayList<>();
        
        // 익명클래스 사용
        sampleDtoList.sort(new Comparator<SampleDto>() {
            @Override
            public int compare(SampleDto sampleDto1, SampleDto sampleDto2) {
                return sampleDto1.getName().compareTo(sampleDto2.getName());
            }
        });

        // 람다식 사용
        sampleDtoList.sort(
                (SampleDto sampleDto1, SampleDto sampleDto2) -> sampleDto1.getName().compareTo(sampleDto2.getName())
        );
        
        /**
         람다표현식의 장점
         1) 이름 없는 함수 선언 가능
         2) 소스코드의 분량을 줄일 수 있다, 반복 코드 관리 유용
         3) 코드를 파라미터로 전달 가능 (동작을 정의해서 메서드에 전달할때 편리하게 사용 가능)
         */
    }
}
