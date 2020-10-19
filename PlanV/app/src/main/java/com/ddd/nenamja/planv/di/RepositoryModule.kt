package com.ddd.nenamja.planv.di

import com.ddd.nenamja.planv.data.remote.repository.PlanVRepository
import com.ddd.nenamja.planv.data.remote.repository.PlanVRepositoryImpl
import org.koin.dsl.module


val repositoryModule = module {

    single<PlanVRepository> {
        PlanVRepositoryImpl(
            get()
        )
    }

}