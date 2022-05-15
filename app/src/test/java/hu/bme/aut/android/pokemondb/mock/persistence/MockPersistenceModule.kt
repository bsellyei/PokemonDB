package hu.bme.aut.android.pokemondb.mock.persistence

import android.app.Application
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import hu.bme.aut.android.pokemondb.di.PersistenceModule
import hu.bme.aut.android.pokemondb.persistence.AppDatabase
import hu.bme.aut.android.pokemondb.persistence.PokemonDao
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [PersistenceModule::class]
)
abstract class MockPersistenceModule {
    @Binds
    @Singleton
    abstract fun providePokemonDao(mockPokemonDao: MockPokemonDao): PokemonDao
}