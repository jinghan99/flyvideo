package top.jinghan94.flyvideo.ui.info

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import top.jinghan94.flyvideo.R
import top.jinghan94.flyvideo.logic.model.PlayVideoUrlModel

/**
 * 适配类
 */
class PlayerTextAdapter(private val videoUrlList:List<PlayVideoUrlModel>) :  RecyclerView.Adapter<PlayerTextAdapter.PlayerTextHolder>() {

    inner class PlayerTextHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView : TextView = view.findViewById(R.id.textAdapterView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerTextHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_text_view, parent, false)
        val playerTextHolder = PlayerTextHolder(view)

        playerTextHolder.textView.setOnClickListener {
            val position = playerTextHolder.bindingAdapterPosition
            val info = videoUrlList[position]
            Toast.makeText(parent.context,"you clicked view ${info}",Toast.LENGTH_SHORT).show()
        }

        return playerTextHolder
    }

    override fun onBindViewHolder(holder: PlayerTextHolder, position: Int) {
        val urlInfo = videoUrlList[position]
        holder.textView.text  = urlInfo.value
    }

    override fun getItemCount(): Int {
        return videoUrlList.size
    }
}