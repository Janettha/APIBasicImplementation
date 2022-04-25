package com.janettha.navigationdrawerexample.sys.util.methods

import com.xwray.groupie.Group

class UtilMethods {

    class Extensions {

        class LiveData {

            companion object {

                fun androidx.lifecycle.LiveData<*>.holdValue(): Boolean {
                    return this.value != null
                }

            }

        }

        class Job {

            companion object {

                fun kotlinx.coroutines.Job?.cancelIfActive() {
                    this?.let {
                        if (isActive) cancel()
                    }
                }

            }

        }

        class Disposable {

            companion object {

                fun io.reactivex.disposables.Disposable?.disposeIfActive() {
                    this?.let {
                        if (!it.isDisposed) it.dispose()
                    }
                }

            }

        }

        class GroupAdapter {

            companion object {
                fun com.xwray.groupie.GroupAdapter<*>.setGroupieData(data: List<Group>) {
                    this.clear()
                    this.addAll(data)
                }
            }

        }

    }

}