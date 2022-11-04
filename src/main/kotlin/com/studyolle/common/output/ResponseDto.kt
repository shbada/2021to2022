package com.studyolle.common.output

class ResponseDto<T> {
    var code: String = "00"
    var message: String? = null
    var data: T? = null
}