package shekibobo.github.io.coinside.application

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import okhttp3.HttpUrl
import shekibobo.github.io.coinside.BuildConfig
import timber.log.Timber
import javax.inject.Inject

class CoinsideApp : Application(), HasActivityInjector {
  private val component: ApplicationComponent by lazy {
    DaggerApplicationComponent.builder()
      .application(this)
      .currenciesBaseUrl(HttpUrl.parse("https://api.bitfinex.com/")!!)
      .build()
  }

  @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>
  override fun activityInjector(): AndroidInjector<Activity> = activityInjector

  override fun onCreate() {
    super.onCreate()

    injectComponent()

    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    } else {
      Timber.plant(CrashReportingTree())
    }
  }

  private fun injectComponent() {
    component.inject(this)
  }
}
