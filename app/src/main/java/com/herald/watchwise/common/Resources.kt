package com.herald.watchwise.common

/**
 * A sealed class that represents the various states that the network calls can be in.
 *
 * @param T The type of data that the resource contains.
 * @property data The data contained within the resource.
 * @property message A message describing the state of the resource.
 */
sealed class Resources<T>(val data:T?= null,val message:String? = null){

    class Success<T>(data: T): Resources<T>(data)
    class Error<T>(data: T? = null,message: String): Resources<T>(data,message)
    class Loading<T>(data: T?=null): Resources<T>(data)
}
