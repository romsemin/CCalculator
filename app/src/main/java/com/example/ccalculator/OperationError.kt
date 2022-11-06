package com.example.ccalculator

enum class OperationError(val descriptionId: String) {
    DIVISION_BY_ZERO("Division by zero"),
    UNKNOWN_OPERATION("Unknown operation")
}