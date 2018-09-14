package shekibobo.github.io.coinside.extensions

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.disposedBy(disposer: CompositeDisposable) = disposer.add(this)
