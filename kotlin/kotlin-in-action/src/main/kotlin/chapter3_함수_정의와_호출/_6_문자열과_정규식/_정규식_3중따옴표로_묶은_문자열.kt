package chapter3_함수_정의와_호출._6_문자열과_정규식

/*
1) String을 확장한 함수를 사용
"/Users/westssun/kotlin-book/chapter.adoc"
- 디렉터리 : /Users/westssun/kotlin-book
- 마지막 슬래시 : /Users/westssun/kotlin-book '/'
- 파일 이름 : chapter
- 마지막 마침표 : chapter'.'
- 확장자 : adoc
 */
fun parsePath(path: String){
    val directory = path.substringBeforeLast("/")
    val fullName = path.substringAfterLast("/")
    val fileName = fullName.substringBeforeLast(".")
    val extension = fullName.substringAfterLast(".")

    println("Dir: $directory, fullName: $fullName, name: $fileName, ext: $extension")
}

fun main() {
    // Dir: /Users/westssun/kotlin-book, fullName: chapter.adoc, name: chapter, ext: adoc
    println(parsePath("/Users/westssun/kotlin-book/chapter.adoc"))

    // Dir: /Users/westssun/kotlin-book, filename: chapter, ext: adoc
    println(parsePathRegex("/Users/westssun/kotlin-book/chapter.adoc"))
}

/* 정규식 사용하기 */
fun parsePathRegex(path: String) {
    /*
    3중 따옴표 사용
    3중 따옴표 문자열에서는 어떤 문자도 이스케이프할 필요가 없다.
    예를들어, 일반 문자열을 사용해 정규식을 작성하는 경우 마침표 기호를 이스케이프하려면 \\.라고 써야하지만,
    3중 따옴표 문자열에서는 \.라고 쓰면 된다.

     */
    val regex = """(.+)/(.+)\.(.+)""".toRegex()
    val matchResult = regex.matchEntire(path)

    if (matchResult != null) {
        val (directory, filename, extension) = matchResult.destructured
        println("Dir: $directory, filename: $filename, ext: $extension")
    }
}