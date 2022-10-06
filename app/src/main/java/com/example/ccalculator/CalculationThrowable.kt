package com.example.ccalculator

class CalculationThrowable(calculationError: CalculationError, val errorMessage: String? = null) :
    Throwable(calculationError.descriptionId)