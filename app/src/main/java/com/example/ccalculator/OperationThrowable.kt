package com.example.ccalculator

class OperationThrowable(
    operationError: OperationError,
    val errorMessage: String? = null
) : Throwable(operationError.descriptionId)