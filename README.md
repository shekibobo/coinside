# coinside
A simple cryptocurrency ticker app for Android.

# Requirements

Pull and display the prices for the following 5 cryptocurrencies:
- Bitcoin: BTCUSD
- Bitcoin Cash: BCHUSD
- Ethereum: ETHUSD
- Litecoin: LTCUSD
- Neo: NEOUSD

Use the https://bitfinex.readme.io/v1/reference#rest-public-ticker endpoint to retrieve the prices.
Display the results in the app.

# Architecture

This application is written using Android's Architecture Components, along with RxJava, Dagger, and Retrofit, written in Kotlin.

The `CurrenciesActivity` injects the currencies view model into the app using the factory provided by the dependency graph. It requests currencies from the view model, and waits for the response to display them.

The view model, in turn, calls the repository, which handles making the request and mapping it to what we want. It first makes a request to find out what symbols are available, filters that list for the currency symbols we want, and then request the ticker data for each symbol. Each of those individual requests are updated to include the symbol in the model, collected into a list, and then sorted. The view model subscribes to the `Single` that the repository provides, and posts that to the live data that is observed by the view.
