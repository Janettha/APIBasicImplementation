package com.janettha.navigationdrawerexample.data.datasources.web.dto.request

class PokemonDetailsRequest (
    val offset: Int?,
    val limit: Int?
) {

    companion object {

        private const val PARAM_OFFSET = "offset"

        private const val PARAM_LIMIT = "limit"

        fun HashMap<String, Any>.addToBody(request: PokemonDetailsRequest) {
            if (request.offset != null ) this[PARAM_OFFSET] = request.offset
            if (request.limit != null) this[PARAM_LIMIT] = request.limit
        }
    }
}