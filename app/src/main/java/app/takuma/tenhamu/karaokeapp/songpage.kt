package app.takuma.tenhamu.karaokeapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_songpage.*
import kotlinx.android.synthetic.main.item_score_data_cell.*
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

class songpage : AppCompatActivity() {
    val realm: Realm = Realm.getDefaultInstance()
    var songTitle: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_songpage)

        val id: String = intent.getStringExtra("SONG")
        Log.d("Intent",id)
        realm.executeTransaction {
            val items = realm.where(Memo::class.java).equalTo("id", id).findFirst()
                ?: return@executeTransaction
            songTitle = items.song
            songView.text = songTitle
            singerView.text = items.singer
            keyView.text = items.key
    }
        val scoreList = readAll()

        val adapter = ScoreViewAdapter(this, scoreList , true)
        recyclerView2.setHasFixedSize(true)
        recyclerView2.layoutManager = LinearLayoutManager(this)
        recyclerView2.adapter = adapter

        KirokuButton.setOnClickListener {

            val myedit = EditText(this)

            AlertDialog.Builder(this)
                .setTitle(songTitle)
                .setView(myedit)
                .setPositiveButton("保存", DialogInterface.OnClickListener() {_,_->
                    val scoreNumber = myedit.text
                    val songName = songTitle
                    create(songName.toString(),scoreNumber.toString())


                })
                .setNegativeButton("キャンセル", null)
                .show()


        }







    }
    fun readAll(): RealmResults<Kiroku> {
        return  realm.where(Kiroku::class.java)
            .contains("songname",songTitle)
            .findAll()
    }

    fun create(song: String,score: String) {
        realm.executeTransaction {
            val kiroku = it.createObject(Kiroku::class.java)

            kiroku.score = score
            kiroku.songname =  song
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }}
