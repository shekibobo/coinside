package shekibobo.github.io.coinside.ui.currencies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_currencies.currenciesTicker
import shekibobo.github.io.coinside.R
import shekibobo.github.io.coinside.extensions.getViewModel
import javax.inject.Inject

class CurrenciesActivity : AppCompatActivity() {
  @Inject lateinit var tickerVmFactory: CurrencyTickerViewModel.Factory
  private lateinit var tickerViewModel: CurrencyTickerViewModel

  private val currenciesAdapter = GroupAdapter<ViewHolder>()
  private val currenciesSection by lazy { Section() }

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_currencies)

    tickerViewModel = getViewModel(tickerVmFactory)

    currenciesAdapter.add(currenciesSection)
    currenciesTicker.apply {
      layoutManager = LinearLayoutManager(this@CurrenciesActivity)
      adapter = currenciesAdapter
    }

    tickerViewModel.currencies
      .observe(this, Observer { currencies ->
        currencies.map { CurrencyTickerItem(it) }.let { currenciesSection.update(it) }
      })
    tickerViewModel.fetchCurrencies()
  }
}
