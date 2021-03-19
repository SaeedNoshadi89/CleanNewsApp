package com.sn.cleannewsapp.utils

data class Resource<out T, out S>(val status: Status, val data: T? = null, val message: S? = null){
    companion object{
        fun<T, S> success(data: T? = null): Resource<T, S> = Resource(status = Status.SUCCESS, data = data, message = null)
        fun<T, S> error(data: T? = null, message: S): Resource<T, S> = Resource(status = Status.ERROR, data = data, message = message)
        fun<T, S> loading(data: T?): Resource<T, S> = Resource(status = Status.LOADING, data = data, message = null)
    }
}
