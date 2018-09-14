package shekibobo.github.io.coinside.ui.currencies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import dagger.android.AndroidInjection
import shekibobo.github.io.coinside.R
import shekibobo.github.io.coinside.extensions.getViewModel
import javax.inject.Inject

class CurrenciesActivity : AppCompatActivity() {
  @Inject lateinit var tickerVmFactory: CurrencyTickerViewModel.Factory
  private lateinit var tickerViewModel: CurrencyTickerViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_currencies)

    tickerViewModel = getViewModel(tickerVmFactory)

    tickerViewModel.currencies
      .observe(this, Observer {

      })
  }
}
