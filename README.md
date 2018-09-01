# Multiplatform Kotlin Blackjack
Here is the problem with `java.util.Random`: 
Kotlin defines a standard library that is common for all platforms 
that Kotlin targets (JVM, JavaScript, Native). 
But `java.util.Random` is not part of that common subset. 

Kotlin-JVM apps must use `java.util.Random`. 
Where Kotlin-JS apps have to use the function `kotlin.js.Math.random` instead.

Below are a few tips for cross-platform kotlin.

## Kotlin-JS (JavaScript)
You already know how to create Kotlin-JVM apps. 
So now you need to learn how to create Kotlin-JS apps. 
Here is a tutorial I made for creating Kotlin-JS apps (it's free):

[Kotlin JavaScript Tutorial](https://medium.com/@daveford/kotlin-javascript-tutorial-e8ce1aa885ef)

## Multiplatform Projects
Once you know how to create Kotlin-JS and Kotlin-JVM apps, 
the next step is to create a *Multiplatform Project*. 
This is a new thing from JetBrains. 
The idea is that you divide your project into 3 sub-projects. 
For the blackjack project, you would have:

- bj-common
- bj-js
- bj-jvm. 

Here is what goes in each of these:

### bj-common
This contains our Kotlin code that can compile and run correctly 
on both the JVM and JavaScript platforms. 
This code will be compiled into both JVM bytecode and JavaScript. 

All of our blackjack logic classes (Card, Hand, Deck, Game) 
can go here except for the piece of code that computes a random number. 
For that, we define only stub, using the keyword `expect`:

```kotlin 
/**
 *  return a random number between 1 and 52 inclusive
 */
```

I added this code to the Deck file:<br/>
`<project-root>/bj-common/src/main/kotlin/bj/Deck.kt`


### bj-js
This contains our Kotlin code that only makes sense for JavaScript. This code will only be compiled into JavaScript.

This is where our JS-friendly version of  code to compute a random number goes. 
For this we use a the keyword `actual`:

```kotlin
actual fun rng52() = floor(Math.random() * 52).toInt()
```

This is in file:<br/>
`<project-root>/bj-js/src/main/kotlin/bj/Deck.kt`

We also put the JS-friendly Kotlin code to generate our HTML-based UI:<br/>
`<project-root>/bj-js/src/main/kotlin/bj/Main.kt`

### bj-jvm
Kotlin code that only makes sense for the JVM. This will only be compiled to JVM bytecode.

This is where our JVM-friendly Kotlin code to compute a random number goes:

```kotlin
private val rng = Random()
actual fun rng52() = rng.nextInt(52)
```

This is in file:<br/>
`<project-root>/bj-jvm/src/main/kotlin/bj/Deck.kt`


We also put our JVM friendly UI here. We don't have much a JVM UI:<br/>
`<project-root>/bj-jvm/src/main/kotlin/bj/Main.kt`

Our unit tests can go here also (junit is a JVM thing):<br/>
`<project-root>/bj-jvm/src/test/kotlin/bj/GameTest.kt`<br/>
`<project-root>/bj-jvm/src/test/kotlin/bj/DeckTest.kt`<br/>
`<project-root>/bj-jvm/src/test/kotlin/bj/HandTest.kt`<br/>
`<project-root>/bj-jvm/src/test/kotlin/bj/GameTest.kt`

## Multiplatform Blackjack Project
I have create a small sample "Multiplatform Project" of the blackjack example and put it on github. This shows you how everything works. 

Here are the steps to use this project:

1. Clone the project from github (from the command line):<br/>
    `git clone https://github.com/StokeMasterJack/bj-mpp.git`<br/>
    *This should create a folder called bj-mpp*
1. Make sure you have a recent version of IntelliJ
1. Make sure you have a recent version of Kotlin Plugin:<br/>
   *Tools -> Kotlin -> Configure Kotlin Plugin Updates*
1. Open the project bj-mpp in IntelliJ
1. Open the Gradle window:<br/> 
   *View -> Tool Windows -> Gradle*
1. From the Gradle window, run the bj-mpp *assemble* task:<br/>
    *bj-mpp -> Tasks -> Build -> Assemble (double click)*
1. To run the JS impl:
    1. Locate the file:<br/> 
       `<project-root>/bj-js/build/web/index.html`
    1. Right click on that file<br/>
    1. Choose Open in Browser<br/>
1. To run the JVM impl:
	1. Locate the file:<br/>
	   `<project-root>/bj-jvm/src/main/kotlin/bj/Main.kt`
	1. Right click on that file
	1. Choose Run

Here is some more detail on multiplatform projects: https://kotlinlang.org/docs/reference/multiplatform.html

### Side note
If all you were trying to do was create a version of blackjack that worked the same on Kotlin-JVM and Kotlin-JS, then you could just use List.shuffle(). That uses random numbers internally. But List.shuffle() is available for Kotlin-JVM and Kotlin-JS.
