package br.com.cvb.venturus.model

class StatusTela(var status: Int, var idMsgErro: Int = -1, val msgAux: String? = null) {

    companion object {
        const val C_STATUS_LOADING = 0
        const val C_STATUS_OK      = 1
        const val C_STATUS_EMPTY   = 2
        const val C_STATUS_ERRO    = 3
    }
}