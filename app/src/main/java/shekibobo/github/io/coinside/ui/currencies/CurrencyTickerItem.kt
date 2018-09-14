package shekibobo.github.io.coinside.ui.currencies

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.list_item_currency_price.view.currencyHigh
import kotlinx.android.synthetic.main.list_item_currency_price.view.currencyLow
import kotlinx.android.synthetic.main.list_item_currency_price.view.currencyMid
import kotlinx.android.synthetic.main.list_item_currency_price.view.currencySymbol
import shekibobo.github.io.coinside.R
import shekibobo.github.io.coinside.bitfinex.models.TickerItem

class CurrencyTickerItem(val currency: TickerItem): Item() {
  override fun getLayout(): Int = R.layout.list_item_currency_price
  override fun getId(): Long = currency.symbol!!.hashCode().toLong()

  override fun bind(viewHolder: ViewHolder, position: Int) {
    viewHolder.containerView.apply {
      currencySymbol.text = currency.symbol
      currencyHigh.text = context.getString(R.string.ticker_high, currency.high)
      currencyMid.text = context.getString(R.string.ticker_mid, currency.mid)
      currencyLow.text = context.getString(R.string.ticker_low, currency.low)
    }
  }
}
