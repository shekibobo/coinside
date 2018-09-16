package shekibobo.github.io.coinside.network

import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
  @Provides @Singleton
  fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
      .connectTimeout(10, TimeUnit.SECONDS)
      .readTimeout(10, TimeUnit.SECONDS)
      .writeTimeout(10, TimeUnit.SECONDS)
      .addInterceptor(loggingInterceptor)
      .build()
  }

  @Provides
  @Reusable
  fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
      Timber.tag("OkHttp").d(message)
    }).apply {
      level = HttpLoggingInterceptor.Level.BODY
    }
  }

  @Provides
  @NetworkScheduler
  fun provideNetworkScheduler(): Scheduler = Schedulers.io()
}
