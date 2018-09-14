package shekibobo.github.io.coinside.bitfinex

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.Scheduler
import okhttp3.HttpUrl
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import shekibobo.github.io.coinside.network.NetworkModule
import shekibobo.github.io.coinside.network.NetworkScheduler

@Module(includes = [NetworkModule::class])
class BitfinexModule {
  @Provides @Reusable fun provideBitfinexApi(retrofit: Retrofit): BitfinexApi {
    return retrofit.create(BitfinexApi::class.java)
  }

  @Provides @Reusable fun provideRetrofit(
    baseUrl: HttpUrl,
    moshi: Moshi,
    @NetworkScheduler networkScheduler: Scheduler
  ): Retrofit {
    return Retrofit.Builder()
      .baseUrl(baseUrl)
      .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(networkScheduler))
      .addConverterFactory(MoshiConverterFactory.create(moshi))
      .build()
  }

  @Provides @Reusable fun provideMoshi(): Moshi {
    return Moshi.Builder().forBitfinex()
  }
}
