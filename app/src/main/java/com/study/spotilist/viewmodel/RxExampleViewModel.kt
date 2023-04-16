package com.study.spotilist.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import com.study.spotilist.model.Task
import com.study.spotilist.repository.PlaylistRepository
import com.study.spotilist.util.DataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class RxExampleViewModel @Inject constructor(
    private val repository: PlaylistRepository
) : ViewModel() {

    companion object {
        const val TAG = "RxExampleViewModel"
    }

    private val disposableList = CompositeDisposable()

    private fun safeSubscriber(action: () -> Disposable) {
        disposableList.add((action()))
    }

    fun <T : Any> Observable<T>.schedulersIoToMain(): Observable<T> {
        return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun onRxTest() {
        val taskList = DataSource.createTaskList()

        val taskObservable = Observable
            .fromIterable(taskList)
//            .distinct() //Doesn't emit duplicated objects

//            .distinct { it.priority } //Use this rule to determine what defines a duplicated object

//            .take(3)

//            .takeWhile { it.priority == 1 } //Emit until reach a object that satisfy the condition

//            .filter { it.isComplete }

            /*It's not clear what is the difference between filter and groupBy besides
            groupBy changes the result on onSubscribe for "groups" of Observables*/
//            .groupBy { it.priority > 4 }
//            .map { it.isComplete = false }
//            .scan { acc, task -> //Works like a reduce
//                Task("", true, acc.priority + task.priority)
//            }

            /*Returns an Observable that can emit again. Does NOT keep the original order of objects
            e.g. Remap each item of the original list for items of a second list */
//            .flatMap {
//                Observable.create { emitter ->
//                    if (!emitter.isDisposed) {
//                        for (task in DataSource.createSecondTaskList()) {
//                            emitter.onNext(task)
//                        }
//                    }
//                    emitter.onComplete()
//                }
//            }

            /*Returns an Observable that can emit again. It KEEPS the original order of objects, however it waits for
            each observable to finish all the work to go to the next
            e.g. Remap each item of the original list for items of a second list*/
//            .concatMap {
//                Observable.create { emitter ->
//                    if (!emitter.isDisposed) {
//                        for (task in DataSource.createSecondTaskList()) {
//                            emitter.onNext(task)
//                        }
//                    }
//                    emitter.onComplete()
//                }
//            }

//            .buffer(3) //Make the Observable emit bundles of N items instead of single items

//            .ignoreElements() // If I only wanna know when the emitter is done but I don't care about the individual onNext calls

//            .skip(n) //Skips the first N elements

//            .skipLast(n) //Skips the last N elements

//            .take(n) //The opposite of skip. It shows only the first N elements

//            .takeLast(n) //The opposite of skipLast. It shows only the last N elements
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())

        safeSubscriber {
            taskObservable
                .doOnSubscribe {

                }
                .doOnComplete {

                }
                .doOnError {

                }
                .subscribe {
                    Log.d(TAG, "Task: $it")
                }
        }
    }

    @SuppressLint("CheckResult")
    fun onRxTestOnCreate() {
        val task = Task("Some work", true, 1)
        val taskObservable = Observable.create(ObservableOnSubscribe<Task> {
            it.onNext(task)
            it.onComplete()
        })

        taskObservable.subscribe {
            Log.d(TAG, "Task: $task")
        }
    }

    @SuppressLint("CheckResult")
    fun onRxTestInterval() {
        Observable
            .interval(1, TimeUnit.SECONDS)
            .takeWhile { time ->
                time < 5
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d(TAG, "onRxTestInterval: $it")
            }
    }

    @SuppressLint("CheckResult")
    fun onRxTestTimer() {
        Observable
            .timer(10, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d(TAG, "onRxTestTimer: $it")
            }
    }

    override fun onCleared() {
        super.onCleared()
        disposableList.clear()
    }
}