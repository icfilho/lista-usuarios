package com.isaiasfilho.listausuario.data

sealed class ActionState{
    object Checking: ActionState()
    object Importing: ActionState()
    object Imported: ActionState()
    object Done: ActionState()
}
