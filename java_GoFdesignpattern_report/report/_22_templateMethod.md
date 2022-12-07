#### 템플릿 메소드 (Template method) 패턴
- 알고리듬 구조를 서브 클래스가 확장할 수 있도록 템플릿으로 제공하는 방법.
- 추상 클래스는 템플릿을 제공하고 하위 클래스는 구체적인 알고리듬을 제공한다.
![IMAGES](../report/images/templateMethod01.png)     

(1) 적용 후
![IMAGES](../report/images/templateMethod02.png)

#### 템플릿 콜백 패턴
- 콜백으로 상속 대신 위임을 사용하는 템플릿 패턴.
- 상속 대신 익명 내부 클래스 또는 람다 표현식을 활용할 수 있다.
![IMAGES](../report/images/templateCallback01.png)