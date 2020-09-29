package heven.holt.jetpack.github.ui.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import heven.holt.jetpack.github.databinding.RepoFragmentBinding
import heven.holt.jetpack.github.util.autoCleared

/**
 *Time:2020/9/25
 *Author:HevenHolt
 *Description:
 */
class RepoFragment : Fragment() {
    var binding by autoCleared<RepoFragmentBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RepoFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}