fun main(args: Array<String>) {
    val name = if (args.isNotEmpty()) args[0] else "Kotlin"
    println("Hello, $name!")

    // Bob을 인자로 넘기면 "Hello Bob", 인자가 없으면 "Hello Kotlin"
}