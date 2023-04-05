package com.study.spotilist.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.rules.TestRule

open class BaseUnitTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineTestRule = MainCoroutineScopeRule()

    @get:Rule
    var instantTask: TestRule = InstantTaskExecutorRule()

}