package bj

import kotlinx.html.FlowContent
import kotlinx.html.b
import kotlinx.html.button
import kotlinx.html.div
import kotlinx.html.dom.create
import kotlinx.html.h1
import kotlinx.html.js.div
import kotlinx.html.js.onClickFunction
import kotlinx.html.style
import kotlin.browser.document
import kotlin.dom.clear

val g = Game()

fun main(args: Array<String>) {
    refresh()
}

fun refresh() {
    val rootDiv = document.create.div {
        gameView(g)
    }
    document.body!!.clear()
    document.body!!.appendChild(rootDiv)
}

fun FlowContent.gameView(g: Game) {
    h1 { +"Blackjack" }
    div {
        //buttons
        button {
            disabled = !g.isGameOver
            +"Deal"
            onClickFunction = {
                g.deal()
                refresh()
            }
        }
        button {
            disabled = g.isGameOver
            +"Hit"
            onClickFunction = {
                g.hit()
                refresh()
            }
        }
        button {
            disabled = g.isGameOver
            +"Stay"
            onClickFunction = {
                g.stay()
                refresh()
            }
        }
    }
    div {
        style = "display:flex"
        handView(g.ph)
        handView(g.dh)
    }
    div {
        +g.msg
    }
}

fun FlowContent.handView(h: Hand) {
    div {
        style = "height:10rem;width:10rem;padding:1rem;margin:1rem;background:#EEEEEE"
        div { b { +"${h.name} Name" } }
        div {
            h.cards.forEach {
                div { +it.name }
            }
        }
        div { b { +"${h.points} Points" } }
    }
}