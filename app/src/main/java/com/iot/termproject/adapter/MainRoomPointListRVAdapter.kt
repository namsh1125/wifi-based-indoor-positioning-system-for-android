package com.iot.termproject.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iot.termproject.data.entity.RoomPoint
import com.iot.termproject.databinding.ItemMainBinding

/**
 * Main에서 room point들을 보여준다.
 *
 * @see com.iot.termproject.admin.MainActivity 에서 보여진다.
 */
class MainRoomPointListRVAdapter(
    private val mContext: Context,
    private val mItemClickListener: MyItemClickListener
) : RecyclerView.Adapter<MainRoomPointListRVAdapter.ViewHolder>() {
    private val roomPoints = ArrayList<RoomPoint>()

    // Click listener
    interface MyItemClickListener {
        fun onItemClick(view: View, position: Int)       // 수정
        fun onItemLongClick()   // 삭제
    }

    // ViewHolder 생성
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: ItemMainBinding = ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    // ViewHolder binding
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(roomPoints[position])
    }

    // 데이터셋 크기를 알려준다.
    override fun getItemCount(): Int = roomPoints.size

    // ViewHolder
    inner class ViewHolder(private val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(roomPoint: RoomPoint) {
            binding.itemMainNameTv.text = roomPoint.name
            binding.itemMainXTv.text = roomPoint.x.toString()
            binding.itemMainYTv.text = roomPoint.y.toString()

            itemView.setOnClickListener {
                mItemClickListener.onItemClick(itemView, bindingAdapterPosition)
            }
        }
    }

    // 데이터셋 추가
    @SuppressLint("NotifyDataSetChanged")
    fun addData(roomPoints: List<RoomPoint>) {
        this.roomPoints.clear()
        this.roomPoints.addAll(roomPoints as ArrayList)
        notifyDataSetChanged()
    }
}