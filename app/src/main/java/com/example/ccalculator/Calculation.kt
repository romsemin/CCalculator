package com.example.ccalculator

import com.example.ccalculator.Operation.Companion.calculate
import com.example.ccalculator.Operation.Companion.getOperation
import com.example.ccalculator.Operation.Companion.getOperationPriority

class Calculation {

    companion object {

        fun calculateResult(input: String): String {

            if (!input.isInputSequenceValid())
                return INVALID_INPUT

            var result = 0.0

            val addAndSubtractOperands = mutableListOf<Double>()
            val addAndSubtractOperations = mutableListOf<Operation>()

            val operands = input
                .split("+", "-", "*", "/")
                .map { it.toDouble() }
                .toMutableList()

            val operations = input
                .filter {
                    Operation
                        .getOperationsCharList()
                        .contains(it)
                }
                .map {
                    it
                        .toString()
                        .getOperation()
                }

            for (i in 0 until operations.count()) {
                val operation = operations[i]
                val operandOne = operands[i]
                val operandTwo = operands[i + 1]

                if (operation.getOperationPriority() == 1) {
                    addAndSubtractOperands.add(operandOne)
                    addAndSubtractOperations.add(operation)
                } else {
                    try {
                        result = operation.calculate(
                            operandOne,
                            operandTwo
                        )
                    } catch (e: OperationThrowable) {
                        return e.errorMessage.toString()
                    }
                    operands[i + 1] = result
                }
            }

            addAndSubtractOperands.add(operands.last())

            for (i in 0 until addAndSubtractOperations.count()) {
                val operation = addAndSubtractOperations[i]
                val operandOne = addAndSubtractOperands[i]
                val operandTwo = addAndSubtractOperands[i + 1]

                result = operation.calculate(
                    operandOne,
                    operandTwo,
                )
                addAndSubtractOperands[i + 1] = result
            }
            return ("$input = $result")
        }


        private fun String.isInputSequenceValid(): Boolean {
            if (
                this.isEmpty() ||
                this.first().isCurrentCharAPoint() ||
                this.first().isCurrentCharAnOperation() ||
                this.last().isCurrentCharAPoint() ||
                this.last().isCurrentCharAnOperation()
            ) {
                return false
            }
            val sequenceArray = this.toCharArray()
            var isCharAnOperation = false
            var isCharAPoint = false

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


        private fun Char.isCurrentCharAnOperation(): Boolean =
            Operation.getOperationsCharList().contains(this)

        private fun Char.isCurrentCharAPoint(): Boolean = (this == '.')

        private const val INVALID_INPUT = "Error! Invalid input!"
    }
}