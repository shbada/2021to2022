package _14_코틀린에서_다양한_클래스를_다루는_방법

fun handleCountry(country: Country) {
    when(country) { // 조금더 읽기 쉬운 코드로도 만들 수 있다. if~else 필요없다.
        Country.KOREA -> TODO()
        Country.AMERICA -> TODO()
    }
}

enum class Country(
    private val code: String
) {
    KOREA("KO"),
    AMERICA("US")
    ;
}