package heven.holt.jetpack.github.ui.common

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 *Time:2020/9/27
 *Author:HevenHolt
 *Description:
 */
class DataBoundViewHolder<out T : ViewDataBinding> constructor(val binding: T) :
    RecyclerView.ViewHolder(binding.root) {
}