package com.droidli.factorial

sealed class State
object Error : State()
object Progress : State()
class Factorial(val factorial: String) : State()

