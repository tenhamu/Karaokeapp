package app.takuma.tenhamu.karaokeapp

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Memo (
    @PrimaryKey open var id: String = UUID.randomUUID().toString(),
    open var song: String = "",
    open var singer: String = "",
    open var key: String = ""
) : RealmObject()