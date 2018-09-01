package bj


/**
 *  return a random number between 1 and 52 inclusive
 */
expect fun rng52():Int

class Deck(shuffle: Boolean = true) {

    private val _cards = mutableListOf<Card>()

    val cards: List<Card> get() = _cards

    val size get() = _cards.size

    init {
        for (s in 1..4) {
            for (v in 1..13) {
                _cards.add(Card(v, s))
            }
        }

        if (shuffle) {
            repeat(10000) {
                val i1 = rng52()
                val i2 = rng52()
                val c1 = _cards[i1]
                val c2 = _cards[i2]
                _cards[i1] = c2
                _cards[i2] = c1
            }
        }


    }

    fun takeNext(): Card {
        return _cards.removeAt(0)
    }

}