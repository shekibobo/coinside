package shekibobo.github.io.coinside

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import dagger.android.AndroidInjection
import shekibobo.github.io.coinside.extensions.getViewModel
import shekibobo.github.io.coinside.ui.main.CurrencyTickerViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
  @Inject lateinit var tickerVmFactory: CurrencyTickerViewModel.Factory
  private lateinit var tickerViewModel: CurrencyTickerViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_activity)

    tickerViewModel = getViewModel(tickerVmFactory)

    tickerViewModel.currencies
      .observe(this, Observer {

      })
  }
}
