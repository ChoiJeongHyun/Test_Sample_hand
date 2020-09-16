package kr.co.example.codingtest_hand.views

import android.graphics.Color
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.view.marginTop
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.nostra13.universalimageloader.core.ImageLoader
import kr.co.example.codingtest_hand.database.entity.Ranking
import kr.co.example.codingtest_hand.utils.PLog
import kr.co.example.codingtest_hand.utils.Utils
import kr.co.example.codingtest_hand.widgets.RoundLinearLayout

@BindingAdapter(value = ["rankingItems", "vm"])
fun setItems(recyclerView: RecyclerView, items: List<Ranking>?, vm: MainViewModel){
    if (items == null) return
    recyclerView.adapter?.run {
        if (this is RankingListAdapter){
            this.rankingItems = items
            this.notifyDataSetChanged()
        }
    } ?: run {
        RankingListAdapter(items , vm).apply {
            recyclerView.adapter = this
            this.notifyDataSetChanged()
        }
    }
}



@BindingAdapter(value = ["rankingPosition", "totalSize" , "vm"])
fun bgColorAndMargin(linerLayout: RoundLinearLayout , ranking: Int, totalSize: Int, vm: MainViewModel){
    val v = linerLayout.layoutParams as ViewGroup.MarginLayoutParams
    v.topMargin = Utils.dpToPx(linerLayout.context , 0).toInt()
    v.bottomMargin = Utils.dpToPx(linerLayout.context , 0).toInt()

    when(ranking){
        in 1..3 -> {
            linerLayout.setBackgroundColor(Color.parseColor("#8BC34A"))
            if (ranking == 3){
                if (vm.sortType.value!!) v.bottomMargin = Utils.dpToPx(linerLayout.context , 10).toInt() else
                                         v.topMargin = Utils.dpToPx(linerLayout.context , 10).toInt()
            }
        }

        totalSize -> {
            linerLayout.setBackgroundColor(Color.parseColor("#FF5722"))
            if (vm.sortType.value!!) v.topMargin = Utils.dpToPx(linerLayout.context , 10).toInt() else
                v.bottomMargin = Utils.dpToPx(linerLayout.context , 10).toInt()
        }

        else -> {
            linerLayout.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }
    }

}




