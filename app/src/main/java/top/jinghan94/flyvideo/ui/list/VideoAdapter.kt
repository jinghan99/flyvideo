package top.jinghan94.flyvideo.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import top.jinghan94.flyvideo.R
import top.jinghan94.flyvideo.logic.model.VideoInfoModel

/**
 * 适配类
 */
class VideoAdapter(private val fragment: Fragment, private val videoList: List<VideoInfoModel>) :
    RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val videoImage : ImageView = view.findViewById(R.id.videoImage)
        val videoName: TextView = view.findViewById(R.id.videoName)
        val remark: TextView = view.findViewById(R.id.remark)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.video_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val videoInfo = videoList[position]
        holder.videoName.text = videoInfo.vod_name
        holder.remark.text = videoInfo.vod_remarks
        Glide.with(fragment).load(videoInfo.vod_pic)
            .placeholder(R.drawable.loading)
            .fallback(R.drawable.loading)
            .into(holder.videoImage)
    }

    override fun getItemCount(): Int {
        return videoList.size
    }
}