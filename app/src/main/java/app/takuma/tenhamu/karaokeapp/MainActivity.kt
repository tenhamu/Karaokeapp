package app.takuma.tenhamu.karaokeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val realm: Realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val songList = readAll()

        val adapter = RecyclerViewAdapter(this, songList, object : RecyclerViewAdapter.OnItemClickListener {
            override  fun onItemClick(item: Memo) {
                val SongPage = Intent(this@MainActivity,songpage::class.java)
                startActivity(SongPage)

            }
        }, true )

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        floatingButton.setOnClickListener {
            val tourokuPage = Intent(this,touroku::class.java)
            startActivity(tourokuPage)

            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    fun readAll(): RealmResults<Memo> {
        return  realm.where(Memo::class.java).findAll()
    }

    fun createDummyData() {
        for (i in 0..10){
            create("曲名","歌手名","キー")

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
