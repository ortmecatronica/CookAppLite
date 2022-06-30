package com.example.cookapplite.LoginFeature.di

import com.example.cookapplite.LoginFeature.data.UsersRepository
import com.example.cookapplite.LoginFeature.framework.UserAuthenticationImpl
import com.example.cookapplite.LoginFeature.framework.UserDataSourceImpl
import com.example.cookapplite.LoginFeature.data.UserAuthentication
import com.example.cookapplite.LoginFeature.data.UserDataSource
import com.example.cookapplite.LoginFeature.ui.viewmodel.AddUserViewModel
import com.example.cookapplite.LoginFeature.ui.viewmodel.LoginViewModel
import com.example.cookapplite.LoginFeature.usecases.CreateUser
import com.example.cookapplite.LoginFeature.usecases.LoginUser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideUserAuthentication() : UserAuthentication = UserAuthenticationImpl()

    @Provides
    fun provideUserDataSource() : UserDataSource = UserDataSourceImpl()

    @Provides
    fun provideUsersRepository(userAuthentication: UserAuthentication, userDataSource: UserDataSource) : UsersRepository = UsersRepository(userAuthentication, userDataSource)

    @Provides
    fun provideCreateUser(usersRepository: UsersRepository) : CreateUser = CreateUser(usersRepository)

    @Provides
    fun provideLoginUser(usersRepository: UsersRepository) : LoginUser = LoginUser(usersRepository)

    @Provides
    fun provideLoginViewModel(loginUser: LoginUser) : LoginViewModel = LoginViewModel(loginUser)

    @Provides
    fun provideAddUserViewModel(createUser: CreateUser) : AddUserViewModel = AddUserViewModel(createUser)

}