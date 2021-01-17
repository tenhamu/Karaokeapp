package app.takuma.tenhamu.karaokeapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_songpage.*

class songpage : AppCompatActivity() {
    val realm: Realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_songpage)

        //val scoreList = readAll()
        val id: String = intent.getStringExtra("SONG")
        Log.d("Intent",id)
        realm.executeTransaction {
            val items = realm.where(Memo::class.java).equalTo("id", id).findFirst()
                ?: return@executeTransaction
            songView.text = items.song
            singerView.text = items.singer
            keyView.text = items.key
    }
        KirokuButton.setOnClickListener {

            val myedit = EditText(this)

            AlertDialog.Builder(this)
                .setTitle(songView.text)
                .setView(myedit)
                .setPositiveButton("保存", DialogInterface.OnClickListener() {_,_->
                    val userText = editText.getText().toString()

                })
                .setNegativeButton("キャンセル", null)
                .show()


        }



}}
