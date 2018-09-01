package bj

class Hand(val name: String) {

    private val _cards: MutableList<Card> = mutableListOf()

    val cards: List<Card> get() = _cards

    val points get() = _cards.sumBy { it.points }

    val size get() = _cards.size

    fun add(c: Card) {
        _cards.add(c)
    }

    fun clear() {
        _cards.clear()
    }

    fun print() {
        println("  $name Hand")
        _cards.forEach { println("    ${it.name}") }
        println("    $points Points")
    }


}