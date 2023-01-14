package top.jinghan94.flyvideo.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_video_list.*
import top.jinghan94.flyvideo.R

class VideoListFragment : Fragment() {

    val videoListModel by lazy {
        ViewModelProvider(this).get(VideoListModel::class.java)
    }

    private lateinit var adapter: VideoAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_video_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        recycleVideoListView.layoutManager = layoutManager
        adapter =  VideoAdapter(this, videoListModel.videoList)
        recycleVideoListView.adapter =adapter
        searchVideoEdit.addTextChangedListener { editable ->
            val content = editable.toString()
            if (content.isNotEmpty()) {
                videoListModel.search(content)
            } else {
                recycleVideoListView.visibility = View.GONE
                bgImageView.visibility = View.VISIBLE
                videoListModel.videoList.clear()
                adapter.notifyDataSetChanged()
            }
        }

//        view Model 添加监听
        videoListModel.searchUrlDataMap.observe(viewLifecycleOwner, Observer { result ->
            val videoPageList = result.getOrNull()
            if (videoPageList != null) {
                recycleVideoListView.visibility = View.VISIBLE
                bgImageView.visibility = View.GONE
                videoListModel.videoList.clear()
                videoListModel.videoList.addAll(videoPageList.list)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(activity, "未能查询到任何数据", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })

    }
}