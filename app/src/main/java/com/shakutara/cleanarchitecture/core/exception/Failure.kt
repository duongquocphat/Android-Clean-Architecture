package com.shakutara.cleanarchitecture.core.exception

sealed class Failure {
    object NetworkError : Failure()
    object ServerError : Failure()

    abstract class FeatureError : Failure()
}