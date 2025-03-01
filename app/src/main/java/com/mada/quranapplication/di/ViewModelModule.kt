package com.mada.quranapplication.di

import androidx.lifecycle.ViewModel
import com.mada.quranapplication.presentation.mvvm.QuranReaderViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(QuranReaderViewModel::class)
    abstract fun bindQuranReaderViewModel(viewModel: QuranReaderViewModel): ViewModel
}