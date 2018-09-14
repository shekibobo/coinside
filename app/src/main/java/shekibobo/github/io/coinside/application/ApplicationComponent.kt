package shekibobo.github.io.coinside.application

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import shekibobo.github.io.coinside.bitfinex.BitfinexModule
import javax.inject.Singleton

@Singleton
@Component(
  modules = [
    AndroidModule::class,
    BitfinexModule::class,
    ActivityInjector::class
  ]
)
interface ApplicationComponent {
  fun inject(app: CoinsideApp)

  @Component.Builder interface Builder {
    fun build(): ApplicationComponent

    @BindsInstance fun application(application: Application): Builder
  }
}
