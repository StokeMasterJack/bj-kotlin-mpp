package bj

fun main(args: Array<String>) {
    val g = Game(shuffle = false)
    g.print()

    g.hit()
    g.print()

    g.hit()
    g.print()

    g.stay()
    g.print()
}


