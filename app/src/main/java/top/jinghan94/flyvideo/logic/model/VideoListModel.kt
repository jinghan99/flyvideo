package top.jinghan94.flyvideo.logic.model

data class VideoListModel(
    val code: Int,
    val limit: String,
    val msg: String,
    val page: Int,
    val pagecount: Int,
    val total: Int,
    val list: List<VideoInfoModel>,
)