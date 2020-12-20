package app.takuma.tenhamu.karaokeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AlertDialog
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_touroku.*
import kotlinx.android.synthetic.main.item_song_data_cell.*
import java.util.*

class touroku : AppCompatActivity() {
    val realm: Realm = Realm.getDefaultInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_touroku)
        saveButton.setOnClickListener {

            if (songText.length() != 0 && singerText.length() != 0){
                val songName = songText.text
                val singerName = singerText.text
                val keyNumber = keyText.text
                create(songName.toString(),singerName.toString(),keyNumber.toString())
                val mainPage = Intent(this,MainActivity::class.java)
                startActivity(mainPage)
                finish()
            } else {
                AlertDialog.Builder(this) // FragmentではActivityを取得して生成
                    .setTitle("入力されていない項目があります")
                    .setMessage("すべての項目に入力をしてください")
                    .setPositiveButton("OK",null)
                    .show()

            }


        }
        seekBar5.setProgress(0)

        seekBar5.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                //ツマミがドラッグされると呼ばれる
                override fun onProgressChanged(
                    seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                    // 68 % のようにフォーマト、
                    // この場合、Locale.USが汎用的に推奨される
                    val str = String.format(Locale.US, "%d ", progress)
                    keyText.text = str
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {
                    // ツマミがタッチされた時に呼ばれる
                }

                override fun onStopTrackingTouch(seekBar: SeekBar) {
                    // ツマミがリリースされた時に呼ばれる
                }

            })
    }




    fun create(song: String,singer: String,key: String) {
        realm.executeTransaction {
            val memo = it.createObject(Memo::class.java,UUID.randomUUID().toString())
            memo.song = song
            memo.singer = singer
            memo.key = key
        }
    }
}
