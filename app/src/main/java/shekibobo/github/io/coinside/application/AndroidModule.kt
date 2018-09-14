package shekibobo.github.io.coinside.application

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides

@Module
class AndroidModule {
  @Provides fun applicationContext(application: Application): Context = application

  @Provides fun provideSharedPreferences(context: Context): SharedPreferences =
    context.getSharedPreferences("DEFAULT", Context.MODE_PRIVATE)
}
