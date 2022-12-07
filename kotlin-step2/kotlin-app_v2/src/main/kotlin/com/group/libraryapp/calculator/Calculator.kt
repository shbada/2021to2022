package com.group.libraryapp.calculator

class Calculator(
    var number: Int // 강의/코드의 간결성을 위해 private -> public으로 변경하자.
) {
    fun add(operand: Int) {
        this.number += operand
    }

    fun minus(operand: Int) {
        this.number -= operand
    }

    fun multiply(operand: Int) {
        this.number *= operand
    }

    fun divide(operand: Int) {
        if (operand == 0) {
            throw IllegalArgumentException("0으로 나눌 수 없습니다.")
        }

        this.number /= operand
    }
}

//class Calculator(
//    private var _number: Int // _number로 변경
//) {
//    // 외부에서 접근 가능한 프로퍼티 생성 (커스텀 get())
//    val number: Int
//        get() = this._number
//
//    fun add(operand: Int) {
//        this._number += operand
//    }
//
//    fun minus(operand: Int) {
//        this._number -= operand
//    }
//
//    fun multiply(operand: Int) {
//        this._number *= operand
//    }
//
//    fun divide(operand: Int) {
//        if (operand == 0) {
//            throw IllegalArgumentException("0으로 나눌 수 없습니다.")
//        }
//
//        this._number /= operand
//    }
//}