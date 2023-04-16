package com.study.spotilist.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.study.spotilist.databinding.FragmentRxExampleBinding
import com.study.spotilist.viewmodel.RxExampleViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class RxExampleFragment : Fragment() {

    companion object {
        const val TAG = "RxExampleFragment"
    }

    private val viewModel: RxExampleViewModel by viewModels()
    private lateinit var binding: FragmentRxExampleBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRxExampleBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onRxTest()

//        Observable.create { emitter ->
//            binding.fragmentRxExampleButton.setOnClickListener {
//                emitter.onNext(it)
//            }
//        }.subscribe {
//            onButtonClicked()
//        }

        binding.fragmentRxExampleButton.clicks().debounce(4, TimeUnit.SECONDS).subscribe {
            onButtonClicked()
        }
    }

    fun View.clicks(): Observable<View> {
        return Observable.create { emitter ->
            this.setOnClickListener {
                emitter.onNext(it)
            }
        }
    }

    fun onButtonClicked() {
        Log.d(TAG, "Button clicked!!!")
    }

}