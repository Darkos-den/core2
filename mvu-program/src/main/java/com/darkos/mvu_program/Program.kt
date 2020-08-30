package com.darkos.mvu_program

import android.util.Log
import com.darkos.mvu.*
import com.darkos.mvu.models.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Program<T: MVUState>(
    private val reducer: Reducer<T>,
    private val effectHandler: EffectHandler,
    private val component: Component<T>,
    initialState: T
) {
    val msgQueue = ArrayDeque<Message>()

    fun accept(message: Message) {
        msgQueue.addLast(message)
        if(msgQueue.isNotEmpty()){
            processMessage()
        }
    }

    val channel = Channel<StateMessageData<T>>()

    var state: T = initialState
        set(value) {
            field = value
            Log.d("LOG[program]", "new state: ${value.javaClass.simpleName}")
        }

    init {
        component.render(initialState)
    }

    fun loop() {
        if (msgQueue.size > 0) {
            processMessage()
        }
    }

    private fun processMessage() {
        StateMessageData(
            state = state,
            message = msgQueue.first()
        ).let {
            CoroutineScope(Dispatchers.IO).launch {
                channel.send(it)
            }
        }
    }

    private val job = CoroutineScope(Dispatchers.IO).launch {
        while (true) {
            channel.receive().let {
                reducer.update(it.state, it.message)
            }.also {
                withContext(Dispatchers.Main) {
                    component.render(it.state)
                }
            }.also {
                state = it.state

                //remove current message from queue
                msgQueue.let {
                    if (it.size > 0) {
                        it.removeFirst()
                    }
                }
                //and send a new msg to relay if any
                loop()
            }.takeIf {
                it.effect !is None
            }?.let {
                CoroutineScope(Dispatchers.IO).launch {
                    effectHandler.call(it.effect).let {
                        when (it) {
                            is Idle -> Unit
                            else -> msgQueue.addLast(it)
                        }

                        loop()
                    }
                }
            }
        }
    }

    fun start() {
        job.start()
        accept(ComponentInitialized)
    }

    fun clear() {
        job.cancel()
    }
}