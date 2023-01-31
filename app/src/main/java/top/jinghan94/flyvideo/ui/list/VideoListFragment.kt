package top.jinghan94.flyvideo.ui.list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
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

    @SuppressLint("NotifyDataSetChanged")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        recycleVideoListView.layoutManager = layoutManager
        adapter = VideoAdapter(this, videoListModel.videoList)
        recycleVideoListView.adapter = adapter

//        监听搜索按钮
        searchButton.setOnClickListener {
            val content = searchVideoEdit.text.toString()
            videoListModel.search(content)
//            清空列表
            videoListModel.videoList.clear()
//            更新界面
            adapter.notifyDataSetChanged()
            recycleVideoListView.visibility = View.GONE
            // 显示进度条
            searchProgressBar.visibility = View.VISIBLE
            noMessage.visibility = View.GONE
            bgImageView.visibility = View.VISIBLE
        }
//      监听键盘
        searchVideoEdit.setOnEditorActionListener(OnEditorActionListener { v, actionId, event -> /*判断是否是“SEARCH”键*/
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val content = searchVideoEdit.text.toString()
                videoListModel.search(content)
//                显示进度条
                searchProgressBar.visibility = View.VISIBLE
//            清空列表
                videoListModel.videoList.clear()
//            更新界面
                adapter.notifyDataSetChanged()
                recycleVideoListView.visibility = View.GONE
                noMessage.visibility = View.GONE
                bgImageView.visibility = View.VISIBLE
            }
           false
        })



//        view Model 添加监听 查询数据响应
        videoListModel.searchUrlDataMap.observe(viewLifecycleOwner, Observer { result ->
            // 隐藏进度条
            searchProgressBar.visibility = View.GONE
            val videoPageList = result.getOrNull()
            if (videoPageList != null) {
                if(videoPageList.list.size>0){
                    recycleVideoListView.visibility = View.VISIBLE
                    bgImageView.visibility = View.GONE
                    videoListModel.videoList.clear()
                    videoListModel.videoList.addAll(videoPageList.list)
                    adapter.notifyDataSetChanged()
                }else{
                    noMessage.visibility = View.VISIBLE
                }
            } else {
               noMessage.visibility = View.VISIBLE
            }
        })

    }
}