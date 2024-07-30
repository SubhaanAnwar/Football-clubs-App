## Football League Search
This Android application allows users to search for football leagues and teams, leveraging both local caching and a remote API to provide results.

### Features
* Local Search: Instantly search for leagues stored in the local database.
* Remote Search: Fetch up-to-date league information from The Sports DB API: https://www.thesportsdb.com/.
* Offline Caching: Store the results of remote searches for future offline availability.
* Reactive UI: Built with Jetpack Compose for a modern and dynamic user interface.

### Technical Overview
* _Architecture_: Model-View-ViewModel (MVVM) pattern.
* Kotlin: Primary programming language.
* Jetpack Compose: UI toolkit (https://developer.android.com/jetpack/compose).
* Coroutines: For asynchronous operations and structured concurrency (https://kotlinlang.org/docs/coroutines-overview.html).
* ViewModel + StateFlow/SharedFlow: Manage data state and UI updates.
* Room Persistence Library: Local database (https://developer.android.com/training/data-storage/room).
* Retrofit: For network API interactions (https://square.github.io/retrofit/).
* Dagger Hilt: Dependency injection (https://dagger.dev/hilt/).
* Coil: For Image loading

### Setup
1. Clone the repository.
2. Obtain an API Key from The Sports DB (if necessary).
3. Install the Android SDK and set up an emulator or device
4. Run the project in Android Studio.
