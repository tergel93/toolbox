package me.tergel.toolbox

class RuntimeError(
    val code: Int, override val message: String
) : Exception()

