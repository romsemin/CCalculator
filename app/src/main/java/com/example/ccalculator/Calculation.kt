package com.example.ccalculator

import com.example.ccalculator.Operations.Companion.calculate
import com.example.ccalculator.Operations.Companion.getOperation
import com.example.ccalculator.Operations.Companion.getOperationPriority

class Calculation {

    companion object {

        fun calculateResult(input: String): String {
            var result = 0.0

            if (!input.isInputSequenceValid()) {
                return INVALID_INPUT
            }

            val addAndSubtractOperands = mutableListOf<Double>()
            val addAndSubtractOperations = mutableListOf<Operations>()

            val operands = input
                .split("+", "-", "*", "/")
                .map { it.toDouble() }
                .toMutableList()


            val operations = input
                .filter { Operations
                    .getOperationsCharList()
                    .contains(it)
                }
                .map { it
                    .toString()
                    .getOperation()
                }

            for (i in 0 until operations.count()) {
                val operation = operations[i]
                val operandOne = operands[i]
                val operandTwo = operands[i+1]

                if (operation.getOperationPriority() == 1) {
                    addAndSubtractOperands.add(operandOne)
                    addAndSubtractOperations.add(operation)
                } else {
                    try {
                        result = operation.calculate(
                            operandOne,
                            operandTwo
                        )
                    } catch (e: CalculationThrowable) {
                        return e.errorMessage.toString()
                    }
                    operands[i+1] = result
                }
            }

            addAndSubtractOperands.add(operands.last())

            for (i in 0 until addAndSubtractOperations.count()) {
                val operation = addAndSubtractOperations[i]
                val operandOne = addAndSubtractOperands[i]
                val operandTwo = addAndSubtractOperands[i+1]

                result = operation.calculate(
                    operandOne,
                    operandTwo,
                )
                addAndSubtractOperands[i+1] = result
            }
            return ("$input = $result")
        }

        private fun String.isInputSequenceValid(): Boolean {
            val sequenceArray = this.toCharArray()
            var isCharAnOperation = false
            var isCharAPoint = false

            if (this.first().isCurrentCharAPoint()
                || this.first().isCurrentCharAnOperation()
                || this.last().isCurrentCharAPoint()
                || this.last().isCurrentCharAnOperation()
            ) {
                return false
            }

            for (c in sequenceArray) {
                if (c.isCurrentCharAPoint()) {
                    if (isCharAPoint) {
                        return false
                    } else {
                        isCharAPoint = true
                    }
                } else if (c.isCurrentCharAnOperation()) {
                    if (isCharAnOperation) {
                        return false
                    } else {
                        isCharAnOperation = true
                    }
                } else if (c.isDigit()) {
                    isCharAPoint = false
                    isCharAnOperation = false
                }
            }
            return true
        }

        private fun Char.isCurrentCharAnOperation() : Boolean = Operations.getOperationsCharList().contains(this)

        private fun Char.isCurrentCharAPoint() : Boolean = (this == '.')

        private const val INVALID_INPUT = "Error! Invalid input!"
    }
}