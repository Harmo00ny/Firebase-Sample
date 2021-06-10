package com.marysugar.firebase_sample

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
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

        binding.btnEvent.setOnClickListener{
            sendEventData()
        }

        binding.btnSubscribe.setOnClickListener {
            Firebase.messaging.subscribeToTopic("weather")
                .addOnCompleteListener { task ->
                    var msg = getString(R.string.msg_subscribed)
                    if (!task.isSuccessful) {
                        msg = getString(R.string.msg_subscribe_failed)
                    }
                    Log.d(TAG, msg)
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                }
        }

        binding.btnUnsubscribe.setOnClickListener {
            Firebase.messaging.unsubscribeFromTopic("weather")
            Toast.makeText(context, "Unsubscribed", Toast.LENGTH_SHORT).show()
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