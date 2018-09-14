@file:Suppress("unused")

package shekibobo.github.io.coinside.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

inline fun <reified T : ViewModel> AppCompatActivity.getViewModel(factory: ViewModelProvider.Factory): T {
  return ViewModelProviders.of(this, factory).get(T::class.java)
}

inline fun <reified T : ViewModel> AppCompatActivity.getViewModel(crossinline factory: () -> T): T {

  @Suppress("UNCHECKED_CAST")
  val vmFactory = object : ViewModelProvider.Factory {
    override fun <U : ViewModel> create(modelClass: Class<U>): U = factory() as U
  }

  return ViewModelProviders.of(this, vmFactory)[T::class.java]
}

inline fun <reified T : ViewModel> AppCompatActivity.withViewModel(
  crossinline factory: () -> T,
  body: T.() -> Unit
): T {
  val vm = getViewModel(factory)
  vm.body()
  return vm
}

fun <T, R> LiveData<T>.switchMap(func: (T) -> LiveData<R>): LiveData<R> =
  Transformations.switchMap(this, func)

fun <T, R> LiveData<T>.map(function: (T) -> R): LiveData<R> =
  Transformations.map(this, function)
