package com.example.ccalculator

class CalculationThrowable(val calculationError: CalculationError, val errorMessage: String? = null) :
    Throwable(calculationError.descriptionId)