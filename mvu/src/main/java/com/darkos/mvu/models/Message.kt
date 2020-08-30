package com.darkos.mvu.models

abstract class Message

class Idle: Message()

object ComponentInitialized : Message()