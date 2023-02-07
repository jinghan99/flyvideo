package top.jinghan94.flyvideo.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import top.jinghan94.flyvideo.R
import top.jinghan94.flyvideo.config.MyApplication
import top.jinghan94.flyvideo.logic.model.VideoInfoModel
import top.jinghan94.flyvideo.ui.info.VideoInfoActivity

/**
 * 适配类
 */
class VideoAdapter(private val fragment: Fragment, private val videoList: List<VideoInfoModel>) :
    RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val videoImage : ImageView = view.findViewById(R.id.videoImage)
        val videoName: TextView = view.findViewById(R.id.videoName)
        val vodLang: TextView = view.findViewById(R.id.vod_lang)
        val vodYear: TextView = view.findViewById(R.id.vod_year)
        val vodClass: TextView = view.findViewById(R.id.vod_class)
        val vodActor: TextView = view.findViewById(R.id.vod_actor)
        val vodDirector: TextView = view.findViewById(R.id.vod_director)
        val vodRemarks: TextView = view.findViewById(R.id.vod_remarks)
        val playItem: TextView = view.findViewById(R.id.play_item)
        val videoListFragment: LinearLayout = view.findViewById(R.id.videoItemFragment)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.video_item, parent, false)
        val holder = ViewHolder(view)
       holder.videoListFragment.setOnClickListener {
           val position = holder.bindingAdapterPosition
           val info = videoList[position]
           VideoInfoActivity.actionsStart(MyApplication.context,info)
       }
        holder.playItem.setOnClickListener {
            val position = holder.bindingAdapterPosition
            val info = videoList[position]
            VideoInfoActivity.actionsStart(MyApplication.context,info)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val videoInfo = videoList[position]
        holder.videoName.text = videoInfo.vod_name
        holder.vodLang.text = videoInfo.vod_lang
        holder.vodYear.text = videoInfo.vod_year
        holder.vodClass.text = videoInfo.vod_class
        holder.vodActor.text = "主演：" + videoInfo.vod_actor
        holder.vodDirector.text = "导演：" + videoInfo.vod_director
        holder.vodRemarks.text = "状态：" + videoInfo.vod_remarks
        Glide.with(fragment).load(videoInfo.vod_pic)
            .placeholder(R.drawable.loading)
            .fallback(R.drawable.loading)
            .into(holder.videoImage)
    }


    override fun getItemCount(): Int {
        return videoList.size
    }
}