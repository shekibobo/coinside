package shekibobo.github.io.coinside.ui.currencies

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.list_item_currency_price.view.currencyHigh
import kotlinx.android.synthetic.main.list_item_currency_price.view.currencyLogo
import kotlinx.android.synthetic.main.list_item_currency_price.view.currencyLow
import kotlinx.android.synthetic.main.list_item_currency_price.view.currencyMid
import kotlinx.android.synthetic.main.list_item_currency_price.view.currencyName
import kotlinx.android.synthetic.main.list_item_currency_price.view.currencySymbol
import shekibobo.github.io.coinside.R
import shekibobo.github.io.coinside.bitfinex.models.CRYPTO_SYMBOL_BITCOIN
import shekibobo.github.io.coinside.bitfinex.models.CRYPTO_SYMBOL_BITCOIN_CASH
import shekibobo.github.io.coinside.bitfinex.models.CRYPTO_SYMBOL_ETHEREUM
import shekibobo.github.io.coinside.bitfinex.models.CRYPTO_SYMBOL_LITECOIN
import shekibobo.github.io.coinside.bitfinex.models.CRYPTO_SYMBOL_NEO
import shekibobo.github.io.coinside.bitfinex.models.CryptoSymbol
import shekibobo.github.io.coinside.bitfinex.models.TickerItem

class CurrencyTickerItem(val currency: TickerItem) : Item() {
  override fun getLayout(): Int = R.layout.list_item_currency_price
  override fun getId(): Long = currency.symbol!!.hashCode().toLong()

  override fun bind(viewHolder: ViewHolder, position: Int) {
    viewHolder.containerView.apply {
      currencyLogo.setImageResource(logoForCurrency(currency.symbol))
      currencyName.text = currency.name
      currencySymbol.text = currency.symbol
      currencyHigh.text = context.getString(R.string.ticker_high, currency.high)
      currencyMid.text = context.getString(R.string.ticker_mid, currency.mid)
      currencyLow.text = context.getString(R.string.ticker_low, currency.low)
    }
  }
}

fun logoForCurrency(symbol: CryptoSymbol?): Int = when (symbol) {
  CRYPTO_SYMBOL_BITCOIN -> R.drawable.bitcoin
  CRYPTO_SYMBOL_BITCOIN_CASH -> R.drawable.bitcoin_cash
  CRYPTO_SYMBOL_ETHEREUM -> R.drawable.ethereum
  CRYPTO_SYMBOL_LITECOIN -> R.drawable.litecoin
  CRYPTO_SYMBOL_NEO -> R.drawable.neo
  else -> 0
}
