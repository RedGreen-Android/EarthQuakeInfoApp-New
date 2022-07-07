package com.example.earthquakeinfoapp.util

sealed class Status {
    object SUCCESS :Status()
    object ERROR :Status()
    object LOADING :Status()
}