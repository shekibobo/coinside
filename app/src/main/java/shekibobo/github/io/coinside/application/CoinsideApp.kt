package shekibobo.github.io.coinside.application

import android.app.Application
import shekibobo.github.io.coinside.BuildConfig
import timber.log.Timber

class CoinsideApp : Application() {

  override fun onCreate() {
    super.onCreate()

    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    } else {
      Timber.plant(CrashReportingTree())
    }
  }
}
