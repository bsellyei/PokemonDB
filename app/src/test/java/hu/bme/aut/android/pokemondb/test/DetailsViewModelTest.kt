package hu.bme.aut.android.pokemondb.test

import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import hu.bme.aut.android.pokemondb.mock.network.MockPokemonService
import hu.bme.aut.android.pokemondb.mock.persistence.MockPokemonDao
import hu.bme.aut.android.pokemondb.ui.details.DetailsRepository
import hu.bme.aut.android.pokemondb.ui.details.DetailsViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [25], application = HiltTestApplication::class)
class DetailsViewModelTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var repository: DetailsRepository

    @BindValue
    @JvmField
    val mockService: MockPokemonService = MockPokemonService()

    @BindValue
    @JvmField
    val mockDao: MockPokemonDao = MockPokemonDao()

    @Mock
    private lateinit var viewModel: DetailsViewModel

    @Before
    fun setup() {
        hiltRule.inject()
        viewModel = DetailsViewModel(repository)
    }

    @Test
    fun getPokemonTestSuccess() = runBlocking {
        val id = 1
        viewModel.getPokemon(id.toLong())
        val pokemon = viewModel.pokemon.first()
        assertEquals("bulbasaur", pokemon.name)
        assertEquals(35, pokemon.hp)
    }
}