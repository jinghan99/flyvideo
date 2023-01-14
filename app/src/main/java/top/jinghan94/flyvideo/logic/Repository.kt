package top.jinghan94.flyvideo.logic

import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher
import top.jinghan94.flyvideo.logic.model.VideoListModel
import top.jinghan94.flyvideo.logic.network.FlyVideoNetwork
import java.lang.Exception

/**
 * 在logic包下新建一个Repository 单例类，作为仓库层的统一封装入口
 */
object Repository {

    /**
     *  仓库曾 统一调用  获取数据（网络接口、本地缓存）
     *  Dispatchers.IO 这样代码块中的所有代码就都运行在子线程中了。
     *  众所周知，Android是 不允许在主线程中进行网络请求的，
     *  诸如读写数据库之类的本地数据操作也是不建议在主线程
    中进行的，因此非常有必要在仓库层进行一次线程转换
     */
    fun searchVideo(url: String) = liveData(Dispatchers.IO) {
        val result = try {
            val videoResponse = FlyVideoNetwork.searchVideo(url)
            if (videoResponse.code == 1) {
                Result.success(videoResponse)
            } else {
                Result.failure(RuntimeException(videoResponse.toString()))
            }
        } catch (e: Exception) {
            Result.failure<VideoListModel>(e)
        }
        emit(result)
    }
}