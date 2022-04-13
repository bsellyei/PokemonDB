package hu.bme.aut.android.pokemondb.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import hu.bme.aut.android.pokemondb.network.PokemonService
import hu.bme.aut.android.pokemondb.persistence.PokemonDao
import hu.bme.aut.android.pokemondb.ui.details.DetailsRepository
import hu.bme.aut.android.pokemondb.ui.main.MainRepository

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
}