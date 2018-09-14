package shekibobo.github.io.coinside.ui.currencies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Reusable
import io.reactivex.disposables.CompositeDisposable
import shekibobo.github.io.coinside.bitfinex.CurrencyRepository
import shekibobo.github.io.coinside.bitfinex.models.TickerItem
import shekibobo.github.io.coinside.extensions.disposedBy
import timber.log.Timber
import javax.inject.Inject

class CurrencyTickerViewModel(private val currencyRepository: CurrencyRepository) : ViewModel() {
  val currencies: MutableLiveData<List<TickerItem>> = MutableLiveData()

  private val disposer: CompositeDisposable = CompositeDisposable()

  fun fetchCurrencies() {
    currencyRepository.allTickers()
      .subscribe(
        { list ->
          currencies.postValue(list.sortedByDescending { it.high.toFloat() })
        },
        {
          Timber.e(it)
          currencies.postValue(listOf())
        }
      )
      .disposedBy(disposer)
  }

  override fun onCleared() {
    disposer.clear()
    super.onCleared()
  }

  @Reusable
  class Factory @Inject constructor(private val currencyRepository: CurrencyRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
      if (modelClass.isAssignableFrom(CurrencyTickerViewModel::class.java)) {
        @Suppress("UNCHECKED_CAST")
        return CurrencyTickerViewModel(currencyRepository) as T
      }
      throw IllegalArgumentException("Unknown ViewModel Class ${modelClass.canonicalName}")
    }
  }
}


