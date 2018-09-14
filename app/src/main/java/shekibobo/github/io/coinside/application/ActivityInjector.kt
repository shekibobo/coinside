package shekibobo.github.io.coinside.application

import dagger.Module
import dagger.android.ContributesAndroidInjector
import shekibobo.github.io.coinside.ui.currencies.CurrenciesActivity

@Module
abstract class ActivityInjector {
  @ContributesAndroidInjector
  abstract fun contributeCurrenciesActivityInjector(): CurrenciesActivity
}
