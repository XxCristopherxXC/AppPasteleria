package com.milsabores.pasteleria.viewmodel

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CarritoViewModelTest {

    private lateinit var viewModel: CarritoViewModel

    @Before
    fun setup() {
        viewModel = CarritoViewModel()
    }

    @Test
    fun `carrito inicia vacío`() = runTest {
        viewModel.carrito.test {
            val items = awaitItem()
            assertThat(items.isEmpty()).isTrue()
        }
    }
}

