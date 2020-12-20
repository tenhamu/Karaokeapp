package app.takuma.tenhamu.karaokeapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import kotlinx.android.synthetic.main.item_song_data_cell.view.*

class RecyclerViewAdapter(private val context: Context,
                          private var songList: OrderedRealmCollection<Memo>?,
                          private var listener: OnItemClickListener,
                          private val autoUpdate: Boolean) : RealmRecyclerViewAdapter<Memo, RecyclerViewAdapter.ViewHolder>(songList, autoUpdate) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         val view =
             LayoutInflater.from(context).inflate(R.layout.item_song_data_cell, parent, false)
         return ViewHolder(view)
    }

    override fun getItemCount(): Int = songList?.size ?:0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val memo: Memo = songList?.get(position) ?: return

        holder.container.setOnClickListener(){

            listener.onItemClick(memo)

        }
        holder.container.setOnLongClickListener {
            listener.onItemLongClick(memo)
            true
        }

        holder.singerName.text = memo.singer
        holder.songName.text = memo.song
        holder.keyNumber.text = memo.key

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val container: LinearLayout = view.linearLayout
        val songName: TextView = view.findViewById(R.id.songnameView)
        val singerName: TextView = view.findViewById(R.id.singernameView)
        val keyNumber: TextView = view.findViewById(R.id.keynumberview)
    }

    interface OnItemClickListener{
        fun onItemClick(item: Memo)
        fun onItemLongClick(item: Memo)
    }
}