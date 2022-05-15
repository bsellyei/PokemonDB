package hu.bme.aut.android.pokemondb.test

import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import hu.bme.aut.android.pokemondb.mock.network.MockPokemonService
import hu.bme.aut.android.pokemondb.mock.persistence.MockPokemonDao
import hu.bme.aut.android.pokemondb.persistence.AppDatabase
import hu.bme.aut.android.pokemondb.ui.main.MainRepository
import hu.bme.aut.android.pokemondb.ui.main.MainViewModel
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
class MainViewModelTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var repository: MainRepository

    @BindValue
    @JvmField
    val mockService: MockPokemonService = MockPokemonService()

    @BindValue
    @JvmField
    val mockDao: MockPokemonDao = MockPokemonDao()

    @Mock
    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        hiltRule.inject()
        viewModel = MainViewModel(repository)
    }

    @Test
    fun testGetPokemonsSuccess() {
        viewModel.getPokemons()
        assertEquals("test", viewModel.pokemons.species!!.name)
        assertEquals("testURL", viewModel.pokemons.species!!.url)
    }
}