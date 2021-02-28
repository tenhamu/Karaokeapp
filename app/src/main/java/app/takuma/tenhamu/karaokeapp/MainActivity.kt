package app.takuma.tenhamu.karaokeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    val realm: Realm = Realm.getDefaultInstance()
    private val swipeToDismissTouchHelper by lazy {
       // getSwipeToDismissTouchHelper(RecyclerViewAdapter)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle("カラオケレパートリー")

       // swipeToDismissTouchHelper.attachToRecyclerView(recyclerView)
        val songList = readAll()



        val adapter = RecyclerViewAdapter(this, songList , object : RecyclerViewAdapter.OnItemClickListener {
            override  fun onItemClick(item: Memo) {
                val SongPage = Intent(this@MainActivity,songpage::class.java)
                SongPage.putExtra("SONG",item.id)
                startActivity(SongPage)


            }
            override fun onItemLongClick(item: Memo) {
                Toast.makeText(this@MainActivity, "削除されました", Toast.LENGTH_SHORT).show()
                Log.d("MainActivty", "削除されました")
                delete(item)
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
            val memo = it.createObject(Memo::class.java, UUID.randomUUID().toString())
            memo.song = song
            memo.singer = singer
            memo.key = key
        }
    }
    private fun getSwipeToDismissTouchHelper(adapter: RecyclerViewAdapter) =
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.ACTION_STATE_IDLE,
            ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            }

        })
    fun delete (memo: Memo){
        realm.executeTransaction{
            memo.deleteFromRealm()
        }

    }


}
