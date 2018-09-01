@file:Suppress("DEPRECATION")

package bj

import kotlin.js.Math
import kotlin.math.floor

actual fun rng52() = floor(Math.random() * 52).toInt()