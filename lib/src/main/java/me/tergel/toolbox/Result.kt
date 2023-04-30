package me.tergel.toolbox

sealed class Result<out T> {
    class Success<T>(val data: T) : Result<T>()
    class Failure(val e: RuntimeError) : Result<Nothing>()

    fun isSucceed() = this is Success

    fun isFailed() = this is Failure

    fun unwrap() = (this as Success).data

    fun error() = (this as Failure).e

}

fun <T, R> Result<T>.onSuccessResult(then: (T) -> Result<R>): Result<R> {
    return when (this) {
        is Result.Success -> then(this.data)
        is Result.Failure -> this
    }
}

fun <T, R> Result<T>.onSuccess(then: (T) -> R): Result<R> {
    return when (this) {
        is Result.Success -> Result.Success(then(this.data))
        is Result.Failure -> this
    }
}