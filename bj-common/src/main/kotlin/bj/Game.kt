package bj

class Game(val shuffle: Boolean = true) {

    private var stay = false
    var deck = Deck(shuffle)
    val ph = Hand("Player")
    val dh = Hand("Dealer")

    init {
        deal()
    }

    fun hit() {
        ph.add(deck.takeNext())
    }

    fun stay() {
        while (dh.points < 17) dh.add(deck.takeNext())
        stay = true
    }

    fun deal() {
        stay = false
        if (deck.size < 30) deck = Deck(shuffle)
        ph.clear()
        dh.clear()
        ph.add(deck.takeNext())
        dh.add(deck.takeNext())
        ph.add(deck.takeNext())
        dh.add(deck.takeNext())
    }

    val isGameOver get() = stay || ph.points > 21

    val msg
        get() = when {
            !isGameOver -> "Press Hit or Stay"
            ph.points > 21 -> "Dealer Wins!"
            dh.points > 21 -> "Player Wins!"
            ph.points == dh.points -> "Tie!"
            ph.points > dh.points -> "Player Wins!"
            dh.points > ph.points -> "Dealer Wins!"
            else -> throw IllegalStateException()
        }

    fun print() {
        println("Game")
        ph.print()
        dh.print()
        println("  Message: $msg")
        println()
    }

}