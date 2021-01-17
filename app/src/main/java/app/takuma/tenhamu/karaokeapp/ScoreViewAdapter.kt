package app.takuma.tenhamu.karaokeapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import kotlinx.android.synthetic.main.item_score_data_cell.view.*
import java.text.FieldPosition

class ScoreViewAdapter (
    private val context2: Context,
    private val scoreList: OrderedRealmCollection<Kiroku>?,
    private val autoUpdate: Boolean
) : RealmRecyclerViewAdapter<Kiroku, ScoreViewAdapter.ScoreViewHolder>(scoreList, autoUpdate) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        val view =
            LayoutInflater.from(context2).inflate(R.layout.item_score_data_cell, parent, false)
        return ScoreViewHolder(view)
    }

    override fun getItemCount(): Int = scoreList?.size ?:0

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        val kiroku: Kiroku = scoreList?.get(position) ?: return

        holder.datenumber.text = kiroku.createdAt.toString()
        holder.scorenumber.text = kiroku.score.toString()
        holder.kyokumei.text = kiroku.songname

    }
     class ScoreViewHolder(view: View): RecyclerView.ViewHolder(view) {
         val datenumber: TextView = view.findViewById(R.id.datetext)
         val scorenumber: TextView = view.findViewById(R.id.scoretext)
         val kyokumei: TextView = view.findViewById(R.id.songText)

     }

}