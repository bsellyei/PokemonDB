package hu.bme.aut.android.pokemondb.mock.network

import dagger.Module
import dagger.Binds
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import hu.bme.aut.android.pokemondb.di.NetworkModule
import hu.bme.aut.android.pokemondb.network.PokemonService
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
abstract class MockNetworkModule {

    @Binds
    @Singleton
    abstract fun providePokemonService(mockPokemonService: MockPokemonService): PokemonService
}