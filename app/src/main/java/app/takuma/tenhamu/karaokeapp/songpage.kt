package app.takuma.tenhamu.karaokeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_songpage.*

class songpage : AppCompatActivity() {
    val realm: Realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_songpage)
        val id: String = intent.getStringExtra("SONG")
        Log.d("Intent",id)
        realm.executeTransaction {
            val items = realm.where(Memo::class.java).equalTo("id", id).findFirst()
                ?: return@executeTransaction
            songView.text = items.song
            singerView.text = items.singer
            keyView.text = items.key
    }


}}
