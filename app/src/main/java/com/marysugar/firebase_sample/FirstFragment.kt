package com.marysugar.firebase_sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.firebase.analytics.FirebaseAnalytics
import com.marysugar.firebase_sample.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private lateinit var binding: FragmentFirstBinding
    private val commonViewModel by activityViewModels<CommonViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let {
            firebaseAnalytics = FirebaseAnalytics.getInstance(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_first,
            container,
            false
        )
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.button.setOnClickListener{
            sendEventData()
        }
    }

    private fun sendEventData() {
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "button_clicked")
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
    }

    companion object {
        val TAG = FirstFragment::class.simpleName
    }
}