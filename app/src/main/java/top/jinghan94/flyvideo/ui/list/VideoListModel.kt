package top.jinghan94.flyvideo.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import top.jinghan94.flyvideo.logic.Repository
import top.jinghan94.flyvideo.logic.model.VideoInfoModel

/**
 * 定义ViewModel层。
 *
 * ViewModel相当于逻辑层和  UI层之间的一个桥梁，
 *  虽然它更偏向于逻辑层的部分，
但是由于ViewModel通常和Activity或 Fragment是一一对应的，
因此我们还是习惯将它们放在一起
 */
class VideoListModel : ViewModel() {

    /**
     *search()函数被调用时，
    switchMap()方法所对应的转换函数就会执行。然后在转换函数中，我们只需要调用仓库层中
    searchVideo()方法就可以发起网络请求，同时将仓库层返回的LiveData对象转换成
    一个可供Activity观察的LiveData对象 searchUrlDataMap。
    另外，我们还在PlaceViewModel中定义了一个videoList 集合，用于对界面上显示的进行缓存，
    因为原则上与界面相关的数据都应该放到ViewModel中，这样可以它们在手
    机屏幕发生旋转的时候不会丢失，
     */
    private val searchUrlData = MutableLiveData<String>()

    val videoList = ArrayList<VideoInfoModel>()

    val searchUrlDataMap = Transformations.switchMap(searchUrlData) { url ->
        Repository.searchVideo(url)
    }

    fun search(query: String) {
        val url = if (query.isNotBlank()) {
            "https://cj.lziapi.com/api.php/provide/vod/?ac=list&wd=$query"
        } else {
            "https://cj.lziapi.com/api.php/provide/vod/?ac=list"
        }
        searchUrlData.value = url
    }

}