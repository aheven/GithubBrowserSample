package heven.holt.jetpack.github.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import heven.holt.jetpack.github.databinding.RepoItemBinding
import heven.holt.jetpack.github.vo.Repo

/**
 *Time:2020/9/28
 *Author:HevenHolt
 *Description:
 */
class RepoListAdapter : DataBoundListAdapter<Repo, RepoItemBinding>(
    object : DiffUtil.ItemCallback<Repo>() {
        override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
            return oldItem.owner == newItem.owner
                    && oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
            return oldItem.description == newItem.description
                    && oldItem.stars == newItem.stars
        }
    }
) {
    override fun createBinding(parent: ViewGroup): RepoItemBinding {
        val binding = RepoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return binding
    }

    override fun bind(binding: RepoItemBinding, item: Repo) {
        binding.repo = item
    }
}