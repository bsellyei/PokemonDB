package hu.bme.aut.android.pokemondb.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.android.pokemondb.persistence.AppDatabase
import hu.bme.aut.android.pokemondb.persistence.PokemonDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {
}