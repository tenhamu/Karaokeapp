package app.takuma.tenhamu.karaokeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_touroku.*
import kotlinx.android.synthetic.main.item_song_data_cell.*

class touroku : AppCompatActivity() {
    val realm: Realm = Realm.getDefaultInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_touroku)
        saveButton.setOnClickListener {
            val songName = songText.text
            val singerName = singerText.text
            val keyNumber = keyText.text
            create(songName.toString(),singerName.toString(),keyNumber.toString())
            val mainPage = Intent(this,MainActivity::class.java)
            startActivity(mainPage)
            finish()

        }
    }




    fun create(song: String,singer: String,key: String) {
        realm.executeTransaction {
            val memo = it.createObject(Memo::class.java)
            memo.song = song
            memo.singer = singer
            memo.key = key
        }
    }
}
