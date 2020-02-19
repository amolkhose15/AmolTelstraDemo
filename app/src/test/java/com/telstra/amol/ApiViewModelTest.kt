package com.telstra.amol

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.telstra.amolassignmenttestra.utils.APIClient
import com.telstra.amolassignmenttestra.utils.ApiInterface
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner.Silent::class)
class ApiViewModelTest {


    /*@get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()*/

    @Mock
    lateinit var apiClient: ApiInterface


    @Mock
    lateinit var lifecycle: Lifecycle


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        val owner: LifecycleOwner = mock(LifecycleOwner::class.java)
        lifecycle = LifecycleRegistry(owner)
        apiClient = APIClient.client.create(ApiInterface::class.java)

    }


    @Test
    fun checkNull() {
        assertNotNull(apiClient.getData())

    }


}