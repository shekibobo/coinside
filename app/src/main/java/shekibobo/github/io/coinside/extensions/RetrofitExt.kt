package shekibobo.github.io.coinside.extensions

import retrofit2.HttpException
import retrofit2.Response

fun <T> Response<T>.bodyOrError(): T =
  if (isSuccessful) body()!! else throw HttpException(this)
