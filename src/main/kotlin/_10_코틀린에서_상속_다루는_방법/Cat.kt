package _10_코틀린에서_상속_다루는_방법

class Cat(
    species: String
) : Animal(species, 4) { // 상속은 :(콜론) 로 표기 (상위 클래스의 생성자를 호출해준다.)
    override fun move() {
        println("고양이가 사뿐 사뿐 걸어가~")
    }
}