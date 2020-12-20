package app.takuma.tenhamu.karaokeapp

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection
import kotlinx.android.synthetic.main.item_score_data_cell.view.*
import java.text.FieldPosition

//class ScoreViewAdapter (
//    private val context2: Context,
//    private val scoreList: OrderedRealmCollection<Kiroku>?,
//    private val autoUpdate: Boolean
//) : ScoreRealmAdapter<Kiroku, ScoreViewAdapter.ScorevViewHolder>(scoreList, autoUpdate) {
//
//    override fun ItemCount(): Int = scoreList?.size ?:0
//
//    override fun onBindViewHolder(holder: ScoreViewAdapter, position: Int) {
//        val kiroku: Kiroku = scoreList?.get(position) ?: return
//
//        holder.date.text = kiroku.createdAt
//        holder.score.text = kiroku.score
//
//    }
//     class ScoreRealmAdapter(view: View): RecyclerView.ViewHolder(view) {
//         val date: TextView = view.findViewById()
//     }
//
//}