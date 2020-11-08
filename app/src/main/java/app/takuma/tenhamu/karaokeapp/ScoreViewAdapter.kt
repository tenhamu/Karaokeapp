package app.takuma.tenhamu.karaokeapp

import android.content.Context
import io.realm.OrderedRealmCollection
import java.text.FieldPosition

class ScoreViewAdapter (
    private val context2: Context,
    private val scoreList: OrderedRealmCollection<Kiroku>?,
    private val autoUpdate: Boolean
) : ScoreRealmAdapter<Kiroku, ScoreViewAdapter.ScorevViewHolder>(scoreList, autoUpdate) {

    override fun ItemCount(): Int = scoreList?.size ?:0

    override fun onBindViewHolder(holder: ScoreViewAdapter, position: Int) {
        val kiroku: Kiroku = scoreList?.get(position) ?: return

        holder.date.text = kiroku.createdAt
        holder.score.text = kiroku.score

    }
}