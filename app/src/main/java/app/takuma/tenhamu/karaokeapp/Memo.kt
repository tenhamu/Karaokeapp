package app.takuma.tenhamu.karaokeapp

import io.realm.RealmObject

open class Memo (
    open var song: String = "",
    open var singer: String = "",
    open var key: String = ""
) : RealmObject()