package app.takuma.tenhamu.karaokeapp

import io.realm.RealmObject
import java.util.*

open class Kiroku (
    open var createdAt: Date = Date(System.currentTimeMillis()),
    open var score: String = "",
    open var songname: String = ""
) : RealmObject()