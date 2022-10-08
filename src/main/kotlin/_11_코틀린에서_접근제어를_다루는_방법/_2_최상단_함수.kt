package _11_코틀린에서_접근제어를_다루는_방법

// 최상단 함수
// Decompile : final 클래스로 생성되고, 이 메서드는 static final 로 생성된다.
fun isDirectoryPath(path: String): Boolean {
    return path.endsWith("/")
}