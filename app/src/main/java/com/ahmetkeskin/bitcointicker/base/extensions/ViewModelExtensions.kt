package com.ahmetkeskin.bitcointicker.base

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

fun <CLS> Class<*>.findGenericWithType(targetClass: Class<*>): Class<out CLS>? {
    var currentType: Type? = this

    while (true) {
        val answerClass =
            (currentType as? ParameterizedType)?.actualTypeArguments // get current arguments
                ?.mapNotNull { it as? Class<*> } // cast them to class
                ?.findLast { targetClass.isAssignableFrom(it) } // check if it is a target (ViewModel for example)

        // We found a target (ViewModel)
        if (answerClass != null) {
            @Suppress("UNCHECKED_CAST")
            return answerClass as Class<out CLS>?
        }

        currentType = when (currentType) {
            is Class<*> -> currentType.genericSuperclass // Not a ParameterizedType so go to parent
            is ParameterizedType -> currentType.rawType // a parameterized type which we could't find any ViewModel yet, so the raw type (parent) should have it
            else -> return null // or throw an exception
        }
    }
}
