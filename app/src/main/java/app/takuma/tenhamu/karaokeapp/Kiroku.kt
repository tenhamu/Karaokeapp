package app.takuma.tenhamu.karaokeapp

import io.realm.RealmObject
import java.util.*

open class Kiroku (
    open var createdAt: Date = Date(System.currentTimeMillis()),
    open var score: Int = 0,
    open var songname: String = ""
) : RealmObject()