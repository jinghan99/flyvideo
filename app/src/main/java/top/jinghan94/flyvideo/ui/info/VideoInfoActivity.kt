package top.jinghan94.flyvideo.ui.info

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_video_info.*
import top.jinghan94.flyvideo.R
import top.jinghan94.flyvideo.logic.model.PlayVideoUrlModel
import top.jinghan94.flyvideo.logic.model.VideoInfoModel


class VideoInfoActivity : AppCompatActivity() {

    private lateinit var adapter: PlayerTextAdapter

    private lateinit var videoInfo: VideoInfoModel


    val list = listOf<PlayVideoUrlModel>(
        PlayVideoUrlModel("第一集", "https://vip.lz-cdn14.com/20221030/14208_b1bbab62/index.m3u8"),
        PlayVideoUrlModel("第二集", "https://vip.lz-cdn9.com/20221229/16685_564029a6/index.m3u8"),
    )

    /**
     * 所有定义在companion
    object中的方法都可以使用类似于Java静态方法的形式调用
     */
    companion object {
        fun actionsStart(context: Context, videoinfo: VideoInfoModel) {
            val intent = Intent(context, VideoInfoActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("videoInfo", videoinfo) //data是DM_Apply的实例对象
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_info)
        videoInfo = intent.getParcelableExtra<VideoInfoModel>("videoInfo")!!;
        Toast.makeText(this,"onCreate  ${videoInfo}", Toast.LENGTH_SHORT).show()

        videoName.text = videoInfo.vod_name
        area.text = videoInfo.vod_area
        year.text = videoInfo.vod_year
        vod_class.text = videoInfo.vod_class

        val layoutManager = LinearLayoutManager(this)
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);//设置为横向滑动
        selectNumberRecyclerView.layoutManager = layoutManager
        adapter = PlayerTextAdapter(list)
        selectNumberRecyclerView.setAdapter(adapter)
    }


}